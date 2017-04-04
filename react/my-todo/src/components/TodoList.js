import React, {PropTypes} from "react";
import Todo from "./Todo";

const TodoList = ({todos, onTodoClick}) => (
    <ul>
        {
            todos.map(todo =>
                <Todo key={todo.id} completed={todo.completed} text={todo.text}/>
            )
        }

    </ul>
);

TodoList.propTypes = {
    todos: PropTypes.arrayOf(
        PropTypes.shape({
            id: PropTypes.number.isRequired,
            completed: PropTypes.bool.isRequired,
            text: PropTypes.string.isRequired
        })
    )
}

export default TodoList;