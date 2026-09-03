package com.market_research_mcp.server.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TimeSeriesResponse(
        Meta meta,
        List<Value> values,
        String status
) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Meta(
            String symbol,
            String interval,
            String currency,
            @JsonProperty("exchange_timezone") String exchangeTimezone,
            String exchange,
            @JsonProperty("mic_code") String micCode,
            String type
    ) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Value(
            String datetime,
            String open,
            String high,
            String low,
            String close,
            String volume
    ) {}
}

