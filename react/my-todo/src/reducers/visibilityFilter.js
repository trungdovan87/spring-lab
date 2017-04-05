import {FILTER as filter, TYPE as type} from '../actions'

const visibilityFilter = (state = filter.SHOW_ALL, action) => {
    switch (action.type) {
        case type.SET_VISIBILITY_FILTER:
            return action.filter;
        default:
            return state;
    }
};

export default visibilityFilter;