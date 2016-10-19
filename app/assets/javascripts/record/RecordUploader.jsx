import React from "react";
import Recorder from "recorderjs";

export default class RecordUploader extends React.Component {
  constructor(props) {
    super(props);
    this.previewFile = this.previewFile.bind(this);
  }

  previewFile(event) {
    console.log("previewFile...in");

    const file = document.querySelector("#audio").files[0];
    const reader = new FileReader();

    reader.onloadend = (upload) => this.props.handleSpeech(file);

    if (file) {
        console.log("previewFile...is file");
        reader.readAsDataURL(file);
    }
  }

  render(){
    return <div>
      <input id="audio" type="file" onChange={this.previewFile}></input>
    </div>
  }
}
