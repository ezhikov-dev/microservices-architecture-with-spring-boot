import BaseApi from './BaseApi'

class TransactionApi extends BaseApi {
  getAll () {
    return this.getAndProcessPromise('/api/transaction')
  }
  create (transaction) {
    return this.postAndProcessPromise('/api/transaction', transaction)
  }
  delete (id) {
    return this.deleteAndProcessPromise(`/api/transaction/${id}`)
  }
}

export default new TransactionApi()