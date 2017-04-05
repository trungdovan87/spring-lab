let nextTodoId = 0;

export const TYPE = {
    ADD_TODO: 'ADD_TODO',
    SET_VISIBILITY_FILTER: 'SET_VISIBILITY_FILTER',
    TOGGLE_TODO: 'TOGGLE_TODO'
};

export const addTodo = (text) => ({
    type: TYPE.ADD_TODO,
    id: nextTodoId++,
    text
});

export const  setVisibilityFilter = (filter) => ({
    type: TYPE.SET_VISIBILITY_FILTER,
    filter
});

export const toggleTodo = (id) => ({
    type: TYPE.TOGGLE_TODO,
    id
});