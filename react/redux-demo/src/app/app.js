import React from 'react'

import { render } from 'react-dom'
import { createStore } from 'redux'
import { Provider } from 'react-redux'

import App from './App.jsx'
import todoApp from './reducers/reducers'

let store = createStore(todoApp);

let rootElement = document.getElementById('app');
let text = "abc";
let json = {
    type: ADD_TODO,
    id: nextTodoId++,
    text
};
render(
    <div>
        <h1> {json} </h1>
    <Provider store = {store}>
        <App />
    </Provider>
    </div>,

    rootElement
);
