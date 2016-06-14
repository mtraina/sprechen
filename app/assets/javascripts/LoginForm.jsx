import React from "react";

//require('../stylesheets/modules/login-form.scss');

export default class List extends React.Component {
  constructor(props){
    super(props);
    this.state = {email: ""};
    this.login = this.login.bind(this);
    this.handleChange = this.handleChange.bind(this);
  }

  login(e) {
    e.preventDefault();
    console.log("login!");
    console.log("username:", this.state.email);
    // fetch("/auth", {
    //     method: "POST",
    //     body: data
    //   })
    //   .then(r => console.log(r))
    //   .catch(error => console.log("Request failed", error))
  }

  handleChange(e) {
    e.preventDefault();

    let state = {};
    state.email = e.target.value;
    this.state = state;
    console.log("state is:" + JSON.stringify(this.state));
  }

  render() {
    return <div class="row">
      <div className="medium-6 medium-centered large-4 large-centered columns">
        <form onSubmit={this.login}>
          <div className="row column log-in-form">
            <h4 className="text-center">Log in with you email account</h4>
             <label>Email
              <input type="text" placeholder="somebody@example.com" onChange={this.handleChange}/>
            </label>
            <label>Password
              <input type="text" placeholder="Password"/>
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
