import React, {PropTypes} from "react";

const AddTodoView = ({onAddTodo}) => {
    let input = '';
    return (
        <div>
            <input ref={(node) => input = node}/>
            <button onClick={e => {
                e.preventDefault();
                if (!input.value.trim())
                    return;
                onAddTodo(input.value);
                input.value = '';
            }}>
                Add To-do
            </button>
        </div>
    );
};

AddTodoView.propTypes = {
    onAddTodo: PropTypes.func.isRequired
};

export default AddTodoView;
