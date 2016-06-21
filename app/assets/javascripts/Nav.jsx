import React from "react";

export default class NavLink extends React.Component {
    render() {
      return <a href="javascript:void(0)">{this.props.children}</a>;
    }
}

export default class Nav extends React.Component {
  render() {
    return <nav>
        <ul>
          <li><NavLink>Home</NavLink></li>
          <li><NavLink>Articles</NavLink></li>
          <li><NavLink>About Us</NavLink></li>
        </ul>
      </nav>;
  }
}
