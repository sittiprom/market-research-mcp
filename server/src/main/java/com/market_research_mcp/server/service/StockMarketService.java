package com.market_research_mcp.server.service;

import com.market_research_mcp.server.client.StockMarketClient;
import com.market_research_mcp.server.model.response.EodResponse;
import com.market_research_mcp.server.model.response.PriceResponse;
import com.market_research_mcp.server.model.response.PriceTargetResponse;
import com.market_research_mcp.server.model.response.QuoteResponse;
import com.market_research_mcp.server.model.response.RecommendationResponse;
import com.market_research_mcp.server.model.response.TimeSeriesResponse;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StockMarketService {

    @Value("${TWELVE_DATA_API_KEY}")
    private String twelevDataApiKey;

    private StockMarketClient client;

    private static final Logger log = LoggerFactory.getLogger(StockMarketService.class);

    public StockMarketService(StockMarketClient client) {
        this.client = client;
    }

    @McpTool(description = "Get historical stock prices for a symbol.\n" +
            "\n" +
            "Use this tool when the user asks about:\n" +
            "- historical performance\n" +
            "- stock trends\n" +
            "- price changes over a period\n" +
            "- reports for previous days, weeks, or months\n" +
            "\n" +
            "Do not use this tool when the user only asks for the latest stock price.")
    public TimeSeriesResponse getTimeSeries(
            @McpToolParam(description = "Symbol ticker of the instrument. E.g. AAPL, EUR/USD, ETH/BTC, ...") String symbol,
            @McpToolParam(description = "Interval between two consecutive points in time series.Supports: 1min, 5min," +
                    " 15min, 30min, 45min, 1h, 2h, 4h, 8h, 1day, 1week, 1month") String interval
    ) {

        try {
            TimeSeriesResponse timeSeriesResponse = client.getTimeSeries(symbol, interval, twelevDataApiKey);
            return timeSeriesResponse;
        } catch (FeignException e) {
            int statusCode = e.status();
            log.error("Feign call failed with status: {}", statusCode);
            throw e;
        } catch (Exception e) {
            log.error("Exception occurred while calling StockMarketService", e);
            throw e;
        }
    }

    @McpTool(description = "The quote endpoint provides real-time data for a selected financial instrument, returning " +
            "essential information such as the latest price, open, high, low, close, volume, and price change. This endpoint " +
            "is ideal for users needing up-to-date market data to track price movements and trading activity for specific stocks, " +
            "ETFs, or other securities.")
    public QuoteResponse getQuote(@McpToolParam(description = "Symbol ticker of the instrument. E.g. AAPL, EUR/USD, ETH/BTC, ...") String symbol) {
        try {
            QuoteResponse quoteResponse = client.getQuote(symbol, twelevDataApiKey);
            return quoteResponse;
        } catch (FeignException e) {
            int statusCode = e.status();
            log.error("Feign call failed with status: {}", statusCode);
            throw e;
        } catch (Exception e) {
            log.error("Exception occurred while calling StockMarketService", e);
            throw e;
        }

    }

    @McpTool(description = """
    Get the latest available price for a stock.
    Use this tool for questions about:
    - current stock price
    - how much a stock costs
    - calculations involving buying shares
    Do not use this for historical performance reports.
""")
    public PriceResponse getPrice(@McpToolParam(description = "Symbol ticker of the instrument. Example : AAPL") String symbol) {
        try {

            PriceResponse priceResponse = client.getPrice(symbol, twelevDataApiKey);
            return priceResponse;
        } catch (FeignException e) {
            int statusCode = e.status();
            log.error("Feign call failed with status: {}", statusCode);
            throw e;
        } catch (Exception e) {
            log.error("Exception occurred while calling StockMarketService", e);
            throw e;
        }


    }

    @McpTool(description = "The End of Day (EOD) Prices endpoint provides the closing price and other relevant metadata " +
            "for a financial instrument at the end of a trading day. This endpoint is useful for retrieving daily historical " +
            "data for stocks, ETFs, or other securities, allowing users to track performance over time and compare daily market movements.")
    public EodResponse getStocks(@McpToolParam(description = "Symbol ticker of the instrument. Example : AAPL")
                                 String symbol) {
        try {
            EodResponse eodResponse = client.getSymbols(symbol);
            return eodResponse;
        } catch (FeignException e) {
            int statusCode = e.status();
            log.error("Feign call failed with status: {}", statusCode);
            throw e;
        } catch (Exception e) {
            log.error("Exception occurred while calling StockMarketService", e);
            throw e;
        }

    }

    @McpTool(description = "The recommendations endpoint provides a summary of analyst opinions for a specific stock, " +
            "delivering an average recommendation categorized as Strong Buy, Buy, Hold, or Sell. It also includes a numerical " +
            "recommendation score, offering a quick overview of market sentiment based on expert analysis.")
    public RecommendationResponse getRecommendations(@McpToolParam(description = "Symbol ticker of the instrument. " +
            "Example : AAPL.Filter by symbol")
                                                     String symbol) {
        try {
            RecommendationResponse response = client.getRecommendations(symbol, twelevDataApiKey);
            return response;
        } catch (FeignException e) {
            int statusCode = e.status();
            log.error("Feign call failed with status: {}", statusCode);
            throw e;
        } catch (Exception e) {
            log.error("Exception occurred while calling StockMarketService", e);
            throw e;
        }

    }

    @McpTool(description = "The recommendations endpoint provides a summary of analyst opinions for a specific stock, " +
            "delivering an average recommendation categorized as Strong Buy, Buy, Hold, or Sell. It also includes a numerical recommendation score, offering a quick overview of market sentiment based on expert analysis.")
    public PriceTargetResponse getPriceTarget(@McpToolParam(description = "Symbol ticker of the instrument." +
            "Example : AAPL") String symbol){
        try {
            PriceTargetResponse priceTargetResponse = client.getPriceTarget(symbol, twelevDataApiKey);
            return priceTargetResponse;
        }catch (FeignException e) {
            int statusCode = e.status();
            log.error("Feign call failed with status: {}", statusCode);
            throw e;
        } catch (Exception e) {
            log.error("Exception occurred while calling StockMarketService", e);
            throw e;
        }
    }

}
