import React from "react";
import Nav from "./Nav.jsx";
import Footer from "./Footer.jsx";

export default class Layout extends React.Component {
  render() {
    return <div>
        <Nav/>
        <main>{this.props.children}</main>
        <Footer/>
      </div>;
  }
}
