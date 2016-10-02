import React from "react";
import RecordBox from "../record/RecordBox.jsx";

export default class Article extends React.Component {
  constructor(props){
    super(props);
    console.log(JSON.stringify(props));
  }

  render() {
    return <article><RecordBox/></article>;
  }
}
