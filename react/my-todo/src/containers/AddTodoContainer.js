import React from "react";
import { connect } from 'react-redux'
import { addTodo } from '../actions'

class AddTodoContainer extends React.Component {

    handleSubmit = e => {
        e.preventDefault();
        if (!this.input.value.trim())
            return;
        alert("add Todo: " + this.input.value);
        console.log("props: ");
        console.log(this.props);
        this.props.dispatch(addTodo(this.input.value));
        this.input.value = '';
    };

    render() {
        return (
            <div>
                <input ref={(node) => this.input = node}/>
                <button type="submit" onClick={this.handleSubmit}>
                    Add To-do
                </button>
            </div>
        );
    }
}

AddTodoContainer = connect()(AddTodoContainer);

export default AddTodoContainer;