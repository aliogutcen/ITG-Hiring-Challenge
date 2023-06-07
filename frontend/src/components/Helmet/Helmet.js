import React from "react";

const Helmet = (props) => {
  document.title = "Car Part - " + props.title;
  return <div>{props.children}</div>;
};

export default Helmet;
