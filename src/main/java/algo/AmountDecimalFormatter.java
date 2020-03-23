package anz.test;

import java.math.BigDecimal;

public class AmountDecimalFormatter {

    private final int precision;

    public AmountDecimalFormatter(int precision)
    {
        this.precision = precision;
    }

    public String format(String amount) {
        return format(new BigDecimal(amount == null || amount.isEmpty() ? "0" : amount));
    }

    public String format(BigDecimal amount) {
        return amount.setScale(precision, BigDecimal.ROUND_HALF_UP).toPlainString();
    }
}
