import React from "react";
import Recorder from "recorderjs";

class VoiceRecorder extends React.Component {
  constructor(props){
    super(props);
    this.startUserMedia = this.startUserMedia.bind(this);
  }

  componentDidMount(){
    document.querySelector("#stop").disabled = true;

    // webkit shim
    navigator.getUserMedia = navigator.getUserMedia ||
                         navigator.webkitGetUserMedia ||
                         navigator.mozGetUserMedia;

    if (navigator.getUserMedia) {
      navigator.getUserMedia({audio: true}, this.startUserMedia, function(e) {
        console.log('No live audio input: ' + e);
      });
    } else {
      console.log("getUserMedia not supported");
    }

    // navigator.getUserMedia({audio: true}, this.startUserMedia, function(e) {
    //   console.log('No live audio input: ' + e);
    // });
  }

  startUserMedia(stream) {
    let audioContext = new AudioContext;
    let input = audioContext.createMediaStreamSource(stream);
    console.log('Media stream created.');
    // Uncomment if you want the audio to feedback directly
    //input.connect(audio_context.destination);
    //__log('Input connected to audio context destination.');

    this.recorder = new Recorder(input);
    console.log('Recorder initialised.');
  }

  startRecording() {
    //recorder && recorder.record();
    document.querySelector("#start").disabled = true;
    document.querySelector("#stop").disabled = false;
    //button.nextElementSibling.disabled = false;
    console.log('Start recording...');
  }

  stopRecording(){
    console.log('Stop recording...');

    document.querySelector("#stop").disabled = true;
    document.querySelector("#start").disabled = false;
  }

  render(){
    return <div>
      <button id="start" onClick={this.startRecording}>record</button>
      <button id="stop" onClick={this.stopRecording}>stop</button>
    </div>;
  }
}

export default VoiceRecorder;
