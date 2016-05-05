import React from "react";
import Recorder from "recorderjs";

class VoiceRecorder extends React.Component {
  constructor(props){
    super(props);
    this.startUserMedia = this.startUserMedia.bind(this);
    this.startRecording = this.startRecording.bind(this);
    this.stopRecording = this.stopRecording.bind(this);
  }

  componentDidMount(){
    document.querySelector("#stop").disabled = true;

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
    // Uncomment if you want the audio to feedback directly
    //input.connect(audio_context.destination);
    //__log('Input connected to audio context destination.');

    this.recorder = new Recorder(input);
    console.log('Recorder initialised.');
  }

  startRecording() {
    document.querySelector("#start").disabled = true;
    document.querySelector("#stop").disabled = false;

    this.recorder && this.recorder.record();
    console.log('Start recording...');
  }

  stopRecording(){
    console.log('Stop recording...');
    this.recorder && this.recorder.stop();

    this.recorder.exportWAV(this.sendSpeech);

    //this.recorder.getBuffer(this.recorder.exportWAV(this.sendSpeech));

    document.querySelector("#stop").disabled = true;
    document.querySelector("#start").disabled = false;
  }

  // doneEncoding(blob){
  //   let url = URL.createObjectURL(blob);
  //   Recorder.setupDownload(blob, "myRecording" + ((recIndex<10)?"0":"") + recIndex + ".wav" );
  // }

  sendSpeech(blob){
    const data = new FormData();
    data.append("speech", blob);

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
  }

  render(){
    return <div>
      <button id="start" onClick={this.startRecording}>record</button>
      <button id="stop" onClick={this.stopRecording}>stop</button>
    </div>;
  }
}

export default VoiceRecorder;
