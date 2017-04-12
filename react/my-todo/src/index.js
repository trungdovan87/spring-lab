import React from "react";
import ReactDOM from "react-dom";
import App from "./components/App.js";
import reducer from './reducers';
import { createStore, applyMiddleware } from 'redux'
import { Provider } from 'react-redux'
import thunkMiddleware from 'redux-thunk'

const middlewares = [thunkMiddleware];
const store = createStore(reducer, applyMiddleware(...middlewares));

ReactDOM.render(
    <Provider store={store}>
        <App />
    </Provider>,
    document.getElementById('root')
);
