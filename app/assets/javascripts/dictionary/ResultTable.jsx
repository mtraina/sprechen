import React from 'react';
import Layout from "../layout/Layout.jsx";
import {Table, Column, Cell} from 'fixed-data-table';

export default class ResultTable extends React.Component {
  constructor(props){
      super(props);
  }

  render() {
      return <Table height={40+((this.props.data.length+1) * 30)}
          width={1150}
          rowsCount={this.props.data.length}
          rowHeight={30}
          headerHeight={30}
          rowGetter={function(rowIndex) {return this.props.data[rowIndex]; }.bind(this)}>
          <Column dataKey="text" width={300} label="Text" />
          <Column dataKey="translations" width={850} label="Translations"
            cellRenderer={cellData => cellData.join()}/>
        </Table>;
    }
}
