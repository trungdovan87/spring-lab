import {connect} from "react-redux";
import {addTodo} from "../actions";
import AddTodoView from "../components/AddTodoView"

// const mapDispatchToProps = (dispatch, props) => ({
//     onAddTodo: input => {
//         dispatch(addTodo(input));
//     }
// });

const mapDispatchToProps = {
    onAddTodo: addTodo
};


const AddTodoContainer = connect(undefined, mapDispatchToProps)(AddTodoView);

export default AddTodoContainer;