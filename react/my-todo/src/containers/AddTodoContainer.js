import {connect} from "react-redux";
import {addTodo, addTodoAsync} from "../actions";
import AddTodoView from "../components/AddTodoView"

const mapDispatchToProps = (dispatch, ownProps) => ({
    onAddTodo: input => {
        dispatch(addTodo(input));
    },

    onAddTodoAsync: input => {
        dispatch(addTodoAsync(input));
    }
});

// Other declaration

// const mapDispatchToProps = {
//     onAddTodo: addTodo
// };


const AddTodoContainer = connect(undefined, mapDispatchToProps)(AddTodoView);

export default AddTodoContainer;