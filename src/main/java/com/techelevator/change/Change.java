package com.techelevator.change;

import java.math.BigDecimal;
import java.util.LinkedHashMap;

public class Change {

    private static final Coin[] coins = new Coin[]{
            new Quarter(), new Dime(), new Nickel()};

    public String makeChange(BigDecimal money) {
        StringBuilder coinReturnString = new StringBuilder();
        int amount = (int) (money.doubleValue() * 100);
        LinkedHashMap<Coin, Integer> change = new LinkedHashMap<>();

        for (Coin coin : coins) {
            if (amount <= 0) {
                break;
            }
            int cnt = amount / coin.getValue();
            if (cnt > 0) {
                amount = amount % (coin.getValue() * cnt);
                change.put(coin, cnt);
            }
        }

        for (Coin coin : change.keySet()) {
            String isPlural = (change.get(coin) > 1) ? "s " : " ";
            coinReturnString.append(change.get(coin)).append(" ").append(coin.getName()).append(isPlural);

        }
        coinReturnString.append("returned.");
        return coinReturnString.toString().trim();
    }

}