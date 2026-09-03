package com.market_research_mcp.server.model.response;

import java.math.BigDecimal;

public record SharePurchase(
        BigDecimal availableAmount,
        BigDecimal sharePrice,
        String currency,
        int wholeShares,
        BigDecimal totalCost,
        BigDecimal remainingAmount
) {}
