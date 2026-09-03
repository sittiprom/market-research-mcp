# MarketLens AI — Frontend

React + Vite frontend for your `market-research-mcp` client app. Talks to
`POST /api/ask` on your Spring Boot `client` app (default `http://localhost:8080`).

## Setup

```bash
npm install
npm run dev
```

Opens at `http://localhost:5173`.

## Important: enable CORS on the backend

Your `ChatController` currently has no CORS configuration, so browser
requests from `http://localhost:5173` (Vite's dev server) will be blocked
by the backend's default same-origin policy. Add one of these to your
`client` Spring Boot app before testing:

**Option A — quick, on the controller:**
```java
@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ChatController {
    // ...
}
```

**Option B — global config (better if you add more controllers later):**
```java
@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins("http://localhost:5173")
                        .allowedMethods("GET", "POST");
            }
        };
    }
}
```

## Pointing at a different backend URL

By default the app calls `http://localhost:8080`. To override, create a
`.env` file in this folder:

```
VITE_API_BASE_URL=http://localhost:8080
```

## Known backend data quirks (worth fixing server-side)

- `priceHistory` entries have no date/datetime field, so the chart labels
  points as "T-5", "T-4", ... "Now" instead of real dates. Add a `date`
  field to `PriceTarget` (client) / the tool response to fix this properly.
- `changePercent` comes back as a raw fraction (e.g. `-0.0523`), not a
  pre-multiplied percentage — the frontend multiplies by 100 when
  displaying it (`StockReportCard.jsx`, `fmtPercent`).
- Non-stock questions (e.g. "convert 5000 USD to THB") still get forced
  into the `ChatResponse` schema since `/api/ask` always calls
  `.entity(ChatResponse.class)`. The frontend falls back to showing
  `keyTakeaways` as plain text when `ticker` is missing, but a dedicated
  response type per query type would be a cleaner long-term fix.

## Project structure

```
src/
  api.js                    — fetch wrapper for POST /api/ask
  App.jsx                   — chat state + layout
  components/
    Header.jsx               — top bar
    Hero.jsx                 — gradient hero + quick-start tags
    ChatThread.jsx            — message list
    ChatInput.jsx             — input bar
    StockReportCard.jsx       — structured report card
    PriceChart.jsx            — recharts line chart
    Sidebar.jsx                — quick examples / popular stocks / market status
```

`Popular Stocks` and `Market Status` are static placeholder data for now —
wire them to real MCP quote calls once you have a lightweight endpoint for
quick lookups that doesn't need to go through the full chat flow.
