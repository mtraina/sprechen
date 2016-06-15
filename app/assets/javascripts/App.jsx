import React from "react";
import { render } from "react-dom";
import "es6-promise";
import "whatwg-fetch";
import Routes from './Routes.jsx'

//require('../stylesheets/modules/login-form.scss');

render(<Routes/>, document.getElementById('content'));
