import React from 'react';
import { Router, Route, hashHistory } from 'react-router';
import cookie from 'react-cookie';

import CommentBox from './CommentBox.jsx';
import Main from "./Main.jsx";
import LoginForm from './LoginForm.jsx';

export default class Routes extends React.Component {
  constructor(props) {
    super(props);
    this.requireAuth = this.requireAuth.bind(this);
    this.state = {"body": ["my", "test"]};
  }

  requireAuth(nextState, replace) {
      const auth = cookie.load("auth");
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
