import React from 'react';

class ListItem extends React.Component {
  render() {
      return <li>{this.props.data}</li>;
  }
}

export default class List extends React.Component {
  render() {
    return <div><h3>Results:</h3>
        <ul>{this.props.data.map((d, i) => <ListItem key={i} data={d}/>)}</ul>
      </div>;
  }
}
