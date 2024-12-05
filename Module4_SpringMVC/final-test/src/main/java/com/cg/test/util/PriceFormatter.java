package com.cg.test.util;

public class PriceFormatter {
    public static String formatPrice(Long price) {
        return String.format("%,d", price);
    }
}
