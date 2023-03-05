package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {

        String stringJson = getStringFromURL("https://api.monobank.ua/bank/currency", "UTF-8");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Currency[] currenciesArray = gson.fromJson(stringJson, Currency[].class);

        Map<Integer, String> currenciesMap = new HashMap<>();
        currenciesMap.put(840, "USD");
        currenciesMap.put(978, "EUR");
        currenciesMap.put(980, "UAH");

        showExchangeRates(currenciesArray, currenciesMap);
    }

    public static String getStringFromURL(String spec, String code) throws IOException {
        URL url = new URL(spec);
        URLConnection connection = (HttpURLConnection) url.openConnection();
        String result = "";
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), code))) {
            String temp = "";
            for (; (temp = br.readLine()) != null; ) {
                result += temp + System.lineSeparator();
            }
        }

        return result;
    }

    public static void showExchangeRates(Currency[] currenciesArray, Map<Integer, String> currenciesMap) {

        for (Currency currency : currenciesArray) {
            if (currency.getRateSell() != 0.0) {
                System.out.println(currenciesMap.get(currency.getCurrencyCodeA()) + "/" + currenciesMap.get(currency.getCurrencyCodeB()) + ":");
                System.out.println("Buy: " + currency.getRateBuy());
                System.out.println("Sell: " + currency.getRateSell());
                System.out.println();
            }
        }

    }

}
