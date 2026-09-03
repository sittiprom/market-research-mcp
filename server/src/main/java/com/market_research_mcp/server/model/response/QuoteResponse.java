package com.market_research_mcp.server.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record QuoteResponse(
        String symbol,
        String name,
        String exchange,
        @JsonProperty("mic_code") String micCode,
        String currency,
        String datetime,
        long timestamp,
        @JsonProperty("last_quote_at") long lastQuoteAt,
        String open,
        String high,
        String low,
        String close,
        String volume,
        @JsonProperty("previous_close") String previousClose,
        String change,
        @JsonProperty("percent_change") String percentChange,
        @JsonProperty("average_volume") String averageVolume,
        @JsonProperty("rolling_1d_change") String rolling1dChange,
        @JsonProperty("rolling_7d_change") String rolling7dChange,
        @JsonProperty("rolling_change") String rollingChange,
        @JsonProperty("is_market_open") boolean isMarketOpen,
        @JsonProperty("fifty_two_week") FiftyTwoWeek fiftyTwoWeek,
        @JsonProperty("extended_change") String extendedChange,
        @JsonProperty("extended_percent_change") String extendedPercentChange,
        @JsonProperty("extended_price") String extendedPrice,
        @JsonProperty("extended_timestamp") Long extendedTimestamp
) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record FiftyTwoWeek(
            String low,
            String high,
            @JsonProperty("low_change") String lowChange,
            @JsonProperty("high_change") String highChange,
            @JsonProperty("low_change_percent") String lowChangePercent,
            @JsonProperty("high_change_percent") String highChangePercent,
            String range
    ) {}
}
