import React from 'react';
import Layout from "../layout/Layout.jsx";
import {Table, Column, Cell} from 'fixed-data-table';

export default class ResultTable extends React.Component {
  constructor(props){
      super(props);
      this.state = {
        rows : [{"text":"weather","translations":["Wetter", "Another"]}]
      };
  }

  render() {
      return <Layout>
        <Table height={40+((this.state.rows.length+1) * 30)}
          width={1150}
          rowsCount={this.state.rows.length}
          rowHeight={30}
          headerHeight={30}
          rowGetter={function(rowIndex) {return this.state.rows[rowIndex]; }.bind(this)}>
          <Column dataKey="text" width={300} label="Text" />
          <Column dataKey="translations" width={850} label="Translations"
            cellRenderer={cellData => cellData.join()}/>
        </Table>
      </Layout>;
    }
}
