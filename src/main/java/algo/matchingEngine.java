package algo;

import java.time.Clock;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;




    import java.time.Clock;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;
public class matchingEngine {

        private Map<Product, List<Order>> buyQueue=  new ConcurrentSkipListMap<Product, List<Order>>();
        private Map<Product,List<Order>> sellQueue=  new ConcurrentSkipListMap<Product, List<Order>>();
        private Set<Product> products=new HashSet<>();
        Map<Side, Map<Product,List<Order>>> queueBySide=new HashMap<>();

        public static void main(String[] args) {
            matchingEngine instance = new matchingEngine();
            System.out.println(">>>>>>Starting Matching Engine=====");
            Scanner scanner = new Scanner(System.in);
            List<String> input = new ArrayList<>();
            while (scanner.hasNext()) {
                input.add(scanner.nextLine());
            }

            instance.processData(input);
            System.out.println("input is:" + Arrays.toString(input.toArray()));
        }

        public void processData(List<String> input){

            queueBySide.put(Side.BUY,sellQueue);
            queueBySide.put(Side.SELL,buyQueue);

            List<Order> listOrders=new ArrayList<>();
            for (String order:input ) {
                Order tmpOrder = new Order(order);
                if(tmpOrder.size<=0){
                    System.out.println("skip zero order: "+ tmpOrder);
                    continue;
                }

                List<Order> ownSideList=getOrderQueueBySide(tmpOrder,false);
                List<Order> otherSideList=getOrderQueueBySide(tmpOrder,true);

                Order thatOrder= otherSideList.get(0);
                if(thatOrder==null || tmpOrder.getSize()<thatOrder.getSize()){
                    // (1) no other side orders, outout no matching and add current order to current side queue
                    // (2) current order is smaller than other side
                    listOrders.add(thatOrder==null?Order.NON_MATCHING:tmpOrder);
                    ownSideList.add(tmpOrder);
                }else{
                    // this.size >= thatOrder.size
                    // (3) go to loop and combine multiple orders together

                    listOrders.add(tmpOrder);tttttt
                    ownSideList.add(tmpOrder);

                    while(thatOrder!=null){
                        // 2.1 all matched
                        if(tmpOrder.size==thatOrder.size){
                            listOrders.add(thatOrder);
                        }else if(tmpOrder.size>thatOrder.size){
                            listOrders.add(thatOrder);
//                        Order
                        }
                    }
                }
            }

            List<Order> listOutput=new ArrayList<>();

            for (Order order:listOrders) {

                Side side=order.side;
                Product product= order.product;
                long size=order.size;

                Queue<Order> orders  = queueBySide.get(side).get(product);
                Order counterOrder=orders.poll();

                if(counterOrder==null){
                    listOutput.add(Order.NON_MATCHING);

                }
                if(counterOrder!=null && size==counterOrder.size){
                    listOrders.add(counterOrder);
                }else if(size>counterOrder.size){

                }




                Order buyOrder = buyOrders.poll();
                Order sellOrder = sellOrders.poll();
                if(buyOrder.size==sellOrder.size){
                    // all matached
                    listMatchedOrders.add(buyOrder);
                    listMatchedOrders.add(sellOrder);
                    buyQueue.get(product).poll();
                    sellQueue.get(product).poll();
                }
            }


            for (Product product:products) {


                Order buyOrder = buyOrders.poll();
                Order sellOrder = buyQueue.get(product).peek();



            }

            System.out.println(">>>>>>End of Matching Engine process. Output of matched orders as:=====");

        }

        public enum Product{
            AAPL(1), MSFT(2);

            Product(int i) {
            }
        }

        private enum Side{
            BUY(1),SELL(2);

            Side(int i) {
            }
        }

        private List<Order> getOrderQueueBySide(Order order,boolean isReversed){
            //TODO: to use array of Map
            Map<Product,List<Order>> tmpQueue;
            switch (order.side){
                case BUY:
                    tmpQueue= isReversed?sellQueue:buyQueue;
                    break;
                case SELL:
                    tmpQueue=isReversed?buyQueue:sellQueue;
                    break;
                default:
                    throw new IllegalStateException("Unexpected SIDE: " + order.side);
            }
//            products.add(tmpOrder.product);
//            listOrders.add(tmpOrder);
            tmpQueue.getOrDefault(order.product,new ArrayList<>());
            return tmpQueue.get(order.product);
        }

        static class Order implements Comparable{
            private final long orderId;
            private final Product product;
            private final Side side;
            private final Clock createTime;
            public static final Order NON_MATCHING=nonMatchingOrder();

            public Order(String input){
                List<String> values= Arrays.stream(input.split("\\s", 4)).filter(s->!s.isEmpty()).map(s->s.trim()).collect(Collectors.toList());
                if(values.size()<5){
                    throw new IllegalArgumentException("Incorrect input:"+input);
                }
                this.orderId=Long.valueOf(values.get(0));
                this.product=Product.valueOf(values.get(1));
                this.side=Side.valueOf(values.get(2));
                this.size=Long.valueOf(values.get(3));
                this.createTime=Clock.systemUTC();

            }

            public Order(long orderId, Product product, Side side,  long size) {
                this.orderId = orderId;
                this.product = product;
                this.side = side;
                this.createTime = Clock.systemUTC();
                this.size = size;
            }

            private static Order nonMatchingOrder(){
                return new Order(0,null,null,0);
            }

            private long size;

            public long getSize() {
                return size;
            }

            public void setSize(long size) {
                this.size = size;
            }

            @Override
            public int compareTo(Object o) {
                Order that = (Order)o;
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
    }

