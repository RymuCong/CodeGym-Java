package com.cg.casestudy.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class PriceFormatter {
    public static String formatPrice(int price) {
        NumberFormat formatter = NumberFormat.getInstance(Locale.US);
        return formatter.format(price);
    }
}
