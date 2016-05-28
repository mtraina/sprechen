module.exports = {
  entry: './app/assets/js/App.jsx',

  output: {
    path: './public/js',
    filename: 'bundle.js',
    sourceMapFilename: "bundle.map"
  },

  module: {
    loaders: [
      {
        test: /.jsx?$/,
        loader: 'babel-loader',
        exclude: /node_modules/,
        query: {
          presets: ['es2015', 'react']
        }
      }
    ]
  },

  externals: {
      "react": "React",
      "react-dom": "ReactDOM",
      "react-router": "ReactRouter"
  }
};
