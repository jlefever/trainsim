import React from "react";
import ReactDOM from "react-dom";
import App from "./App";

const element = document.createElement("div");
element.setAttribute("id", "root");
document.body.appendChild(element);
ReactDOM.render(React.createElement(App), element);