import { hashHistory } from 'react-router';
import cookie from 'react-cookie';

class ServiceClient {

  get(url, callback){
    fetch("/speeches", {
        method: "GET",
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
