import React from "react";
import ReactDOM from "react-dom";
import "es6-promise";
import "whatwg-fetch";

//import Router from './Router.js'

import LoginForm from './LoginForm.jsx';
import CommentBox from './CommentBox.jsx';
import { Router, Route, hashHistory } from 'react-router';

ReactDOM.render((
  <Router history={hashHistory}>
    <Route path="/" component={CommentBox} />
    <Route path="/login" component={LoginForm} />
  </Router>), document.getElementById('content'));
