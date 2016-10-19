import React from "react";
import { audioInput } from "./AudioInput.jsx"

export default class VoiceRecorder extends React.Component {
  constructor(props){
    super(props);
    this.startRecording = this.startRecording.bind(this);
    this.stopRecording = this.stopRecording.bind(this);
  }

  startRecording() {
    audioInput.record();
  }

  stopRecording(){
    audioInput.stopRecording(this.props.handleSpeech);
  }

  render(){
    return <div>
      <button onMouseDown={this.startRecording} onMouseUp={this.stopRecording}>record</button>
    </div>;
  }
}
