import React, {PropTypes} from "react";

class AddTodoView extends React.Component {
    // input = undefined;
    render() {
        return (
            <div>
                <input ref={(node) => this.input = node}/>
                <button type="submit" onClick={e => {
                    e.preventDefault();
                    console.log("submit props:");
                    console.log(this.props);
                    this.props.onAddTodo(this.input);
                }}>
                    Add To-do
                </button>
            </div>
        );
    }
}

AddTodoView.propTypes = {
    onAddTodo: PropTypes.func.isRequired
};

export default AddTodoView;
