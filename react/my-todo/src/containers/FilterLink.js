import {connect} from "react-redux";
import Link from "../components/Link";
import {PropTypes} from 'react'
import {setVisibilityFilter} from "../actions";

const mapStateToProps = (state, ownProps) => {
    console.log("Filter Link, map State");
    console.log(state);
    console.log(ownProps);
    return {
        active: ownProps.filter === state.visibilityFilter
    }
};

const mapDispatchToProps = (dispatch, ownProps) => ({
    onClick: () => {
        dispatch(setVisibilityFilter(ownProps.filter))
    }
})

const FilterLink = connect(mapStateToProps, mapDispatchToProps)(Link);

FilterLink.propTypes = {
    filter: PropTypes.string.isRequired
};

export default FilterLink;