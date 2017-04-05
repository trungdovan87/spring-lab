import {TYPE} from "../actions";

let addTodo = (todos, action) => ([ ...todos, {
    id: action.id,
    text: action.text,
    completed: false
}]);

let toggle = (stateTodos, action) => {
  let result = [...stateTodos];
  for (let todo of result) {
      if (todo.id === action.id) {
          todo.completed = !todo.completed;
      }
  }
  return result;
};

const todos = (state = [], action) => {
    console.log("state, action: ");
    console.log(state);
    console.log(action);
    switch (action.type) {
        case TYPE.ADD_TODO:
            return addTodo(state, action);
        case TYPE.TOGGLE_TODO:
            return toggle(state, action);
        default:
            return state;
    }
};

export default todos;