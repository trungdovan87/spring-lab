/**
 * Created by trungdovan on 03/04/2017.
 */
import React from "react";

const Todo = ({onClick, completed, text}) => (
    <li
        onClick={onClick}
        style={
            {
                textDecoration: completed ? 'line-through' : 'none'
            }
        }
    >
        {text}
    </li>
);

Todo.propTypes = {
    completed: React.PropTypes.bool.isRequired,
    text: React.PropTypes.string.isRequired,
    onClick: React.PropTypes.func
};

export default Todo;