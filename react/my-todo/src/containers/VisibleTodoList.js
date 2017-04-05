import {connect} from "react-redux";
import TodoList from "../components/TodoList";
import {toggleTodo} from "../actions";

const getVisibleTodos = (todos, filter) => {
    return todos;
}
const mapStateToProps = (state) => ({
    todos: getVisibleTodos(state.todos, state.visibilityFilter)
});

const mapDispatchToProps = {
    onTodoClick: toggleTodo
};

// const mapDispatchToProps = (dispatch) => ({
//     onTodoClick: (id) => {
//         dispatch(toggleTodo(id));
//     }
// });

const VisibleTodoList = connect(
    mapStateToProps,
    mapDispatchToProps
)(TodoList);

export default VisibleTodoList;