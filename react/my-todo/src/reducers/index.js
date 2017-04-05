import {combineReducers} from 'redux'
import todos from './todos'

const todoApp = combineReducers({
    todos
});

// const todoApp = (state, action) => {
//     return {...state, todos : todos(state, action)}
// }

export default todoApp;