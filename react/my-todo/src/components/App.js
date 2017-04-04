/**
 * Created by trungdovan on 03/04/2017.
 */
import React from "react";
// import AddTodoView from "./AddTodoView";
import AddTodoContainer from "../containers/AddTodoContainer";
import Footer from "./Footer";
import TodoList from "./TodoList";

let todos = [
    {id: 1, completed: true, text: 'MyLove'},
    {id: 2, completed: false, text: 'xyz'},
];

const App = () => (
    <div>
        <h1>To-do App 3</h1>
        <AddTodoContainer/>
        <TodoList todos={todos}/>
        <Footer/>
    </div>
);

export default App;

