import React from "react"
import Todo from "./Todo"

const TodoList = ({ todos, onTodoClick}) => (
    <ul>
        <Todo key={1} completed={true} text='abc'/>
        <Todo key={2} completed={false} text='kaka'/>
    </ul>
);

export default TodoList;