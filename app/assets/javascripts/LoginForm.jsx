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
      .then(r => {
        console.log(r)
        cookie.save("auth", auth, { path: "/" });
        hashHistory.push("/");
      })
      .catch(error => console.log("Request failed", error))
  }

  updateState(k, v) {
    this.state[k] = v;
  }

  render() {
    return <div class="row">
      <div className="medium-6 medium-centered large-4 large-centered columns">
        <form onSubmit={this.login}>
          <div className="row column log-in-form">
            <h4 className="text-center">Log in with you email account</h4>
             <label>Username
              <input type="text" placeholder="Username" onChange={e => this.updateState("username", e.target.value)}/>
            </label>
            <label>Password
              <input type="text" placeholder="Password" onChange={e => this.updateState("password", e.target.value)}/>
            </label>
            {/* <input id="show-password" type="checkbox"><label for="show-password">Show password</label></input> */}
            <input type="submit" className="button expanded" value="Log In"/>
            <p className="text-center"><a href="#">Forgot your password?</a></p>
          </div>
        </form>
      </div>
    </div>;
  }
}
