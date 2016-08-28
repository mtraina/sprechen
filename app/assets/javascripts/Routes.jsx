import React from 'react';
import { Router, Route, hashHistory } from 'react-router';
import cookie from 'react-cookie';

import Main from "./layout/Main.jsx";
import LoginForm from './auth/LoginForm.jsx';

export default class Routes extends React.Component {
  constructor(props) {
    super(props);
    this.requireAuth = this.requireAuth.bind(this);
  }

  requireAuth(nextState, replace) {
      const auth = cookie.load("AUTH_TOKEN");
      if (!auth) {
        replace({
          pathname: '/login',
          state: { nextPathname: nextState.location.pathname }
        })
      }
  }

  render(){
    return <Router history={hashHistory}>
      <Route path="/" component={Main} onEnter={this.requireAuth} data={this.state}/>
      <Route path="/login" component={LoginForm} />
    </Router>;
  }
}
