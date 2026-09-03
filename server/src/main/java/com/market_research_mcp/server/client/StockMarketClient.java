package com.market_research_mcp.server.client;

import com.market_research_mcp.server.model.response.EodResponse;
import com.market_research_mcp.server.model.response.PriceResponse;
import com.market_research_mcp.server.model.response.PriceTargetResponse;
import com.market_research_mcp.server.model.response.QuoteResponse;
import com.market_research_mcp.server.model.response.RecommendationResponse;
import com.market_research_mcp.server.model.response.TimeSeriesResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "stockmarket-service", url = "https://api.twelvedata.com")
public interface StockMarketClient {

    @GetMapping(value = "/time_series")
    TimeSeriesResponse getTimeSeries(
            @RequestParam(value = "symbol") String symbol,
            @RequestParam(value = "interval") String interval,
            @RequestParam(value = "apikey") String apikey);

    @GetMapping("/quote")
    QuoteResponse getQuote(
            @RequestParam(value = "symbol") String symbol,
            @RequestParam(value = "apikey") String apikey);

    @GetMapping("/price")
    PriceResponse getPrice(
            @RequestParam(value = "symbol") String symbol,
            @RequestParam(value = "apikey") String apikey);

    @GetMapping("/stocks")
    EodResponse getSymbols(@RequestParam(value = "apikey") String symbol);

    @GetMapping("recommendations")
    RecommendationResponse getRecommendations(
            @RequestParam(value = "symbol") String symbol,
            @RequestParam(value = "apikey") String apikey);

    @GetMapping("/price_target")
    PriceTargetResponse getPriceTarget(
            @RequestParam(value = "symbol") String symbol,
            @RequestParam(value = "apikey") String apikey);

}
