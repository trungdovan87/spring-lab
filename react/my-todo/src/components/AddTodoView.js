import React, {PropTypes} from "react";

const AddTodoView = ({onAddTodo, onAddTodoAsync}) => {
    let input;
    return (
        <div>
            <input ref={(node) => input = node}/>
            <button onClick={e => {
                e.preventDefault();
                if (!input.value.trim()) {
                    alert("Please, type input!")
                    return;                    
                }
                onAddTodo(input.value);
                input.value = '';
            }}>
                Add To-do
            </button>

            <button onClick={e => {
                e.preventDefault();
                if (!input.value.trim()) {
                    alert("Please, type input!")
                    return;
                }
                onAddTodoAsync(input.value);
                input.value = '';
            }}>
                Add To-do ASYNC
            </button>
        </div>
    );
};

AddTodoView.propTypes = {
    onAddTodo: PropTypes.func.isRequired
};

export default AddTodoView;
