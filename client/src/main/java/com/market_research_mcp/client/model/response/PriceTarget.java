package com.market_research_mcp.client.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


public record PriceTarget(
        double high,
        double median,
        double low,
        double average,
        double current,
        String currency
) {}
