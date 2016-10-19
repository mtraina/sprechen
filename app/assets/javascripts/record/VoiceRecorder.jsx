import React from "react";
import Recorder from "recorderjs";

export default class VoiceRecorder extends React.Component {
  constructor(props){
    super(props);
    this.startUserMedia = this.startUserMedia.bind(this);
    this.startRecording = this.startRecording.bind(this);
    this.stopRecording = this.stopRecording.bind(this);
  }

  componentDidMount(){
    // webkit shim
    navigator.getUserMedia = navigator.getUserMedia ||
                         navigator.webkitGetUserMedia ||
                         navigator.mozGetUserMedia;

    if (navigator.getUserMedia) {
      navigator.getUserMedia(
        {"audio": {
          "mandatory": {
            "googEchoCancellation": "false",
            "googAutoGainControl": "false",
            "googNoiseSuppression": "false",
            "googHighpassFilter": "false"
          },
          "optional": []
        }},
        this.startUserMedia, function(e) {
          console.log('No live audio input: ' + e);
        });
    } else {
      console.log("getUserMedia not supported");
    }
  }

  startUserMedia(stream) {
    let audioContext = new AudioContext;
    let input = audioContext.createMediaStreamSource(stream);
    console.log('Media stream created.');

    this.recorder = new Recorder(input);
    console.log('Recorder initialised.');
  }

  startRecording() {
    this.recorder && this.recorder.record();
    console.log('Start recording...');
  }

  stopRecording(){
    console.log('Stop recording...');
    this.recorder && this.recorder.stop();
    this.recorder.exportWAV(this.props.handleSpeech);
    this.recorder.clear();
  }

  render(){
    return <div>
      <button onMouseDown={this.startRecording} onMouseUp={this.stopRecording}>record</button>
    </div>;
  }
}
