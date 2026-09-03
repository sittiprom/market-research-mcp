import { Sparkles, Flame, Activity, ChevronRight } from 'lucide-react'

const EXAMPLES = [
  'Give me a 2-week report on Apple.',
  'Compare Apple and Microsoft over the last month.',
  'If I invested 10,000 CNY in Apple two weeks ago, what is it worth today?',
  'Convert 5,000 USD to THB and tell me how many Apple shares I can buy.',
  'Show me the top 5 tech stocks by market cap.'
]

// Static placeholders for now — wire these to real MCP quote/list tools
// (e.g. a getQuote call per ticker) once you have a lightweight endpoint
// for it, so this panel doesn't have to go through the full chat flow.
const POPULAR_STOCKS = [
  { ticker: 'AAPL', name: 'Apple', price: '$184.70', delta: '+2.14%', up: true },
  { ticker: 'MSFT', name: 'Microsoft', price: '$415.32', delta: '+1.03%', up: true },
  { ticker: 'NVDA', name: 'Nvidia', price: '$138.21', delta: '+3.76%', up: true },
  { ticker: 'AMZN', name: 'Amazon', price: '$178.44', delta: '-0.92%', up: false },
  { ticker: 'TSLA', name: 'Tesla', price: '$262.13', delta: '+1.58%', up: true }
]

export default function Sidebar({ onPick }) {
  return (
    <div className="sidebar">
      <div className="side-card">
        <h3>
          <Sparkles size={16} color="#7c5cf5" /> Quick Examples
        </h3>
        {EXAMPLES.map((ex) => (
          <div className="example-row" key={ex} onClick={() => onPick(ex)}>
            {ex}
            <ChevronRight size={15} color="#a39ec2" style={{ flexShrink: 0 }} />
          </div>
        ))}
      </div>

      <div className="side-card">
        <h3>
          <Flame size={16} color="#ee7fb0" /> Popular Stocks
        </h3>
        {POPULAR_STOCKS.map((s) => (
          <div
            className="stock-row"
            key={s.ticker}
            style={{ cursor: 'pointer' }}
            onClick={() => onPick(`Give me a report on ${s.ticker}.`)}
          >
            <div className="stock-logo">{s.ticker.slice(0, 2)}</div>
            <div>
              <div className="stock-ticker">{s.ticker}</div>
              <div className="stock-name-mini">{s.name}</div>
            </div>
            <div className="stock-price">
              <span className="price">{s.price}</span>
              <span className={`delta ${s.up ? 'up' : 'down'}`}>{s.delta}</span>
            </div>
          </div>
        ))}
      </div>

      <div className="side-card">
        <h3>
          <Activity size={16} color="#7c5cf5" /> Market Status
        </h3>
        <div className="market-status-row">
          <div>
            <div style={{ fontSize: 13, fontWeight: 600 }}>US Market</div>
            <div className="status-dot-row">
              <span className="status-dot" /> Open
            </div>
          </div>
          <div style={{ textAlign: 'right' }}>
            <div style={{ fontSize: 11.5, color: '#6b6784' }}>Closes in</div>
            <div style={{ fontWeight: 700, color: '#7c5cf5', fontSize: 13.5 }}>2h 18m</div>
          </div>
        </div>
      </div>
    </div>
  )
}
