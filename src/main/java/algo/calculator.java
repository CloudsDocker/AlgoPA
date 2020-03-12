
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ANZCalculator {
    public static void main(String[] args) {
        System.out.println("====");
        ANZCalculator inst=new ANZCalculator();
        List<String> list=new ArrayList<>();
        list.add("AUD 100.00 in USD");
        double price=0.8371;
        int qty=10;
        int decimalPoint=2;
        System.out.println("AUD 100.00 in USD:"+(int)(price*qty*Math.pow(10,2))/Math.pow(10,2));
    }

    public void processData(List<String> list){

    }

    public List<String> processInput(List<String> inputs){

        return null;
    }

    private double getQuote(String ccyPair){
        Map<String, Double> prices=new HashMap<>();
        prices.putIfAbsent("AUDUSD",0.8371);
        return prices.get(ccyPair);
    }

    private int getDecimalLength(String ccy){
        Map<String,Integer> decimalMapping=new HashMap<>();
        decimalMapping.putIfAbsent("AUD",2);
        decimalMapping.putIfAbsent("USD",2);
        decimalMapping.putIfAbsent("JPY",0);
        decimalMapping.putIfAbsent("GBP",2);
        return decimalMapping.getOrDefault(ccy,0);
    }

    class PriceRequest{

    }

}
