import React from "react";
import FilterLink from "../containers/FilterLink";
import {FILTER} from '../actions'

const Footer = () => (
    <p>
        Show: {' '}
        <FilterLink filter={FILTER.SHOW_ALL}>
            All
        </FilterLink> {', '}

        <FilterLink filter={FILTER.SHOW_ACTIVE}>
            Active
        </FilterLink> {', '}

        <FilterLink filter={FILTER.SHOW_COMPLETED}>
            Completed
        </FilterLink>
    </p>
);

export default Footer;