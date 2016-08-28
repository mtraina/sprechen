import React from "react";
import cookie from 'react-cookie';
import { hashHistory } from 'react-router';

export default class List extends React.Component {
  constructor(props){
    super(props);
    this.state = {username: "", password: ""};
    this.login = this.login.bind(this);
  }

  login(e) {
    e.preventDefault();
    console.log("state:", this.state);
    const auth = btoa(this.state.username + ":" + this.state.password);

    fetch("/auth", {
        method: "POST",
        headers: {
          "Authorization": "Basic " + auth
        }
      })
      .then(r => r.json())
      .then(json => {
        console.log(json);
        cookie.save("AUTH_TOKEN", json.token, { path: "/" });
        hashHistory.push("/");
      }).catch(error => console.log("Request failed", error))
  }

  updateState(k, v) {
    this.state[k] = v;
  }

  render() {
    return <div className="container">
        <div className="login">
          <h1>Log in with you email account</h1>
          <form onSubmit={this.login}>
            <p><input type="text" placeholder="Username" onChange={e => this.updateState("username", e.target.value)}/></p>
            <p><input type="password" placeholder="Password" onChange={e => this.updateState("password", e.target.value)}/></p>
            {/* <input id="show-password" type="checkbox"><label for="show-password">Show password</label></input> */}
            <p><input type="submit" className="button expanded" value="Log In"/></p>
            <p><a href="#">Forgot your password?</a></p>
          </form>
        </div>
      </div>;
  }
}
