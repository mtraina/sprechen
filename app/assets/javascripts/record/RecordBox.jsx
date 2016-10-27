import React from "react";
import cookie from 'react-cookie';
import List from "./List.jsx";
import RecordUploader from "../record/RecordUploader.jsx";
import VoiceRecorder from "../record/VoiceRecorder.jsx";
import { serviceClient } from "../client/ServiceClient.js"

export default class CommentBox extends React.Component {
  constructor(props) {
    super(props);
    this.state = {guesses: []};
    this.sendSpeech = this.sendSpeech.bind(this);
    this.getSpeech = this.getSpeech.bind(this);
  }

  sendSpeech(blob){
    const data = new FormData();
    data.append("speech", blob);
    serviceClient.post("/recognize", data, this.getSpeech)
  }

  getSpeech(speech){
    this.setState({guesses: speech});
  }

  render(){
    return <div>
      <RecordUploader handleSpeech={this.sendSpeech}/>
      <VoiceRecorder handleSpeech={this.sendSpeech}/>
      <List id="list" data={this.state.guesses}/>
    </div>;
  }
};
