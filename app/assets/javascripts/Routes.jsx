import React from 'react';
import { Router, Route, hashHistory } from 'react-router';
import cookie from 'react-cookie';

import CommentBox from './CommentBox.jsx';
import LoginForm from './LoginForm.jsx';

export default class Routes extends React.Component {
  constructor(props) {
    super(props);
    this.requireAuth = this.requireAuth.bind(this);
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
      <Route path="/" component={CommentBox} onEnter={this.requireAuth}/>
      <Route path="/login" component={LoginForm} />
    </Router>;
  }
}
