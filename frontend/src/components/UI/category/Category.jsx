import "./category.scss";
import { Container, Row, Col } from "reactstrap";
import carTyre from "../../../assets/tyre.png";
const categoryData = [
  {
    display: "Tyre",
    img: carTyre,
  },
  {
    display: "Oil",
    img: carTyre,
  },
  {
    display: "Engine",
    img: carTyre,
  },
  {
    display: "Engine",
    img: carTyre,
  },
];

const Category = () => {
  return (
    <Container>
      <Row>
        {categoryData.map((item, index) => (
          <Col lg="3" md="3">
            <div className="category__item">
              <div className="category__img">
                <img src={item.img} alt="" className="category__img" />
              </div>
              <h6>{item.display}</h6>
            </div>
          </Col>
        ))}
      </Row>
    </Container>
  );
};

export default Category;
