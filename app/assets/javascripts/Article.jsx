import React from "react";

export default class Article extends React.Component {
  constructor(props){
    super(props);
    console.log(JSON.stringify(props));
  }

  render() {
    const body = this.props.data.body.map((p, i) => <p key={i}>{p}</p>);
    return <article>
      <h1>{this.props.data.title}</h1>
      {body}
    </article>;
  }
}
