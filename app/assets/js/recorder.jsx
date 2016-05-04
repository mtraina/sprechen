import React from "react";

class Recorder extends React.Component {
  constructor(props){
    super(props);
    //this.audioContext = new AudioContext;
    //let input = audioContext.createMediaStreamSource(stream);
    //this.recorder = new Recorder(input);
  }

  componentDidMount(){
    document.querySelector("#stop").disabled = true;
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

export default Recorder;
