import React from 'react';
import { Router, Route, browserHistory, IndexRoute } from 'react-router';

import CommentBox from './CommentBox.jsx';
import LoginForm from './LoginForm.jsx';

export default (
  <Router history={browserHistory}>
    <Route path="/" component={CommentBox} />
    <Route path="/login" component={LoginForm} />
  </Router>
);
