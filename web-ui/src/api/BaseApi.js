function checkStatus (response, checkAuth = true, request) {
  if (response.status >= 200 && response.status < 300) {
    return response.payload
  }
  if (response.status === 401 && checkAuth) {
    // eslint-disable-next-line global-require
    // require('store').default.dispatch({type: 'AUTH_FAILURE'})
    // todo: reload?
  } else if (response.status === 502 || response.status === 503) {
    // location.href = '/error'
  }
  const error = new Error(response.statusText)
  error.response = response
  error.request = request
  throw error
}

export function parse (response, func) {
  return response[func]()
    .then(res => {
      response.payload = res
      return response
    })
    .catch(() => {
      response.payload = null
      return response
    })
}

function logError (error) {
  const errorObj = {
    errorMessage: error.message,
    ...error
  }
  if (error.response) {
    errorObj.status = error.response.status
    errorObj.payload = error.response.payload
    errorObj.errorUrl = error.response.url
  } else {
    errorObj.errorUrl = window.location.href
  }
  if (error.request) {
    errorObj.request = error.request
  }
  console.error(errorObj)
  throw errorObj
}

export function requestOptions (method, body = null, headers = {}) {
  const options = {
    method,
    headers: {
      Accept: 'application/json',
      'Content-Type': 'application/json',
      ...headers
    }
  }
  return !body ? options : {...options, body: JSON.stringify(body)}
}

export default class BaseApi {
  processPromise (promise, request, checkAuth, isJson = true) {
    return promise
      .then(response => parse(response, isJson ? 'json' : 'text'))
      .then(response => checkStatus(response, checkAuth, request))
      .catch(logError)
  }

  buildRequestAndProcessPromise (url, restVerb = 'GET', checkAuth, isJson, body, headers) {
    const req = new Request(url, requestOptions(restVerb, body, headers))
    return this.processPromise(fetch(req), req, checkAuth, isJson)
  }

  getAndProcessPromise (apiUrl, checkAuth) {
    return this.buildRequestAndProcessPromise(apiUrl, 'GET', checkAuth, true, undefined)
  }

  getAndProcessPromiseAaText (apiUrl, checkAuth) {
    return this.buildRequestAndProcessPromise(apiUrl, 'GET', checkAuth, false, undefined)
  }

  putAndProcessPromise (apiUrl, body, checkAuth) {
    return this.buildRequestAndProcessPromise(apiUrl, 'PUT', checkAuth, true, body)
  }

  postAndProcessPromise (apiUrl, body, checkAuth, headers) {
    return this.buildRequestAndProcessPromise(apiUrl, 'POST', checkAuth, true, body, headers)
  }

  deleteAndProcessPromise (apiUrl, body, checkAuth) {
    return this.buildRequestAndProcessPromise(apiUrl, 'DELETE', checkAuth, true, body)
  }
}
