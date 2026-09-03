import { useState } from 'react'
import { ArrowUp, Sparkles } from 'lucide-react'

export default function ChatInput({ onSend, disabled }) {
  const [value, setValue] = useState('')

  function submit(e) {
    e.preventDefault()
    const trimmed = value.trim()
    if (!trimmed || disabled) return
    onSend(trimmed)
    setValue('')
  }

  return (
    <form className="chat-input-bar" onSubmit={submit}>
      <Sparkles size={16} color="#7c5cf5" style={{ flexShrink: 0 }} />
      <input
        value={value}
        onChange={(e) => setValue(e.target.value)}
        placeholder="Ask anything about stocks or currency..."
        disabled={disabled}
      />
      <button className="send-btn" type="submit" disabled={disabled || !value.trim()} aria-label="Send">
        <ArrowUp size={18} />
      </button>
    </form>
  )
}
