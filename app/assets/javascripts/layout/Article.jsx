import React from "react";
import CommentBox from "../dictionary/CommentBox.jsx";

export default class Article extends React.Component {
  constructor(props){
    super(props);
    console.log(JSON.stringify(props));
  }

  render() {
    return <article>
      <CommentBox/>
    </article>;
  }
}
