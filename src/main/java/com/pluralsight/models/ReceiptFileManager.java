package com.pluralsight.models;

import com.pluralsight.models.store.Store;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public abstract class ReceiptFileManager {

    private static final String RECEIPT_PATH = "receipts/";

    public static void saveReceipt(Store store, Receipt receipt) {
        File directory = new File(RECEIPT_PATH + store.getName());
        if (!directory.exists()) {
            directory.mkdirs();
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd-hhmmss");
        String filename = receipt.transactionDate().format(dateTimeFormatter);
        String filePath = RECEIPT_PATH + store.getName() + "/" + filename + ".txt";

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))) {
            bufferedWriter.append(String.format("""
                    ======================== RECEIPT ========================
                                        %s
                                    %s
                    %s
                    =========================================================
                    
                    %s
                    ======================== End of Receipt ========================
                    """, store.getName(), store.getAddress(), receipt.transactionDate(), receipt.order()));

            System.out.println("Receipt Successfully created!");
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
