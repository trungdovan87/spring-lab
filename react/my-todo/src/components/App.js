/**
 * Created by trungdovan on 03/04/2017.
 */
import React from "react";
import AddTodoContainer from "../containers/AddTodoContainer";
import Footer from "./Footer";
import VisibleTodoList from "../containers/VisibleTodoList";

const App = () => (
    <div>
        <h1>To-do App 3</h1>
        <AddTodoContainer/>
        <VisibleTodoList/>
        <Footer/>
    </div>
);

export default App;

