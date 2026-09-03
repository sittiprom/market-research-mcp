package com.market_research_mcp.client.model.response;

import java.math.BigDecimal;
import java.util.List;

public record ChatResponse(
        String ticker, String companyName,
        BigDecimal startPrice, BigDecimal latestPrice,
        BigDecimal changePercent, BigDecimal periodHigh, BigDecimal periodLow,
        List<PriceTarget> priceHistory,
        TradingActivity tradingActivity,
        List<String> keyTakeaways
) {
}
