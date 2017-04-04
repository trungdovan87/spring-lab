import React from 'react';

const Link = ({active, children, onClick}) => {
    if (active) {
        return <span>{children}</span>
    } else {
        return (
            <a href='http://24h.com.vn'
               onClick={e => {
                   e.preventDefault();
                   alert("hehe");

               }}
            >
                {children}
            </a>
        )
    }
};

Link.propTypes = {
    active: React.PropTypes.bool.isRequired
};

export default Link;