import React from 'react';
import Layout from "../layout/Layout.jsx";
import ResultTable from "./ResultTable.jsx";
import { serviceClient } from "../client/ServiceClient.js"

export default class Dictionary extends React.Component {

  constructor(props){
    super(props);
    this.state = {dictionary: []};
    this.getDictionary = this.getDictionary.bind(this);
  }

  componentWillMount(){
    serviceClient.get("/speeches", this.getDictionary);
  }

  getDictionary(dictionary){
    this.setState({dictionary: dictionary});
  }

  render(){
    return <Layout><ResultTable data={this.state.dictionary}/></Layout>;
  }
}
