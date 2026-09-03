package com.market_research_mcp.server.model.response;



import java.math.BigDecimal;

public record CurrencyConversionResponse(
        String base,
        String quote,
        BigDecimal amount,
        Double rate,
        BigDecimal convertedAmount
) {
}
