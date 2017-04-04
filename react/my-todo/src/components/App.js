/**
 * Created by trungdovan on 03/04/2017.
 */
import React from "react";
import AddTodo from "./AddTodo";
import Footer from "./Footer";
import TodoList from "./TodoList";

let todos = [
    {id: 1, completed: true, text: 'MyLove'},
    {id: 2, completed: false, text: 'xyz'},
];

const App = () => (
    <div>
        <h1>Todo App</h1>
        <AddTodo/>
        <TodoList todos={todos}/>
        <Footer/>
    </div>
);

export default App;

