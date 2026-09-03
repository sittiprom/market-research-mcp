import { Sparkles, Github, SunMedium } from 'lucide-react'

export default function Header() {
  return (
    <div className="top-bar">
      <div className="brand">
        <div className="brand-mark">
          <Sparkles size={20} />
        </div>
        <div>
          <div className="brand-name">MarketLens AI</div>
          <div className="brand-sub">AI Market Research Assistant</div>
        </div>
      </div>
      <div className="top-bar-actions">
        <div className="pill-badge">
          <Sparkles size={13} /> MCP Powered
        </div>
        <a
          className="icon-btn"
          href="https://github.com"
          target="_blank"
          rel="noreferrer"
          aria-label="View source"
        >
          <Github size={17} />
        </a>
        <button className="icon-btn" aria-label="Toggle theme">
          <SunMedium size={17} />
        </button>
      </div>
    </div>
  )
}
