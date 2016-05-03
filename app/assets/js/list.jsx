import React from 'react';

class ListItem extends React.Component {
  render() {
      return <li>{this.props.data}</li>;
  }
}

class List extends React.Component {
  render() {
      return <ul>
        {this.props.data.map((d, i) => <ListItem key={i} data={d}/>)}
      </ul>;
  }
}

export default List;
