import "./category.scss";
import { Container, Row, Col } from "reactstrap";
import CategoryService from "../../../service/CategoryService";
import { useState, useEffect } from "react";
const Category = () => {
  const [category, setCategory] = useState([]);

  useEffect(() => {
    CategoryService.categories().then((response) => {
      setCategory([...response.data]);
    });
    return () => {};
  }, []);

  return (
    <Container>
      <Row>
        {category.map((category, index) => (
          <Col lg="3" md="3" key={index}>
            <div className="category__item">
              <div className="category__img"></div>
              <h6>{category.categoryName}</h6>
            </div>
          </Col>
        ))}
      </Row>
    </Container>
  );
};

export default Category;
