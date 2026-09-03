package com.market_research_mcp.server.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RecommendationResponse(
        Meta meta,
        Trends trends,
        double rating,
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
    public record Trends(
            @JsonProperty("current_month") Period currentMonth,
            @JsonProperty("previous_month") Period previousMonth,
            @JsonProperty("2_months_ago") Period twoMonthsAgo,
            @JsonProperty("3_months_ago") Period threeMonthsAgo
    ) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Period(
            @JsonProperty("strong_buy") int strongBuy,
            int buy,
            int hold,
            int sell,
            @JsonProperty("strong_sell") int strongSell
    ) {}
}