package com.wallstreet.wallstreetxchange.models.util;

import org.json.JSONObject;

public class StockCalcOperations {

    public JSONObject getStockUpdateCalc(JSONObject oldTransaction, JSONObject currentTransaction) {
        System.out.println(oldTransaction.toString());
        System.out.println(currentTransaction.toString());
        double oldAvgPrice = oldTransaction.getDouble("transactionPrice");
        int oldQuantity = oldTransaction.getInt("quantity");
        double currentAvgPrice = currentTransaction.getDouble("transactionPrice");
        int currentQuantity = currentTransaction.getInt("quantity");

        double avgPrice = calculateAveragePrice(oldAvgPrice, currentAvgPrice, oldQuantity, currentQuantity);
        double avgQuantity = calculateAverageQuantity(oldQuantity, currentQuantity,
                currentTransaction.getBoolean("transactionType"));

        if (avgQuantity != 0) {

            boolean type = true;

            if (avgQuantity <= 0) {
                type = false;
            }
            JSONObject result = new JSONObject();

            result.put("price", avgPrice);
            result.put("quantity", avgQuantity);
            result.put("type", type);

            return result;
        }
        return null;
    }

    private double calculateAveragePrice(double oldAvgPrice, double currentAvgPrice, int oldQuantity,
            int currentQuantity) {
        double oldTotal = oldAvgPrice * oldQuantity;
        double currentTotal = currentAvgPrice * currentQuantity;
        double newTotal = oldTotal + currentTotal;
        return newTotal / (oldQuantity + currentQuantity);
    }

    private double calculateAverageQuantity(int oldQuantity, int currentQuantity, boolean transactionType) {
        return transactionType ? oldQuantity + currentQuantity : oldQuantity - currentQuantity;
    }
}
