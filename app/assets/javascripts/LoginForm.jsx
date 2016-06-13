import React from "react";

//require('../stylesheets/modules/login-form.scss');

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
    return <div class="row">
      <div className="medium-6 medium-centered large-4 large-centered columns">
        <form>
          <div className="row column log-in-form">
            <h4 className="text-center">Log in with you email account</h4>
             <label>Email
              <input type="text" placeholder="somebody@example.com"/>
            </label>
            <label>Password
              <input type="text" placeholder="Password"/>
            </label>
            {/* <input id="show-password" type="checkbox"><label for="show-password">Show password</label></input> */}
            <p><a type="submit" className="button expanded">Log In</a></p>
            <p className="text-center"><a href="#">Forgot your password?</a></p>
          </div>
        </form>
      </div>
    </div>;
  }
}
