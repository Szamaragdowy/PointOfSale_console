package com.company;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class PointOfSale {
    private Printer printer;
    private Lcd lcd;
    private List<Product> database;
    private List<Product> receipt;
    private Scanner in;
    private String loaded_line;
    private int loaded_bar_code;
    private double sum;

    PointOfSale() {
        this.printer = new Printer();
        this.lcd = new Lcd();
        this.database = new LinkedList<>();
        this.receipt = new LinkedList<>();
        LoadDatabase();
        in = new Scanner(System.in);
    }

    private void LoadDatabase() {
        database.add(new Product("", 0.00, 100));
        database.add(new Product("bread", 2.50, 101));
        database.add(new Product("roll", 0.45, 102));
        database.add(new Product("butter", 4.00, 103));
        database.add(new Product("cheese", 20.00, 104));
        database.add(new Product("eggs", 4.50, 105));
        database.add(new Product("onion", 3.00, 106));
        database.add(new Product("tomatoes", 4.50, 107));
        database.add(new Product("cocumbers", 4.00, 108));
        database.add(new Product("ketchup", 4.00, 109));
        database.add(new Product("milk", 2.30, 110));
        database.add(new Product("yoghurt", 1.50, 111));
        database.add(new Product("potatoes", 1.20, 112));
        database.add(new Product("rice", 2.85, 113));
        database.add(new Product("pasta", 2.80, 114));
        database.add(new Product("meat", 12.00, 115));
        database.add(new Product("fish", 15.00, 116));
        database.add(new Product("oil", 22.00, 117));
        database.add(new Product("mineral water", 1.60, 118));
        database.add(new Product("coca-cola", 5.00, 119));
        database.add(new Product("juice", 5.00, 120));
        database.add(new Product("pepsi", 3.50, 121));
        database.add(new Product("coffee", 8.00, 122));
        database.add(new Product("tea", 3.40, 123));
        database.add(new Product("sugar", 2.65, 124));
        database.add(new Product("beer", 2.50, 125));
        database.add(new Product("vodka", 21.00, 126));
        database.add(new Product("wine", 15.00, 127));
        database.add(new Product("cigarettes", 13.20, 128));
        database.add(new Product("chips", 5.00, 129));
        database.add(new Product("chocolate", 3.00, 130));
    }

    void run() {
        System.out.println("Zeskanuj produkt  w bazie produkty mają  kod 101 do 130 a produkt z kodem 100 jest \"pusty\" ");
        while (true) {
            read_code();
            if (loaded_line.equals("exit")) break;
            analyze_code();
        }
        receipt();
    }

    private void receipt() {
        if (sum != 0.00) {
            for (Product x : receipt) {
                printer.print(x.print());
            }
            java.text.DecimalFormat df = new java.text.DecimalFormat();
            df.setMinimumFractionDigits(2);

            printer.print("total: " + df.format(sum));
            lcd.print(df.format(sum));
        } else {
            lcd.print("No Products");
        }
    }

    private void read_code() {
        loaded_line = in.nextLine();
    }

    private void analyze_code() {
        try {
            loaded_bar_code = Integer.parseInt(loaded_line);

            for (Product x : database) {
                if (x.getBar_code() == loaded_bar_code) {
                    if (x.getName().isEmpty()) {
                        lcd.error_print("Invalid bar-code");
                    } else {
                        lcd.print(x.print());
                        receipt.add(x);
                        sum += x.getPrice();
                    }
                    return;
                }
            }
            lcd.error_print("Product not found");
        } catch (NumberFormatException e) {
            System.out.println("Prosze podac poprawny bar-code");
        }
    }
}
