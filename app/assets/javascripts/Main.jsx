import React from "react";
import Layout from "./Layout.jsx";
import Article from "./Article.jsx";

export default class Main extends React.Component {
  render() {
    return <Layout>
        <Article data={this.props.data} />
      </Layout>;
  }
}
