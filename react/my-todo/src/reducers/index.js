import {combineReducers} from 'redux'
import todos from './todos'
import visibilityFilter from './visibilityFilter'

const todoApp = combineReducers({
    todos,
    visibilityFilter
});

// const todoApp = (state, action) => {
//     return {...state, todos : todos(state, action)}
// }

export default todoApp;