import React from 'react';
import { Router, Route, hashHistory } from 'react-router';

import CommentBox from './CommentBox.jsx';
import LoginForm from './LoginForm.jsx';

export default class Routes extends React.Component {
  render(){
    return <Router history={hashHistory}>
      <Route path="/" component={CommentBox} />
      <Route path="/login" component={LoginForm} />
    </Router>;
  }
}
