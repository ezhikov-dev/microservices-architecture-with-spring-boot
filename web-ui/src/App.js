import React from 'react'
import styled from 'styled-components'
import theme from './theme'
import transactionApi from './api/transactionApi'

const Section = styled.div`
  background-color: ${theme.palette.white};
  box-shadow: ${theme.shadows['1']};
  margin: 8px;
  border-radius: 4px;
`

const Table = styled.table`
  ${({maxWidth}) => maxWidth ? `max-width: ${maxWidth}px;` : ''}
  width: 100%;
  display: table;
  border-spacing: 0;
  border-collapse: collapse;
`
const TableHead = styled.thead`
  vertical-align: middle;
`
const TableRow = styled.tr`
  color: inherit;
  display: table-row;
  outline: 0;
  vertical-align: middle;
`
const TableCell = styled.td`
  padding: 16px;
  font-size: 0.875rem;
  text-align: left;
  font-weight: 400;
  line-height: 1.43;
  border-bottom: 1px solid rgba(224, 224, 224, 1);
  letter-spacing: 0.01071em;
  vertical-align: inherit;
`
const TableHeaderCell = styled(TableCell)`
  color: rgba(0, 0, 0, 0.87);
  font-weight: 500;
  line-height: 1.5rem;
`

const myInit = {
  method: 'GET',
  headers: {
    'Content-Type': 'application/json'
  }
}

function App () {
  const [transactions, setTransactions] = React.useState([])
  React.useEffect(() => {
    transactionApi.getAll().then(json => setTransactions(json))
  }, [])
  return (
    <div>
      <Section style={{minWidth: '600px'}}>
        <h2>Transactions</h2>
        <button onClick={() => transactionApi.create({
          date: '2020-01-19',
          amount: 40.44
        }).then(tr => setTransactions([...transactions, tr]))}>Add
        </button>
        <Table>
          <TableHead>
            <TableRow>
              <TableHeaderCell>
                #
              </TableHeaderCell>
              <TableHeaderCell>
                date
              </TableHeaderCell>
              <TableHeaderCell>
                amount
              </TableHeaderCell>
            </TableRow>
          </TableHead>
          <tbody>
          {transactions.map(it => (
            <TableRow key={it.id}
                      onClick={() => transactionApi.delete(it.id).then(() => setTransactions(transactions.filter(t => t.id !== it.id)))}>
              <TableCell>
                {it.id}
              </TableCell>
              <TableCell>
                {new Date(it.date).toLocaleDateString()}
              </TableCell>
              <TableCell>
                ${it.amount}
              </TableCell>
            </TableRow>
          ))}
          </tbody>
        </Table>
      </Section>
    </div>
  )
}

export default App
