import React from "react";

const Link = ({active, children, onClick}) => {
    if (active) {
        return <span>{children}</span>
    } else {
        return (
            <a href='http://google.com'
               onClick={e => {
                   alert("click Link: " + children);
                   e.preventDefault();
               }}
            >
                {children}
            </a>
        )
    }
}

Link.propTypes = {
    active: React.PropTypes.bool.isRequired
}

export default Link;