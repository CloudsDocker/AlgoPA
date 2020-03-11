package algo;

import java.time.Clock;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;


import static algo.matchingEngine.Side.BUY;
import static algo.matchingEngine.Side.SELL;

public class matchingEngine {
    final static String OUTPUT_MATCHED = "%s matched";
    final static String OUTPUT_NO_MATCH = "%s no matching";
    private Map<Product, List<Order>> buyQueue = new ConcurrentSkipListMap<Product, List<Order>>();
    private Map<Product, List<Order>> sellQueue = new ConcurrentSkipListMap<Product, List<Order>>();
    private Map<Side, Map<Product, List<Order>>> queueBySide = new HashMap<>();

    public static void main(String[] args) {
        matchingEngine instance = new matchingEngine();
        System.out.println(">>>>>>Starting Matching Engine=====");
        List<String> input = new ArrayList<>();

        if (instance.sanityCheck()) {
            System.out.println("[DEBUG] All sanity test Passed");
        }else{
            System.out.println("[ERROR] Sanity checks failure");
        }
    }



    public List<Order> processData(List<String> input) {

        queueBySide.put(BUY, sellQueue);
        queueBySide.put(SELL, buyQueue);


        final Order NON_MATCHING = new Order(0, null, null, 0);

        List<Order> listOrders = new ArrayList<>();
        for (String order : input) {
            Order tmpOrder = new Order(order);
            if (tmpOrder.size <= 0) {
                System.out.println("skip zero order: " + tmpOrder);
                continue;
            }

            List<Order> ownSideList = helperOrderQueueBySide(tmpOrder, false);
            List<Order> otherSideList = helperOrderQueueBySide(tmpOrder, true);

            Order thatOrder = otherSideList.size() > 0 ? otherSideList.get(0) : null;
            long diff = tmpOrder.getSize() - (thatOrder == null ? 0 : thatOrder.getSize());
            if (thatOrder == null) {
                // (1) no other side orders, outout no matching and add current order to current side queue
                listOrders.add(NON_MATCHING);
                ownSideList.add(tmpOrder);
            } else if (diff < 0) {
                // (2) current order is smaller than other side
                listOrders.add(tmpOrder);
                thatOrder.minusSize(tmpOrder.getSize());
            } else if (diff == 0) {
                listOrders.add(tmpOrder);
                listOrders.add(thatOrder);
                otherSideList.remove(thatOrder);
            } else {//TODO: to refactor this part to common method
                // this.size >= thatOrder.size
                // (3) go to loop and combine multiple orders together
                long leftSize = tmpOrder.getSize();
                while (!otherSideList.isEmpty()) {
                    thatOrder = otherSideList.get(0);
                    if (leftSize == thatOrder.size) {
                        // same
                        listOrders.add(thatOrder);
                        otherSideList.remove(thatOrder);
                    } else if (leftSize < thatOrder.size) {
                        listOrders.add(tmpOrder);
                        thatOrder.minusSize(leftSize);
                    } else {
                        // still bigger
                        listOrders.add(thatOrder);
                        otherSideList.remove(thatOrder);
                        leftSize -= thatOrder.size;
                    }
                }
                if (leftSize > 0) {
                    // add residual and put into queue
                    tmpOrder.setSize(leftSize);
                    ownSideList.add(tmpOrder);
                }
            }
        }

        System.out.println(">>>>>>End of Matching Engine process. Output of matched orders as:=====");
        return listOrders;
    }


    private List<String> helperFormatOutput(List<Order> listOutputOrders) {
        return listOutputOrders.stream().map(o -> {
            if (o.orderId > 0) {
                return String.format(OUTPUT_MATCHED, o.orderId);
            } else {
                return String.format(OUTPUT_NO_MATCH, 0);
            }
        }).collect(Collectors.toList());
    }

    private List<Order> helperOrderQueueBySide(final Order order, final boolean isReversed) {
        //TODO: to use array of Map
        Map<Product, List<Order>> tmpQueue;
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
            tmpQueue.put(order.product, new ArrayList<>());
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
            List<String> values = Arrays.stream(input.split("\\s", 4)).filter(s -> !s.isEmpty()).map(s -> s.trim()).collect(Collectors.toList());
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

    private boolean sanityCheck(){
        List<String> testResult=new ArrayList<String>(){{
            addAll(testCase1());
            addAll(testCase2());
            addAll(testCase3());
            addAll(testCase4());
        }};
        if(testResult.size()>0){
            System.out.println("[INFO] Failures of tests:");
            testResult.stream().forEach(s-> System.out.println(s));
            return false;
        }
        return true;
    }
    private List<String> testCase1() {
        matchingEngine instance = new matchingEngine();
        List<String> input = new ArrayList<>();
        List<String> outputExpected = new ArrayList<>();
        input.add("101 APPL BUY 100");
        outputExpected.add("0 no matching");
        input.add("102 APPL BUY 200");
        outputExpected.add("0 no matching");

        List<String> output = instance.helperFormatOutput(instance.processData(input));

//        output.stream().forEach(s-> System.out.println(s));
        if (output.containsAll(outputExpected) && outputExpected.containsAll(output))
            return Collections.EMPTY_LIST;
        else{
            System.out.println("[INFO] test case 1 failed!");
            return new ArrayList<String>(){{
                addAll(output.stream().filter(s->!outputExpected.contains(s)).collect(Collectors.toList()));
                addAll(outputExpected.stream().filter(s->!output.contains(s)).collect(Collectors.toList()));
            }};
        }
    }

    private List<String> testCase2() {
        matchingEngine instance = new matchingEngine();
        List<String> input = new ArrayList<>();
        List<String> outputExpected = new ArrayList<>();
        input.add("101 APPL BUY 100");
        outputExpected.add("0 no matching");
        input.add("102 APPL BUY 200");
        outputExpected.add("0 no matching");
        input.add("103 APPL SELL 50");
        outputExpected.add("103 matched");

        List<String> output = instance.helperFormatOutput(instance.processData(input));

//        output.stream().forEach(s-> System.out.println(s));
        if (output.containsAll(outputExpected) && outputExpected.containsAll(output))
            return Collections.EMPTY_LIST;
        else{
            System.out.println("[INFO] test case 2 failed!");
            return new ArrayList<String>(){{
                addAll(output.stream().filter(s->!outputExpected.contains(s)).collect(Collectors.toList()));
                addAll(outputExpected.stream().filter(s->!output.contains(s)).collect(Collectors.toList()));
            }};
        }
    }

    private List<String> testCase3() {
        matchingEngine instance = new matchingEngine();
        List<String> input = new ArrayList<>();
        List<String> outputExpected = new ArrayList<>();
        input.add("101 APPL BUY 100");
        outputExpected.add("0 no matching");
        input.add("102 APPL BUY 200");
        outputExpected.add("0 no matching");
        input.add("103 APPL SELL 500");
        outputExpected.add("101 matched");
        outputExpected.add("102 matched");

        List<String> output = instance.helperFormatOutput(instance.processData(input));

//        output.stream().forEach(s-> System.out.println(s));
        if (output.containsAll(outputExpected) && outputExpected.containsAll(output))
            return Collections.EMPTY_LIST;
        else{
            System.out.println("[INFO] test case 3 failed!");
            return new ArrayList<String>(){{
                addAll(output.stream().filter(s->!outputExpected.contains(s)).collect(Collectors.toList()));
                addAll(outputExpected.stream().filter(s->!output.contains(s)).collect(Collectors.toList()));
            }};
        }
    }

    private List<String> testCase4() {
        matchingEngine instance = new matchingEngine();
        List<String> input = new ArrayList<>();
        List<String> outputExpected = new ArrayList<>();
        input.add("101 APPL BUY 100");
        outputExpected.add("0 no matching");
        input.add("102 APPL BUY 200");
        outputExpected.add("0 no matching");
        input.add("103 APPL SELL 400");
        outputExpected.add("101 matched");
        outputExpected.add("102 matched");
        input.add("901 MSFT BUY 500");
        outputExpected.add("0 no matching");
        input.add("104 APPL BUY 300");
        outputExpected.add("103 matched");
        input.add("902 MSFT SELL 100");
        outputExpected.add("902 matched");
        input.add("105 APPL SELL 200");
        outputExpected.add("104 matched");
        outputExpected.add("105 matched");
        input.add("903 MSFT SELL 400");
        outputExpected.add("903 matched");
        outputExpected.add("901 matched");

        List<String> output = instance.helperFormatOutput(instance.processData(input));


//        output.stream().forEach(s-> System.out.println(s));
        if (output.containsAll(outputExpected) && outputExpected.containsAll(output))
            return Collections.EMPTY_LIST;
        else{
            System.out.println("[INFO] test case 4 failed!");
            return new ArrayList<String>(){{
                addAll(output.stream().filter(s->!outputExpected.contains(s)).collect(Collectors.toList()));
                addAll(outputExpected.stream().filter(s->!output.contains(s)).collect(Collectors.toList()));
            }};
        }
    }
}
