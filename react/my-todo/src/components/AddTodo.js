import React from "react";

class AddTodo extends React.Component {
    input = undefined;

    handleSubmit = e => {
        e.preventDefault();
        if (!this.input.value.trim())
            return;
        alert("add Todo: " + this.input.value);
        this.input.value = '';
    };

    render() {
        return (
            <div>
                <input ref={(node) => this.input = node}/>
                <button type="submit" onClick={this.handleSubmit}>
                    Add Todo
                </button>
            </div>
        );
    }
}

export default AddTodo;