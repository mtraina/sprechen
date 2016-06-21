import React from "react";

export default class Article extends React.Component {
  render() {
    const body = this.props.data.body.map(part => <p>{part}</p>);
    return <article>
      <h1>{this.props.data.title}</h1>
      {body}
    </article>;
  }
}
