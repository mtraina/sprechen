import { hashHistory } from 'react-router';
import cookie from 'react-cookie';

const executeRequest = Symbol();

class ServiceClient {

  get(url, callback){
    this[executeRequest](url, "GET", null, callback);
  }

  post(url, data, callback){
    this[executeRequest](url, "POST", data, callback);
  }

  [executeRequest](url, method, data, callback){
    let params = {
      method: method,
      headers: {
        "X-AUTH-TOKEN": cookie.load("AUTH_TOKEN")
      }
    }

    if(data != null) params.body = data;

    fetch(url, params)
      .then(r => {
        if(r.status == 401) hashHistory.push('/login');
        else return r.json();
      })
      .then(json => {
        console.log("json: ", json);
        callback(json);
      })
      .catch(error => console.log("Request failed", error))
  }

}

export let serviceClient = new ServiceClient();
