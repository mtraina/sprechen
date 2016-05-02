import React from "react";
import ReactDOM from "react-dom";
import "es6-promise";
import "whatwg-fetch";
import List from "./list.jsx";

class CommentBox extends React.Component {

  previewFile(event) {
    console.log("previewFile...in");

    const file = document.querySelector("#audio").files[0];
    const reader = new FileReader();

    reader.onloadend = (upload) => {
      const data = new FormData();
      data.append("speech", file);

      fetch("http://localhost:9000/recognize", {
          method: "POST",
          body: data
        })
        .then(r => r.json())
        .then(json => console.log("json: ", json))
        .catch(error => console.log("Request failed", error))
    };

    if (file) {
        console.log("previewFile...is file");
        reader.readAsDataURL(file);
    }
  }

  render(){
    return <div>
      <input id="audio" type="file" onChange={this.previewFile}></input>
      <List data={[1,2,3,4,5]}/>
    </div>;
  }
};

ReactDOM.render(
  <CommentBox />, document.getElementById('content')
);
