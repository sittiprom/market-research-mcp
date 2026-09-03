package com.market_research_mcp.server.service;

import com.market_research_mcp.server.client.ExchangeClient;
import com.market_research_mcp.server.model.response.CurrencyConversionResponse;
import com.market_research_mcp.server.model.response.ExchangeRateResponse;
import com.market_research_mcp.server.model.response.SharePurchase;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class ExchangeService {

    private static final Logger log = LoggerFactory.getLogger(ExchangeService.class);

    private final ExchangeClient client;

    public ExchangeService(ExchangeClient client) {
        this.client = client;
    }

    @McpTool(description = """
        Convert a monetary amount from one currency to another.

        Use this tool when the user's currency is different
        from the currency required by another tool.

        For purchasing US stocks, convert the user's money
        to USD before calculating how many shares can be purchased.

        Example:
        45,000 CAD -> USD
        """)
    public CurrencyConversionResponse convertCurrency(
            @McpToolParam(description = "The currency code to exchange from. Please provide currency code such as USD") String base,
            @McpToolParam(description = "The currency code to exchange to. Please provide currency code such as EUR") String quote,
            @McpToolParam(description ="" ) BigDecimal amount

    ) {
        try {
            ExchangeRateResponse exchangeRate = client.getExchangeRate(base,quote);

            BigDecimal convertedAmount = amount.multiply(BigDecimal.valueOf(exchangeRate.rate()));

            return new CurrencyConversionResponse(
                    base,
                    quote,
                    amount,
                    exchangeRate.rate(),
                    convertedAmount
            );
        }catch (FeignException e) {
            int statusCode = e.status();
            log.error("Feign call failed with status: {}", statusCode);
            throw e;
        } catch (Exception e) {
            log.error("Exception occurred while calling StockMarketService", e);
            throw e;
        }


    }

    @McpTool(description = """
        Calculate how many whole shares can be purchased
        using an amount in USD and a stock price in USD.

        IMPORTANT:
        Both availableAmount and sharePrice MUST be in USD.

        If the user's money is not in USD, first use the currency
        conversion tool to convert the amount to USD before calling
        this tool.

        Example:
        User has 45,000 CAD and wants to buy Apple.
        First convert 45,000 CAD to USD.
        Then get the Apple share price in USD.
        Finally call this tool with both USD values.
        """)
    public SharePurchase calculateShares(
            BigDecimal availableAmount,
            BigDecimal sharePrice) {

        int wholeShares = availableAmount
                .divide(sharePrice, 0, RoundingMode.DOWN)
                .intValue();

        BigDecimal totalCost = sharePrice
                .multiply(BigDecimal.valueOf(wholeShares))
                .setScale(2, RoundingMode.HALF_UP);

        BigDecimal remainingAmount = availableAmount
                .subtract(totalCost)
                .setScale(2, RoundingMode.HALF_UP);

        return new SharePurchase(
                availableAmount,
                sharePrice,
                "USD",
                wholeShares,
                totalCost,
                remainingAmount
        );
    }
}
