package com.lucky.currencyexchange.exchangeRateDataAPI.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucky.currencyexchange.exchangeRateDataAPI.dtos.ConversionResultDTO;
import com.lucky.currencyexchange.transaction.dtos.TransactionDTO;
import com.lucky.currencyexchange.transaction.enums.Currency;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@NoArgsConstructor
public class ExchangeRateDataAPIClient {

    private static final HttpClient client = HttpClient.newHttpClient();
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final String API_URL = "https://api.apilayer.com/exchangerates_data";
    private static final String CONVERT_ENDPOINT = "convert";

    public static TransactionDTO convert(final TransactionDTO transactionDTO) throws IOException, InterruptedException {
        final ConversionResultDTO conversionResultDTO = ExchangeRateDataAPIClient.convert(transactionDTO.getOriginalValue(), transactionDTO.getInitialCurrency(),
                transactionDTO.getFinalCurrency());

        transactionDTO.setConversionRate(conversionResultDTO.getInfo().getRate());
        return transactionDTO;
    }

    public static ConversionResultDTO convert(final double value, final Currency originalCurrency, final Currency targetCurrency) throws IOException, InterruptedException {
        final String path = String.format("to=%s&from=%s&amount=%s", targetCurrency.apiValue, originalCurrency.apiValue, value);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format("%s/%s?%s", API_URL, CONVERT_ENDPOINT, path)))
                .header("apikey", "jHjL7NEQHgvIkYaukXOjAQVV3mlke5im")
                .method("GET", HttpRequest.BodyPublishers.noBody()).build();

        final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return mapper.readValue(response.body(), ConversionResultDTO.class);
    }

}
