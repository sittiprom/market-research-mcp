package com.market_research_mcp.client.model.response;

public record TradingActivity(
        String datetime,
        String open,
        String high,
        String low,
        String close,
        String volume
) {
}
