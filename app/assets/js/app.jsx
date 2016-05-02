import React from "react";
import ReactDOM from "react-dom";
import 'es6-promise';
import 'whatwg-fetch';
import $ from "jquery";

class CommentBox extends React.Component {
  constructor(props){
    super(props);
    this.getData = this.getData.bind(this);
  }

  componentDidMount(){
    console.log("componentDidMount");

  }

  // handleChange(event) {
  //   console.log("handle event!");
  // }

  onChange(field) {
    return event => {
      const value = event.target.value;
      this.props.updateField(field, value);
    };
  }

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

      // fetch("http://localhost:9000/recognize", {
      //   method: 'POST',
      //   headers: new Headers({
      //      "Content-Type": "audio/wav",
      //   }),
      //   body: upload.target.result
      // })
      // .catch(function(error) {
      //   console.log( 'Request failed', error );
      // });

      var file_data = $("#audio").prop("files")[0];
      var form_data = new FormData();
      form_data.append("speech", file_data);
      $.ajax({
                url: "http://localhost:9000/recognize",
                dataType: 'script',
                cache: false,
                contentType: false,
                processData: false,
                data: form_data,                         // Setting the data attribute of ajax with file_data
                type: 'post'
       })
  };

    // reader.addEventListener("load", function () {
    //   console.log("previewFile...loaded");
    //   this.getData(reader.result);
    //
    //   //preview.src = reader.result;
    //   //this.audio = reader.result;
    // }, false);

    if (file) {
      console.log("previewFile...is file");
      reader.readAsDataURL(file);
    }
  }

  getData(event){
    fetch("http://localhost:9000/recognize", {
      method: 'POST',
      // headers: new Headers({
      //   "Content-Type": "image/jpeg",
      // }),
      body: event.result,
    })
  }

  render(){
    return <div>
      {/*}<input type="text" onChange={(e) => this.handleChange(e)} />*/}
      <input id="audio" type="file" onChange={this.previewFile}></input>
      <img src="" height="200" alt="Image preview..."></img>
    </div>;
  }
};

ReactDOM.render(
  <CommentBox />, document.getElementById('content')
);
