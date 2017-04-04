import React from 'react'
import { connect } from 'react-redux'
import { addTodo } from '../actions'
import AddTodoView from "../components/AddTodoView";

const handleAddTodo = () => {

};

const mapStateToProps = (state, ownProps) => ({
    onAddTodo: () => {
        alert("add todo: state");
    }
});

const mapDispatchToProps = (dispatch, ownProps) => ({
    onAddTodo: () => {
        alert("add todo: dispatch");
        dispatch(addTodo(text));
    }
});


const AddTodoTmp = () => (
    <AddTodoView/>
);

export const AddTodo = connect(mapStateToProps, mapDispatchToProps)(AddTodoTmp);