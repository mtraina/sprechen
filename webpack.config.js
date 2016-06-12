const config = {
  entry: './app/assets/javascripts/App.jsx',

  output: {
    path: './public/javascripts',
    filename: 'bundle.js',
    sourceMapFilename: "bundle.map"
  },

  module: {
    loaders: [
      {
        test: /.jsx?$/,
        loader: 'babel',
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

module.exports = config
