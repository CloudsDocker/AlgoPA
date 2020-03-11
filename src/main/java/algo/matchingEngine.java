package algo;

import java.time.Clock;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

import static algo.matchingEngine.Side.BUY;
import static algo.matchingEngine.Side.SELL;

public class matchingEngine {
    // Max number of orders can be queued, more new orders will be blocked until more space freed
    final static int MAX_ORDER_DEPTH=10_000;
    final static String OUTPUT_MATCHES = "%s matches: %s";
    // pre define string constants for most frequent cases, purely for performance
    final static String OUTPUT_NO_MATCHES = "0 matches:";

    //TODO: replace list with blocking queue to provent system overstack
    private Map<Product, Queue<Order>> buyQueue = new ConcurrentSkipListMap<Product, Queue<Order>>();
    private Map<Product, Queue<Order>> sellQueue = new ConcurrentSkipListMap<Product, Queue<Order>>();
    private Map<Side, Map<Product, Queue<Order>>> queueBySide = new HashMap<>();

    public static void main(String[] args) {
        matchingEngine instance = new matchingEngine();
        System.out.println(">>>>>>Starting Matching Engine=====");

        if (instance.sanityCheck()) {
            System.out.println("[DEBUG] All sanity test Passed, please enter your orders");
        } else {
            System.out.println("[ERROR] Sanity checks failure, please contact supprot");
        }

        List<String> input = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            input.add(scanner.nextLine());
        }
        List<String> listOut= instance.processData(input);
        System.out.println("Output messages are:");
        listOut.stream().forEach(s-> System.out.println(s));



    }

    // proxy method
    public List<String> processData(List<String> input) {
        return processData(input, true);
    }

    public List<String> processData(List<String> input, boolean showLogs) {

        queueBySide.put(BUY, sellQueue);
        queueBySide.put(SELL, buyQueue);

        List<String> listOrders = new ArrayList<>();
        for (String order : input) {
            Order tmpOrder = new Order(order);
            if (tmpOrder.size <= 0) {
                if(showLogs)
                    System.out.println("[DEBUG] skip zero order: " + tmpOrder);
                continue;
            }

            Queue<Order> ownSideList = helperOrderQueueBySide(tmpOrder, false);
            Queue<Order> otherSideList = helperOrderQueueBySide(tmpOrder, true);

            Order thatOrder = otherSideList.size() > 0 ? otherSideList.peek() : null;
            long diff = tmpOrder.getSize() - (thatOrder == null ? 0 : thatOrder.getSize());
            if (thatOrder == null) {
                // (1) no other side orders, outout no matching and add current order to current side queue
                listOrders.add(OUTPUT_NO_MATCHES);
                ownSideList.add(tmpOrder);
            } else if (diff < 0) {
                // (2) current order is smaller than other side
                listOrders.add(helperFormatOutput(tmpOrder));
                thatOrder.minusSize(tmpOrder.getSize());
            } else if (diff == 0) {
                listOrders.add(helperFormatOutput(thatOrder, tmpOrder));
                otherSideList.poll();
            } else {//TODO: to refactor this part to common method
                // this.size >= thatOrder.size
                // (3) go to loop and combine multiple orders together
                List<Order> listMatchedOrders = new ArrayList<>();
                long leftSize = tmpOrder.getSize();
                while (!otherSideList.isEmpty()) {
                    thatOrder = otherSideList.peek();
                    if (leftSize < thatOrder.size) {
                        listMatchedOrders.add(tmpOrder);
                        thatOrder.minusSize(leftSize);
                        break;
                    } else {
                        listMatchedOrders.add(thatOrder);
                        otherSideList.poll();
                        leftSize -= thatOrder.size;
                        tmpOrder.setSize(leftSize);
                    }
                }
                if (leftSize == 0) {
                    listMatchedOrders.add(tmpOrder);
                } else {
                    // add residual and put into queue
                    tmpOrder.setSize(leftSize);
                    ownSideList.add(tmpOrder);
                }
                listOrders.add(helperFormatOutput(listMatchedOrders.toArray(new Order[0])));
            }
        }
        if (showLogs)
            System.out.println(">>>>>>End of Matching Engine process. Output of matched orders as:=====");
        return listOrders;
    }

    private String helperFormatOutput(Order... orders) {
        if (orders == null || orders.length < 1) return "";
        List<String> listIDs = Arrays.stream(orders).map(o -> "" + o.orderId).collect(Collectors.toList());
        int n = orders.length;
        return String.format(OUTPUT_MATCHES, n, String.join(",", listIDs));
    }

    private Queue<Order> helperOrderQueueBySide(final Order order, final boolean isReversed) {
        //TODO: to use array of Map
        Map<Product, Queue<Order>> tmpQueue;
        switch (order.side) {
            case BUY:
                tmpQueue = isReversed ? sellQueue : buyQueue;
                break;
            case SELL:
                tmpQueue = isReversed ? buyQueue : sellQueue;
                break;
            default:
                throw new IllegalStateException("Unexpected SIDE: " + order.side);
        }
        if (!tmpQueue.containsKey(order.product)) {
            tmpQueue.put(order.product, new ArrayBlockingQueue<Order>(MAX_ORDER_DEPTH, true));
        }
        return tmpQueue.get(order.product);
    }

    public enum Product {
        APPL(1), MSFT(2);

        Product(int i) {
        }
    }

    enum Side {
        BUY(1), SELL(2);

        Side(int i) {
        }
    }

    class Order implements Comparable {
        private final long orderId;
        private final Product product;
        private final matchingEngine.Side side;
        private final Clock createTime;
        private long size;

        //TODO: to covert to UPPERCASE for input
        public Order(String input) {
            List<String> values = Arrays.stream(input.split("\\s", 4)).filter(s -> !s.isEmpty()).map(s -> s.trim().toUpperCase()).collect(Collectors.toList());
            if (values.size() < 4) {
                throw new IllegalArgumentException("Incorrect input:" + input);
            }
            this.orderId = Long.valueOf(values.get(0));
            this.product = Product.valueOf(values.get(1));
            this.side = matchingEngine.Side.valueOf(values.get(2));
            this.size = Long.valueOf(values.get(3));
            this.createTime = Clock.systemUTC();

        }

        public Order(long orderId, Product product, matchingEngine.Side side, long size) {
            this.orderId = orderId;
            this.product = product;
            this.side = side;
            this.createTime = Clock.systemUTC();
            this.size = size;
        }


        public long getSize() {
            return size;
        }

        public void setSize(long size) {
            this.size = size;
        }

        public long minusSize(long size) {
            this.size -= size;
            return this.size;
        }

        @Override
        public int compareTo(Object o) {
            Order that = (Order) o;
            return this.createTime.instant().compareTo(that.createTime.instant());
        }

        @Override
        public String toString() {
            return "Order{" +
                    "orderId=" + orderId +
                    ", product=" + product +
                    ", side=" + side +
                    ", createTime=" + createTime +
                    ", size=" + size +
                    '}';
        }
    }

    private boolean sanityCheck() {
        List<String> testResult = new ArrayList<String>() {{
            addAll(testCase1());
            addAll(testCase2());
            addAll(testCase3());
            addAll(testCase4());
            addAll(testCase5());
            addAll(testCase6());
            addAll(testCase7());
        }};
        if (testResult.size() > 0) {
            System.out.println("[INFO] Failures of tests:");
            testResult.stream().forEach(s -> System.out.println(s));
            return false;
        }
        return true;
    }

    private List<String> testCase1() {
        matchingEngine instance = new matchingEngine();
        List<String> input = new ArrayList<>();
        List<String> outputExpected = new ArrayList<>();
        input.add("101 APPL BUY 100");
        outputExpected.add(OUTPUT_NO_MATCHES);
        input.add("102 APPL BUY 200");
        outputExpected.add(OUTPUT_NO_MATCHES);

        List<String> output = instance.processData(input,false);

//        output.stream().forEach(s-> System.out.println(s));
        if (output.containsAll(outputExpected) && outputExpected.containsAll(output))
            return Collections.EMPTY_LIST;
        else {
            System.out.println("[INFO] test case 1 failed!");
            return new ArrayList<String>() {{
                addAll(output.stream().filter(s -> !outputExpected.contains(s)).collect(Collectors.toList()));
                addAll(outputExpected.stream().filter(s -> !output.contains(s)).collect(Collectors.toList()));
            }};
        }
    }

    private List<String> testCase2() {
        matchingEngine instance = new matchingEngine();
        List<String> input = new ArrayList<>();
        List<String> outputExpected = new ArrayList<>();
        input.add("101 APPL BUY 100");
        outputExpected.add(OUTPUT_NO_MATCHES);
        input.add("102 APPL BUY 200");
        outputExpected.add(OUTPUT_NO_MATCHES);
        input.add("103 APPL SELL 50");
        outputExpected.add("1 matches: 103");

        List<String> output = instance.processData(input,false);

//        output.stream().forEach(s-> System.out.println(s));
        if (output.containsAll(outputExpected) && outputExpected.containsAll(output))
            return Collections.EMPTY_LIST;
        else {
            System.out.println("[INFO] test case 2 failed!");
            return new ArrayList<String>() {{
                addAll(output.stream().filter(s -> !outputExpected.contains(s)).collect(Collectors.toList()));
                addAll(outputExpected.stream().filter(s -> !output.contains(s)).collect(Collectors.toList()));
            }};
        }
    }

    private List<String> testCase3() {
        matchingEngine instance = new matchingEngine();
        List<String> input = new ArrayList<>();
        List<String> outputExpected = new ArrayList<>();
        input.add("101 APPL BUY 100");
        outputExpected.add(OUTPUT_NO_MATCHES);
        input.add("102 APPL BUY 200");
        outputExpected.add(OUTPUT_NO_MATCHES);
        input.add("103 APPL SELL 500");
        outputExpected.add("2 matches: 101,102");


        List<String> output = instance.processData(input,false);

//        output.stream().forEach(s-> System.out.println(s));
        if (output.containsAll(outputExpected) && outputExpected.containsAll(output))
            return Collections.EMPTY_LIST;
        else {
            System.out.println("[INFO] test case 3 failed!");
            return new ArrayList<String>() {{
                addAll(output.stream().filter(s -> !outputExpected.contains(s)).collect(Collectors.toList()));
                addAll(outputExpected.stream().filter(s -> !output.contains(s)).collect(Collectors.toList()));
            }};
        }
    }

    private List<String> testCase4() {
        matchingEngine instance = new matchingEngine();
        List<String> input = new ArrayList<>();
        List<String> outputExpected = new ArrayList<>();
        input.add("101 APPL BUY 100");
        outputExpected.add(OUTPUT_NO_MATCHES);
        input.add("102 APPL BUY 200");
        outputExpected.add(OUTPUT_NO_MATCHES);
        input.add("103 APPL SELL 400");
        outputExpected.add("2 matches: 101,102");
        input.add("901 MSFT BUY 500");
        outputExpected.add(OUTPUT_NO_MATCHES);
        input.add("104 APPL BUY 300");
        outputExpected.add("1 matches: 103");
        input.add("902 MSFT SELL 100");
        outputExpected.add("1 matches: 902");
        input.add("105 APPL SELL 200");
        outputExpected.add("2 matches: 104,105");
        input.add("903 MSFT SELL 400");
        outputExpected.add("2 matches: 901,903");


        List<String> output = instance.processData(input,false);


//        output.stream().forEach(s-> System.out.println(s));
        if (output.containsAll(outputExpected) && outputExpected.containsAll(output))
            return Collections.EMPTY_LIST;
        else {
            System.out.println("[INFO] test case 4 failed!");
            return new ArrayList<String>() {{
                addAll(output.stream().filter(s -> !outputExpected.contains(s)).collect(Collectors.toList()));
                addAll(outputExpected.stream().filter(s -> !output.contains(s)).collect(Collectors.toList()));
            }};
        }
    }

    private List<String>
    testCase5() {
        matchingEngine instance = new matchingEngine();
        List<String> input = new ArrayList<>();
        List<String> outputExpected = new ArrayList<>();
        input.add("101 appl BUY 100");
        outputExpected.add(OUTPUT_NO_MATCHES);
        input.add("102 APPL buy 200");
        outputExpected.add(OUTPUT_NO_MATCHES);
        input.add("103 AppL SELL 300");
        outputExpected.add("3 matches: 101,102,103");


        List<String> output = instance.processData(input,false);

//        output.stream().forEach(s-> System.out.println(s));
        if (output.containsAll(outputExpected) && outputExpected.containsAll(output))
            return Collections.EMPTY_LIST;
        else {
            System.out.println("[INFO] test case 5 failed!");
            return new ArrayList<String>() {{
                addAll(output.stream().filter(s -> !outputExpected.contains(s)).collect(Collectors.toList()));
                addAll(outputExpected.stream().filter(s -> !output.contains(s)).collect(Collectors.toList()));
            }};
        }
    }

    private List<String> testCase6() {
        matchingEngine instance = new matchingEngine();
        List<String> input = new ArrayList<>();
        List<String> outputExpected = new ArrayList<>();
        input.add("101 APPL BUY 100");
        outputExpected.add(OUTPUT_NO_MATCHES);
        input.add("102 APPL BUY 200");
        outputExpected.add(OUTPUT_NO_MATCHES);
        input.add("103 APPL SELL 250");
        outputExpected.add("2 matches: 101,103");

        List<String> output = instance.processData(input,false);

//        output.stream().forEach(s-> System.out.println(s));
        if (output.containsAll(outputExpected) && outputExpected.containsAll(output))
            return Collections.EMPTY_LIST;
        else {
            System.out.println("[INFO] test case 6 failed!");
            return new ArrayList<String>() {{
                addAll(output.stream().filter(s -> !outputExpected.contains(s)).collect(Collectors.toList()));
                addAll(outputExpected.stream().filter(s -> !output.contains(s)).collect(Collectors.toList()));
            }};
        }
    }

    private List<String> testCase7() {
        matchingEngine instance = new matchingEngine();
        List<String> input = new ArrayList<>();
        List<String> outputExpected = new ArrayList<>();
        input.add("101 APPL BUY 100");
        outputExpected.add(OUTPUT_NO_MATCHES);
        input.add("102 APPL BUY 200");
        outputExpected.add(OUTPUT_NO_MATCHES);
        input.add("103 APPL SELL 400");
        outputExpected.add("2 matches: 101,102");
        input.add("104 APPL BUY 500");
        outputExpected.add("1 matches: 103");
        input.add("105 APPL SELL 400");
        outputExpected.add("2 matches: 104,105");

        List<String> output = instance.processData(input,false);

//        output.stream().forEach(s-> System.out.println(s));
        if (output.containsAll(outputExpected) && outputExpected.containsAll(output))
            return Collections.EMPTY_LIST;
        else {
            System.out.println("[INFO] test case 7 failed!");
            return new ArrayList<String>() {{
                addAll(output.stream().filter(s -> !outputExpected.contains(s)).collect(Collectors.toList()));
                addAll(outputExpected.stream().filter(s -> !output.contains(s)).collect(Collectors.toList()));
            }};
        }
    }
}
