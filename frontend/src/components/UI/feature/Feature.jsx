import React from "react";
import Quick from "../../../assets/quick.png";
import { Container, Row, Col } from "reactstrap";
import "./feature.scss";
const featureData = [
  {
    title: "Quick Delivery",
    img: Quick,
    desc: "Lorem ipsum dolor sit amet consectetur adipisicing elit. Ipsa, cumque? ",
  },
  {
    title: "Super Packaging",
    img: Quick,
    desc: "Lorem ipsum dolor sit amet consectetur adipisicing elit. Ipsa, cumque? ",
  },
  {
    title: "Easy Pick Up",
    img: Quick,
    desc: "Lorem ipsum dolor sit amet consectetur adipisicing elit. Ipsa, cumque? ",
  },
];

const Feature = () => {
  return (
    <Container>
      <Row>
        {featureData.map((item, index) => (
          <Col lg="4" md="4" className="dene">
            <div className="feature">
              <div className="feature__img">
                <img src={item.img} alt="" className="feature__img" />
              </div>
              <div>
                <h6>{item.title}</h6>
                <p>{item.desc}</p>
              </div>
            </div>
          </Col>
        ))}
      </Row>
    </Container>
  );
};

export default Feature;
