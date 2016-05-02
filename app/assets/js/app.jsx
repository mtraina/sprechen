import React from "react";
import ReactDOM from "react-dom";
import 'es6-promise';
import 'whatwg-fetch';
import $ from "jquery";

class CommentBox extends React.Component {
  previewFile(event) {
    console.log("previewFile...in");

    //let audio;
    //let preview = document.querySelector('img');
    let file    = document.querySelector('input[type=file]').files[0];
    let reader  = new FileReader();

    console.log("previewFile...after let setup");

    //reader.onload = (upload) => this.getData(upload);
    reader.onloadend = (upload) => {
      console.log(upload);

      const file_data = $("#audio").prop("files")[0];
      const data = new FormData();
      data.append("speech", file_data);

      fetch("http://localhost:9000/recognize", {
        method: "POST",
        body: data
      })
      .then(function(response){
        console.log("response: ", response);
        //console.log("json:", response.json());
        return response.json();
      })
      .then(function(json){
        console.log("json: ", json);
      })
      .catch(function(error) {
        console.log("Request failed", error );
      });
  };

    if (file) {
      console.log("previewFile...is file");
      reader.readAsDataURL(file);
    }
  }

  render(){
    return <div>
      <input id="audio" type="file" onChange={this.previewFile}></input>
      <img src="" height="200" alt="Image preview..."></img>
    </div>;
  }
};

ReactDOM.render(
  <CommentBox />, document.getElementById('content')
);
