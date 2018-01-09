import axios from 'axios';

const client = function (options) {
    let instance = axios.create({
        baseURL: '/apiv1',
        timeout: 30000,  // 超时
        responseType: 'json' // default
    })
    return new Promise((resolve, reject) => {
        instance(options)
            .then(response => {
                resolve(response.data)
            })
            .catch(error => {
                reject(error)
            })
    })
}

const apiFactory = {

}

export default (requestName, params = {}, data = {}) => client({
    url: apiFactory[requestName].url,
    method: apiFactory[requestName].type,
    params: params
})
