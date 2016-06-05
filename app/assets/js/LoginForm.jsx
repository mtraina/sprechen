import React from "react";

export default class List extends React.Component {
  constructor(props){
    super(props);
    this.login = this.login.bind(this);
  }

  login() {
    //console.log("username:", document.querySelector("#username").text());
    fetch("/auth", {
        method: "POST",
        body: data
      })
      .then(r => console.log(r))
      .catch(error => console.log("Request failed", error))
  }

  render() {
    return <div>
      <h3>Login</h3>
      <h5>Username</h5><input id="username" type="text"/>
      <h5>Password</h5><input id="password" type="password"/>
      <button onClick={this.login}/>
      </div>;
  }
}
