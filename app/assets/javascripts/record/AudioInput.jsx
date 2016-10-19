import Recorder from "recorderjs";

class AudioInput {

  constructor() {
    this.startUserMedia = this.startUserMedia.bind(this);

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

  record() {
    this.recorder && this.recorder.record();
    console.log('Start recording...');
  }

  stopRecording(handleSpeech) {
    this.recorder && this.recorder.stop();
    this.recorder.exportWAV(handleSpeech);
    this.recorder.clear();
    console.log('Stop recording...');
  }
}

export let audioInput = new AudioInput();
