const ExtractTextPlugin = require('extract-text-webpack-plugin');

const config = {
  entry: './app/assets/javascripts/App.jsx',

  output: {
    path: './public/',
    filename: 'javascripts/[name].js',
    sourceMapFilename: "bundle.map"
  },

  module: {
    loaders: [
      {
        test: /\.jsx?$/,
        loader: 'babel',
        exclude: /node_modules/,
        query: {
          presets: ['es2015', 'react']
        }
      },
      {
        test: /\.scss$/,
        loader: ExtractTextPlugin.extract('css!sass')
      }
    ]
  },

  plugins: [
      new ExtractTextPlugin('stylesheets/[name].css', {
          allChunks: true
      })
  ],

  externals: {
      "react": "React",
      "react-dom": "ReactDOM",
      "react-router": "ReactRouter"
  }
};

module.exports = config
