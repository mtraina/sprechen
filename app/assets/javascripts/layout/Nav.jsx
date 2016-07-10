import React from "react";

class NavLink extends React.Component {
    render() {
      return <a href="javascript:void(0)">{this.props.children}</a>;
    }
}

export default class Nav extends React.Component {
  render() {
    return <nav>
        <ul>
          <li><NavLink>Home</NavLink></li>
          <li><NavLink>Record</NavLink></li>
          <li><NavLink>Dictionary</NavLink></li>
        </ul>
      </nav>;
  }
}
