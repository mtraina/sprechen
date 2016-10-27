import { hashHistory } from 'react-router';
import cookie from 'react-cookie';

const executeRequest = Symbol();

class ServiceClient {

  get(url, callback){
    this[executeRequest](url, "GET", callback);
  }

  post(url, data, callback){
    fetch(url, {
        method: "POST",
        body: data,
        headers: {
          "X-AUTH-TOKEN": cookie.load("AUTH_TOKEN")
        }
      })
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

  [executeRequest](url, method, callback){
    fetch(url, {
        method: method,
        headers: {
          "X-AUTH-TOKEN": cookie.load("AUTH_TOKEN")
        }
      })
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
