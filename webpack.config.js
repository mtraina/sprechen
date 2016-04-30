module.exports = {
  entry: './app/assets/js/app.jsx',

  output: {
    path: './public/js',
    filename: 'bundle.js'
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
};
