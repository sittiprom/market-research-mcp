import {
  ResponsiveContainer,
  LineChart,
  Line,
  XAxis,
  YAxis,
  Tooltip,
  CartesianGrid
} from 'recharts'

// The backend's priceHistory entries don't currently include a date field
// (see ChatResponse/PriceTarget), so points are labelled by relative
// position rather than an actual calendar date. Once the API adds a
// `date`/`datetime` field per entry, swap `label` below for that value.
export default function PriceChart({ priceHistory = [] }) {
  if (!priceHistory.length) {
    return <div className="chart-title">No price history returned for this query.</div>
  }

  // API returns most-recent-first; chart reads left-to-right oldest -> newest.
  const points = [...priceHistory].reverse().map((p, i, arr) => ({
    label: i === arr.length - 1 ? 'Now' : `T-${arr.length - 1 - i}`,
    price: p.current
  }))

  return (
    <div style={{ width: '100%', height: 210 }}>
      <ResponsiveContainer>
        <LineChart data={points} margin={{ top: 6, right: 10, left: -18, bottom: 0 }}>
          <CartesianGrid stroke="#ebe8f7" vertical={false} />
          <XAxis
            dataKey="label"
            tick={{ fontSize: 11, fill: '#6b6784' }}
            axisLine={{ stroke: '#ebe8f7' }}
            tickLine={false}
          />
          <YAxis
            tick={{ fontSize: 11, fill: '#6b6784' }}
            axisLine={false}
            tickLine={false}
            domain={['auto', 'auto']}
          />
          <Tooltip
            formatter={(value) => [`$${Number(value).toFixed(2)}`, 'Price']}
            contentStyle={{ borderRadius: 10, border: '1px solid #ebe8f7', fontSize: 12 }}
          />
          <Line
            type="monotone"
            dataKey="price"
            stroke="#7c5cf5"
            strokeWidth={2.5}
            dot={{ r: 3, fill: '#7c5cf5', strokeWidth: 0 }}
            activeDot={{ r: 5 }}
          />
        </LineChart>
      </ResponsiveContainer>
    </div>
  )
}
