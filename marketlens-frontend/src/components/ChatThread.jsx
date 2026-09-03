import { Sparkles } from 'lucide-react'
import ReactMarkdown from 'react-markdown'


function timeNow() {
  return new Date().toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' })
}

export default function ChatThread({ messages, isLoading, scrollRef }) {
  if (messages.length === 0 && !isLoading) {
    return (
      <div className="chat-scroll">
        <div className="chat-empty">
          <div className="chat-empty-icon">
            <Sparkles size={24} />
          </div>
          <div style={{ fontWeight: 700, color: '#1c1a2b', marginBottom: 6 }}>
            Ask about any stock
          </div>
          Try a quick example on the right, or ask your own question below.
        </div>
      </div>
    )
  }

  return (
    <div className="chat-scroll" ref={scrollRef}>
      {messages.map((m) => (
        <div key={m.id} className={`msg-row ${m.role}`}>
          {m.role === 'user' ? (
            <div>
              <div className="user-bubble">{m.text}</div>
              <span className="msg-timestamp">{m.time}</span>
            </div>
          ) : (
            <>
              <div className="avatar">AI</div>
              <div className="assistant-block">
                {m.error && <div className="assistant-error">{m.error}</div>}
                {!m.error && m.text && (
                      <div className="assistant-text">
                        <ReactMarkdown>{m.text}</ReactMarkdown>
                      </div>)
                }
                <span className="msg-timestamp">{m.time}</span>
              </div>
            </>
          )}
        </div>
      ))}

      {isLoading && (
        <div className="msg-row assistant">
          <div className="avatar">AI</div>
          <div className="assistant-block">
            <div className="typing-dots">
              <span /> <span /> <span />
            </div>
          </div>
        </div>
      )}
    </div>
  )
}

export { timeNow }
