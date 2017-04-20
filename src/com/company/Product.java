package com.company;

class Product {
    private String name;
    private double price;
    private int bar_code;

    Product(String name, double price, int bar_code) {
        this.name = name;
        this.price = price;
        this.bar_code = bar_code;
    }

    double getPrice() {
        return price;
    }

    String getName() {
        return name;
    }

    int getBar_code() {
        return bar_code;
    }

    String print() {
        java.text.DecimalFormat df = new java.text.DecimalFormat();
        df.setMinimumFractionDigits(2);

        StringBuilder sb = new StringBuilder("                                           ");
        sb.replace(0, name.length(), name);
        sb.replace(15, (15 + df.format(price).length()), df.format(price));

        return sb.toString();
    }
}
