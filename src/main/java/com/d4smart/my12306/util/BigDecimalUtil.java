package com.d4smart.my12306.util;

import java.math.BigDecimal;

/**
 * Created by d4smart on 2017/6/30 18:08
 */
public class BigDecimalUtil {

    private BigDecimalUtil() {

    }

    public static BigDecimal add(double a, double b) {
        BigDecimal m = new BigDecimal(Double.toString(a));
        BigDecimal n = new BigDecimal(Double.toString(b));
        return m.add(n);
    }

    public static BigDecimal subtract(double a, double b) {
        BigDecimal m = new BigDecimal(Double.toString(a));
        BigDecimal n = new BigDecimal(Double.toString(b));
        return m.subtract(n);
    }

    public static BigDecimal multiply(double a, double b) {
        BigDecimal m = new BigDecimal(Double.toString(a));
        BigDecimal n = new BigDecimal(Double.toString(b));
        return m.multiply(n);
    }

    public static BigDecimal divide(double a, double b) {
        BigDecimal m = new BigDecimal(Double.toString(a));
        BigDecimal n = new BigDecimal(Double.toString(b));
        return m.divide(n, 2, BigDecimal.ROUND_HALF_UP); // 四舍五入，保留2位小数
    }
}
