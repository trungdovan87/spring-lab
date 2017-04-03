import React from "react";
import Link from "./Link";

const Footer = () => (
    <p>
        Show: {' '}
        <Link active={true} >
            All
        </Link> {', '}

        <Link active={false}>
            Active
        </Link> {', '}

        <Link active={false}>
            Completed
        </Link>
    </p>
);

export default Footer;