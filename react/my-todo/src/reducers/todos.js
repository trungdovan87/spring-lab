import {TYPE} from '../actions'

let todo = (state, action) => {
    switch (action.type) {
        case TYPE.ADD_TODO:
            return {
                id: action.id,
                text: action.text,
                completed: false
            }
        default:
            return state;
    }
}

const todos = (state = [], action) => {
    console.log("state, action: ");
    console.log(state);
    console.log(action);
    switch (action.type) {
        case TYPE.ADD_TODO:
            return [
                ...state,
                todo(undefined, action)
            ];
        default:
            return false;
    }
};

export default todos;