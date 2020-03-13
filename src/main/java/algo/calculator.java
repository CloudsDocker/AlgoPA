package anz.test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.math.RoundingMode.HALF_UP;

/*
 final int scale = leg.getOrder().getDealPrecision().getContraCcyAmountPrecision();
        final BigDecimal baseAmount = new BigDecimal(allocation.getDealtCcyAmt());
        final BigDecimal spotPx = new BigDecimal(spotPrice);
        final BigDecimal value = (isBaseCcyDealt)
                ? baseAmount.multiply(spotPx)
                : baseAmount.divide(spotPx, scale, BigDecimal.ROUND_HALF_UP);
        return contraCcyAmtFormatter.format(value);





    ======


    if (baseUsdComponentSymbol.isBaseCurrency(USD)) {
            if (counterUsdComponentSymbol.isBaseCurrency(USD)) {
                // USD/YYY = (XXX/YYY) * (USD/XXX)
                counterUsdClientRate = crossCurrencyClientRate.multiply(baseUsdTraderRate);
                counterUsdClientRate = counterUsdClientRate.setScale(precision, ROUNDING_MODE);
            } else {
                // YYY/USD = 1 / ((XXX/YYY) * (USD/XXX))
                counterUsdClientRate = ONE.divide(crossCurrencyClientRate.multiply(baseUsdTraderRate), precision, ROUNDING_MODE);
            }
        } else {
            if (counterUsdComponentSymbol.isBaseCurrency(USD)) {
                // USD/YYY = (XXX/YYY) / (XXX/USD)
                counterUsdClientRate = crossCurrencyClientRate.divide(baseUsdTraderRate, precision, ROUNDING_MODE);
            } else {
                // YYY/USD = (XXX/USD) / (XXX/YYY)
                counterUsdClientRate = baseUsdTraderRate.divide(crossCurrencyClientRate, precision, ROUNDING_MODE);
            }
        }

        ==========
            @Override
    public String getBaseCurrency() {
        return ccyPair.substring(0,3);
    }

    @Override
    public String getCounterCurrency() {
        return ccyPair.substring(3);
    }



 */
public class calc {

    final int SCALE=4;
    final RoundingMode ROUND_MODE= HALF_UP;

    public static void main(String[] args) {
        System.out.println("====");
        calc inst=new calc();
        List<String> list=new ArrayList<>();
        list.add("AUD 100.00 in USD");
        list.add("USD 100.00 in AUD");
//        double price=0.8371;
//        int qty=10;
//        int decimalPoint=2;
//        System.out.println("AUD 100.00 in USD:"+(int)(price*qty*Math.pow(10,2))/Math.pow(10,2));
        System.out.println("==output==:"+inst.processData(list));

    }

    public List<String> processData(List<String> list){
        return processInput(list).stream().map(o->calculatePrice(o)).collect(Collectors.toList());
    }

    private boolean isBaseCcyDealt(PriceRequest pr){
        if(getQuote(pr.getCcy(),false)!=null){
            return true;
        }
        else{
            return false;
        }
    }

    private String calculatePrice(PriceRequest pr){
        boolean isBaseCcyDealt = isBaseCcyDealt(pr);
        int precision=0;
        BigDecimal result;

        if(isBaseCcyDealt){
            precision=getDecimalLength(pr.getCcy().getBaseCurrency());
            result = BigDecimal.valueOf(pr.amount).multiply(getQuote(pr.getCcy(),false)).setScale(SCALE, ROUND_MODE);
        }else{
            precision=getDecimalLength(pr.getCcy().getCounterCurrency());
            BigDecimal price= getQuote(pr.getCcy(),true);
            result = BigDecimal.valueOf(pr.amount).multiply(BigDecimal.ONE.divide(price, SCALE, ROUND_MODE));
        }

        AmountDecimalFormatter formatter = new AmountDecimalFormatter(precision);
        return formatter.format(result);
    }

    public List<PriceRequest> processInput(List<String> inputs){
        assert inputs!=null:"Incorrect price input";
        return inputs.stream().filter(s->!s.isEmpty()).map(s->s.toUpperCase()).map(s->{
                    String[] ary=s.split("\\s",4);
                    double amount= Double.valueOf(ary[1]);
                    CurrencyPair ccy = new CurrencyPairImpl(ary[0],ary[3]);
                    return new PriceRequest(ccy,amount);
                }).collect(Collectors.toList());
    }

    private BigDecimal getQuote(CurrencyPair ccyPair, boolean rev){
        Map<String, BigDecimal> prices=new HashMap<>();
        prices.putIfAbsent("AUDUSD",BigDecimal.valueOf(0.8371));
        prices.putIfAbsent("USDJPY",BigDecimal.valueOf(119.95));
        return prices.get(rev?ccyPair.getSymbolCounter():ccyPair.getSymbol());
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
        CurrencyPair ccy;
        double amount;

        public PriceRequest(CurrencyPair ccy, double amount) {
            this.ccy = ccy;
            this.amount = amount;
        }

        public CurrencyPair getCcy() {
            return ccy;
        }

        public void setCcy(CurrencyPair ccy) {
            this.ccy = ccy;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }
    }


}
