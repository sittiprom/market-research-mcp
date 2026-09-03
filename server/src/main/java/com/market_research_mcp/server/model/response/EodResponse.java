package com.market_research_mcp.server.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record EodResponse(
        String datetime,
        String symbol,
        String exchange,
        String currency,
        String close,
        @JsonProperty("mic_code")
        String micCode


) {
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static record SymbolSearchResponse(
                int count,
                List<Data> data,
                String status
        ) {

            @JsonIgnoreProperties(ignoreUnknown = true)
            public record Data(
                    String symbol,
                    String name,
                    String currency,
                    String exchange,
                    @JsonProperty("mic_code") String micCode,
                    String country,
                    String type,
                    @JsonProperty("figi_code") String figiCode,
                    @JsonProperty("cfi_code") String cfiCode,
                    String isin,
                    String cusip,
                    Access access
            ) {}

            @JsonIgnoreProperties(ignoreUnknown = true)
            public record Access(
                    String global,
                    String plan,
                    @JsonProperty("plan_business") String planBusiness
            ) {}
        }
}
