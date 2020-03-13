package anz.test;

public class CurrencyPairImpl implements CurrencyPair {
    private final String ccyPair;

    public CurrencyPairImpl(String ccy1, String ccy2) {
        this(ccy1+ccy2);
    }

    public CurrencyPairImpl(String ccyPair) {
        assert ccyPair != null: "currency pair cannot be null";
        assert ccyPair.length() == 6: "must be a 6-characters string and without slash; Refer to ISO 4217 codes for base and terms currencies";
        this.ccyPair = ccyPair;
    }

    @Override
    public String getBaseCurrency() {
        return ccyPair.substring(0,3);
    }

    @Override
    public String getCounterCurrency() {
        return ccyPair.substring(3);
    }

    @Override
    public String getSymbol() {
        return ccyPair;
    }

    @Override
    public String getSymbolCounter() {
        return getCounterCurrency()+getBaseCurrency();
    }

}
