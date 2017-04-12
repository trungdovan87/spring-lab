let nextTodoId = 0;

export const TYPE = {
    ADD_TODO: 'ADD_TODO',
    SET_VISIBILITY_FILTER: 'SET_VISIBILITY_FILTER',
    TOGGLE_TODO: 'TOGGLE_TODO'
};

export const FILTER = {
    SHOW_ALL: 'SHOW_ALL',
    SHOW_ACTIVE: 'SHOW_ACTIVE',
    SHOW_COMPLETED: 'SHOW_COMPLETED'
};


export const addTodo = (text) => ({
    type: TYPE.ADD_TODO,
    id: nextTodoId++,
    text
});

export const addTodoAsync = (text) =>
    (dispatch, getState) => {        
        setTimeout( 
            () => dispatch(addTodo(text))
            , 2000);        
    }

export const  setVisibilityFilter = (filter) => ({
    type: TYPE.SET_VISIBILITY_FILTER,
    filter
});

export const toggleTodo = (id) => ({
    type: TYPE.TOGGLE_TODO,
    id
});