const HtmlWebpackPlugin = require("html-webpack-plugin");
const path = require("path");

module.exports = {
    entry: "./src/index.ts",
    mode: "development",
    devtool: "inline-source-map",
    plugins: [
        new HtmlWebpackPlugin({
            title: "Output Management"
        })
    ],
    module: {
        rules: [
            {
                test: /\.tsx?$/,
                use: "ts-loader",
                exclude: /node_modules/
            }
        ]
    },
    resolve: {
        extensions: [".tsx", ".ts", ".js"]
    },
    output: {
        filename: "bundle.js",
        path: path.resolve(__dirname, "..", "main", "resources", "static"),
        clean: true
    },
};