// Points at your Spring Boot "client" app (ChatController), which itself
// calls the market-server MCP tools. Defaults to the app's default port (8080).
const API_BASE = import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080'

/**
 * Sends a question to the MarketLens AI backend
 * and returns the assistant response as plain text.
 */
export async function askQuestion(question) {
  const res = await fetch(`${API_BASE}/api/ask`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ question })
  })

  if (!res.ok) {
    const text = await res.text().catch(() => '')
    throw new Error(`Request failed (${res.status}): ${text || res.statusText}`)
  }

  return res.text()
}
