const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
	plugins: [new HtmlWebpackPlugin({ template: './src/index.html' })],
	module: {
		rules: [
			{
				test: /\.js$/,
				loader: 'babel-loader',
				options: {
					presets: [
						[
							'@babel/preset-react',
							{
								runtime: 'automatic',
							},
						],
					],
				},
			},
			{
				test: /\.css$/,
				use: ['style-loader', 'css-loader'],
			},
		],
	},
};
