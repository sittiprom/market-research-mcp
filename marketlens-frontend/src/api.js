// Points at your Spring Boot "client" app (ChatController), which itself
// calls the market-server MCP tools. Defaults to the app's default port (8080).
const API_BASE = import.meta.env.VITE_API_BASE_URL || ''
const CONVERSATION_ID_KEY = 'marketlens-conversation-id'

function generateConversationId() {
  return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
    const r = Math.random() * 16 | 0
    const v = c === 'x' ? r : (r & 0x3) | 0x8
    return v.toString(16)
  })
}

function getConversationId() {
  let conversationId = sessionStorage.getItem(CONVERSATION_ID_KEY)

  if (!conversationId) {
    conversationId = generateConversationId()
    sessionStorage.setItem(CONVERSATION_ID_KEY, conversationId)
  }

  return conversationId
}




/**
 * Sends a question to the MarketLens AI backend
 * and returns the assistant response as plain text.
 */
export async function askQuestion(question) {
  const res = await fetch(`${API_BASE}/api/ask`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({
      question,
      conversationId: getConversationId()
    })
  })

  if (!res.ok) {
    const text = await res.text().catch(() => '')
    throw new Error(
      `Request failed (${res.status}): ${text || res.statusText}`
    )
  }

  return res.text()
}
