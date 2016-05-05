import React from "react";
import Recorder from "recorderjs";

class RecordUploader extends React.Component {
  constructor(props) {
    super(props);
    this.previewFile = this.previewFile.bind(this);
  }

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
        .then(json => {
          console.log("json: ", json);
          this.setState({guesses: json.guesses});
        })
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
    </div>
  }
}

export default RecordUploader;
