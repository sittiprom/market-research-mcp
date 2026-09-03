import { useRef, useState, useEffect } from 'react'

import Header from './components/Header.jsx'
import Hero from './components/Hero.jsx'
import ChatThread, { timeNow } from './components/ChatThread.jsx'
import ChatInput from './components/ChatInput.jsx'
import Sidebar from './components/Sidebar.jsx'
import { askQuestion } from './api.js'

let idCounter = 0

function nextId() {
  idCounter += 1
  return idCounter
}

export default function App() {
  const [messages, setMessages] = useState([])
  const [isLoading, setIsLoading] = useState(false)
  const scrollRef = useRef(null)

  useEffect(() => {
    if (scrollRef.current) {
      scrollRef.current.scrollTop = scrollRef.current.scrollHeight
    }
  }, [messages, isLoading])

  async function handleSend(question) {
    setMessages((prev) => [
      ...prev,
      {
        id: nextId(),
        role: 'user',
        text: question,
        time: timeNow()
      }
    ])

    setIsLoading(true)

    try {
      const answer = await askQuestion(question)

      setMessages((prev) => [
        ...prev,
        {
          id: nextId(),
          role: 'assistant',
          text: answer,
          time: timeNow()
        }
      ])
    } catch (err) {
      setMessages((prev) => [
        ...prev,
        {
          id: nextId(),
          role: 'assistant',
          time: timeNow(),
          error: `Couldn't reach MarketLens: ${err.message}. Make sure the client app is running on http://localhost:8080.`
        }
      ])
    } finally {
      setIsLoading(false)
    }
  }

  return (
    <div className="app-shell">
      <Header />

      <Hero onPick={handleSend} />

      <div className="workspace">
        <div className="chat-panel">
          <ChatThread
            messages={messages}
            isLoading={isLoading}
            scrollRef={scrollRef}
          />

          <ChatInput
            onSend={handleSend}
            disabled={isLoading}
          />
        </div>

        <Sidebar onPick={handleSend} />
      </div>
    </div>
  )
}