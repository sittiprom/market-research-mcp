import { TrendingUp, Scale, RefreshCw } from 'lucide-react'

const TAGS = [
  { icon: TrendingUp, label: 'Stock Reports', prompt: 'Give me a 2-week report on Apple stock performance.' },
  { icon: Scale, label: 'Compare Stocks', prompt: 'Compare Apple and Microsoft over the last month.' },
  { icon: RefreshCw, label: 'Currency Conversion', prompt: 'Convert 5,000 USD to THB.' }
]

export default function Hero({ onPick }) {
  return (
    <div className="hero">
      <h1>
        Ask. Explore. Understand <span>the market.</span>
      </h1>
      <p>
        Get real-time data, historical reports, comparisons, and currency
        conversions — all in one place.
      </p>
      <div className="hero-tags">
        {TAGS.map(({ icon: Icon, label, prompt }) => (
          <button key={label} className="hero-tag" onClick={() => onPick(prompt)}>
            <Icon size={15} /> {label}
          </button>
        ))}
      </div>
    </div>
  )
}
