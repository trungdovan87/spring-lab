import {connect} from "react-redux";
import TodoList from "../components/TodoList";
import {toggleTodo, FILTER} from "../actions";

const getVisibleTodos = (todos, filter) => {
    switch (filter) {
        case FILTER.SHOW_ALL:
            return todos;
        case FILTER.SHOW_COMPLETED:
            return todos.filter(t => t.completed);
        case FILTER.SHOW_ACTIVE:
            return todos.filter(t => !t.completed);
        default:
            throw new Error('Unknown filter: ' + filter);
    }
};
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