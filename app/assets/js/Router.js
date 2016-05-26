import React from 'react';
import { Router, Route, browserHistory, IndexRoute } from 'react-router';

import CommentBox from './CommentBox.jsx';

export default (
  <Router history={browserHistory}>
    <Route path="/" component={CommentBox} />
  </Router>
);
