package com.market_research_mcp.client.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {

    @Bean
    public ChatClient chatClient(
            ChatClient.Builder builder,
            ChatMemory chatMemory,
            ToolCallbackProvider mcpTools) {

        return builder
                .defaultSystem("""
                You are MarketLens AI, a market research assistant.
                Use the available MCP tools whenever current or historical
                market data or currency exchange data is required.
                Never invent stock prices, exchange rates, or market data.
                Base calculations and conclusions on tool results.
                Keep responses concise and easy to scan.
                Use Markdown headings, short paragraphs, and bullet points
                when they improve readability.
                For historical stock reports, summarize the period.
                Do not list every daily OHLC data point unless the user
                explicitly asks for daily details.

                    Include the most useful metrics such as:
                    - starting price
                    - latest price
                    - percentage change
                    - period high and low
                    - relevant analyst sentiment when available
                
                    You provide market information and research,
                    not personalized financial advice.
    """)


                .defaultTools(mcpTools.getToolCallbacks())
                .build();
    }
}
