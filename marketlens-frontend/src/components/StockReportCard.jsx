import { ArrowUp, ArrowDown, Sparkles, Copy, Download, ThumbsUp, ThumbsDown } from 'lucide-react'
import PriceChart from './PriceChart.jsx'

function fmtMoney(n) {
  if (n === null || n === undefined || Number.isNaN(Number(n))) return '—'
  return `$${Number(n).toLocaleString(undefined, { minimumFractionDigits: 2, maximumFractionDigits: 2 })}`
}

// Backend currently returns changePercent as a raw fraction (e.g. -0.0523
// for -5.23%), not a pre-multiplied percentage. Adjust here if that changes.
function fmtPercent(fraction) {
  if (fraction === null || fraction === undefined || Number.isNaN(Number(fraction))) return '—'
  return `${(fraction * 100).toFixed(2)}%`
}

function fmtVolume(v) {
  const n = Number(v)
  if (Number.isNaN(n)) return v ?? '—'
  if (n >= 1_000_000) return `${(n / 1_000_000).toFixed(1)}M`
  if (n >= 1_000) return `${(n / 1_000).toFixed(1)}K`
  return String(n)
}

export default function StockReportCard({ report }) {
  const {
    ticker,
    companyName,
    startPrice,
    latestPrice,
    changePercent,
    periodHigh,
    periodLow,
    priceHistory,
    tradingActivity,
    keyTakeaways
  } = report

  const isUp = Number(changePercent) >= 0

  return (
    <div className="report-card">
      <div className="report-header">
        <div className="report-header-left">
          <div className="ticker-badge">{ticker || '—'}</div>
          <div>
            <div className="report-title">
              {companyName || ticker || 'Report'} {ticker ? `(${ticker})` : ''}
            </div>
            <div className="report-subtitle">Market report</div>
          </div>
        </div>
        {changePercent !== undefined && changePercent !== null && (
          <div>
            <div className={`change-chip ${isUp ? 'up' : 'down'}`}>
              {isUp ? <ArrowUp size={14} /> : <ArrowDown size={14} />}
              {fmtPercent(changePercent)}
            </div>
            <span className="change-chip-sub">vs. period start</span>
          </div>
        )}
      </div>

      <div className="stat-grid">
        <div className="stat-cell">
          <div className="stat-label">Starting Price</div>
          <div className="stat-value">{fmtMoney(startPrice)}</div>
        </div>
        <div className="stat-cell">
          <div className="stat-label">Latest Price</div>
          <div className="stat-value">{fmtMoney(latestPrice)}</div>
        </div>
        <div className="stat-cell">
          <div className="stat-label">Period High</div>
          <div className="stat-value up">{fmtMoney(periodHigh)}</div>
        </div>
        <div className="stat-cell">
          <div className="stat-label">Period Low</div>
          <div className="stat-value down">{fmtMoney(periodLow)}</div>
        </div>
      </div>

      <div className="report-body">
        <div className="chart-cell">
          <div className="chart-title">Price (USD)</div>
          <PriceChart priceHistory={priceHistory} />
        </div>
        <div className="activity-cell">
          <div className="activity-title">Trading Activity</div>
          {tradingActivity ? (
            <>
              <div className="activity-row">
                <span className="label">Open</span>
                <span className="value">{fmtMoney(tradingActivity.open)}</span>
              </div>
              <div className="activity-row">
                <span className="label">High</span>
                <span className="value">{fmtMoney(tradingActivity.high)}</span>
              </div>
              <div className="activity-row">
                <span className="label">Low</span>
                <span className="value">{fmtMoney(tradingActivity.low)}</span>
              </div>
              <div className="activity-row">
                <span className="label">Close</span>
                <span className="value">
                  {fmtMoney(tradingActivity.close)}
                  {tradingActivity.datetime && <span className="sub">{tradingActivity.datetime}</span>}
                </span>
              </div>
              <div className="activity-row">
                <span className="label">Volume</span>
                <span className="value">{fmtVolume(tradingActivity.volume)}</span>
              </div>
            </>
          ) : (
            <div className="activity-row">
              <span className="label">No trading activity returned.</span>
            </div>
          )}
        </div>
      </div>

      {keyTakeaways?.length > 0 && (
        <div className="takeaways">
          <div className="takeaways-title">
            <Sparkles size={14} color="#7c5cf5" /> Key Takeaways
          </div>
          <ul>
            {keyTakeaways.map((point, i) => (
              <li key={i}>{point}</li>
            ))}
          </ul>
        </div>
      )}

      <div className="report-footer">
        <button className="icon-btn" style={{ width: 32, height: 32 }} aria-label="Copy">
          <Copy size={14} />
        </button>
        <button className="icon-btn" style={{ width: 32, height: 32 }} aria-label="Download">
          <Download size={14} />
        </button>
        <button className="icon-btn" style={{ width: 32, height: 32 }} aria-label="Good response">
          <ThumbsUp size={14} />
        </button>
        <button className="icon-btn" style={{ width: 32, height: 32 }} aria-label="Bad response">
          <ThumbsDown size={14} />
        </button>
      </div>
    </div>
  )
}
