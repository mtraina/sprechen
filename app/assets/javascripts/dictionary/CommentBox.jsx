import React from "react";
//import { hashHistory } from 'react-router';
import List from "./List.jsx";
import RecordUploader from "../record/RecordUploader.jsx";
import VoiceRecorder from "../record/VoiceRecorder.jsx";

export default class CommentBox extends React.Component {
  constructor(props) {
    super(props);
    this.state = {guesses: []};
    this.sendSpeech = this.sendSpeech.bind(this);
  }

  sendSpeech(blob){
    const data = new FormData();
    data.append("speech", blob);

    fetch("/recognize", {
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
      <RecordUploader handleSpeech={this.sendSpeech}/>
      <VoiceRecorder handleSpeech={this.sendSpeech}/>
      <List id="list" data={this.state.guesses}/>
    </div>;
  }
};
