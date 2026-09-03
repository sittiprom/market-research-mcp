package com.market_research_mcp.server.client;

import com.market_research_mcp.server.model.response.ExchangeRateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "exchange-rate-service", url = "https://api.frankfurter.dev/v2")
public interface ExchangeClient {

    @GetMapping("/rate/{base}/{quote}")
    ExchangeRateResponse getExchangeRate(
            @PathVariable(value = "base") String base,
            @PathVariable(value = "quote")String quote
    );
}
