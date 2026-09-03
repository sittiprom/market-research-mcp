package com.market_research_mcp.server.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeSeriesRequest {
    private String symbol;
    private String interval;
    private String apikey;
}
