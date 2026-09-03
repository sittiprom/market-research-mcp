package com.market_research_mcp.server.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PriceTargetResponse(
        Meta meta,
        @JsonProperty("price_target") PriceTarget priceTarget,
        String status
) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Meta(
            String symbol,
            String name,
            String currency,
            @JsonProperty("exchange_timezone") String exchangeTimezone,
            String exchange,
            @JsonProperty("mic_code") String micCode,
            String type
    ) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record PriceTarget(
            double high,
            double median,
            double low,
            double average,
            double current,
            String currency
    ) {}
}
