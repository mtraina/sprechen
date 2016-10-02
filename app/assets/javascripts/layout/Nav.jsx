import React from "react";

class NavLink extends React.Component {
  render() {
    const link = "#/" + this.props.link;
    return <a href={link}>{this.props.children}</a>;
  }
}

export default class Nav extends React.Component {
  render() {
    return <nav>
        <ul>
          <li><NavLink link={""}>Home</NavLink></li>
          <li><NavLink link={"record"}>Record</NavLink></li>
          <li><NavLink link={"dictionary"}>Dictionary</NavLink></li>
        </ul>
      </nav>;
  }
}
