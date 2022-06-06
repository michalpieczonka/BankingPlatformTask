package com.recruitment.task.infrastructure.configuration;

import com.opencsv.CSVReader;
import com.recruitment.task.TaskApplication;
import com.recruitment.task.domain.transaction.CurrencyCalculator;
import com.recruitment.task.domain.transaction.CurrencyCalculatorImpl;
import com.recruitment.task.domain.transaction.CurrencyType;
import com.recruitment.task.domain.transaction.ExchangeRate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Configuration
@ComponentScan(basePackageClasses = TaskApplication.class)
public class CurrencyConfiguration{
    private static final String CURRENCIES_FILE_NAME = "currencys.csv";

    @Bean
    public CurrencyCalculator currencyCalculator(){
        return new CurrencyCalculatorImpl(exchangeRates());
    }

    @Bean
    public List<ExchangeRate> exchangeRates() {
        List<ExchangeRate> exchangeRates = new ArrayList<>();
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(CURRENCIES_FILE_NAME)).getFile());

        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            List<String[]> currencys = reader.readAll();
            currencys.forEach(currency -> exchangeRates.add(createExchangeRate(currency)));
        } catch (Exception e) {}

        log.info("The exchange rates were correctly loaded");
        return exchangeRates;
    }

    private ExchangeRate createExchangeRate(String[] metaData){
        ExchangeRate exchangeRate = ExchangeRate.builder()
                .sourceCurrency(CurrencyType.valueOf(metaData[0]))
                .targetCurrency(CurrencyType.valueOf(metaData[1]))
                .amount(BigDecimal.valueOf(Double.parseDouble(metaData[2])))
                .build();
        return exchangeRate;
    }
}
