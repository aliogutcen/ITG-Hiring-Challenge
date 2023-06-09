import "./home.scss";
import { useState, useEffect } from "react";
import Helmet from "../../components/Helmet/Helmet";
import { Container, Row, Col, ListGroup, ListGroupItem } from "reactstrap";
import carHero from "../../assets/car.svg";
import { Link } from "react-router-dom";
import Category from "../../components/UI/category/Category";
import Feature from "../../components/UI/feature/Feature";
import ProductCard from "../../components/UI/product-card/ProductCard";
import category1 from "../../assets/hamburger.png";
import products from "../../assets/fake-data/products";
import whyImg from "../../assets/location.png";
const Home = () => {
  return (
    <Helmet title="Home">
      <section className="home">
        <Container>
          <Row className="mt-2">
            <Col lg="6" md="6">
              <div className="hero__content">
                <h5 className="mb-3">Easy way to make an order</h5>
                <h1 className="mb-4 hero__title">
                  <span>Needed?</span> Just wait a second <span>your door</span>
                </h1>
                <p>
                  Lorem ipsum dolor, sit amet consectetur adipisicing elit.
                  Aperiam, vel.
                </p>

                <div className="hero__btns d-flex align-items-center gap-5 mt-5">
                  <button className="order_btn d-flex align-items-center justify-content-between">
                    <span>Shop now</span> <i class="ri-arrow-right-s-line"></i>
                  </button>
                  <button className="all__products-btn d-flex align-items-center justify-content-between">
                    <Link to="/products"> All Products</Link>
                  </button>
                </div>
              </div>
            </Col>
            <Col lg="6" md="6">
              <div className="hero__img">
                <img src={carHero} alt="hero-img" className="hero__image" />
              </div>
            </Col>
          </Row>
        </Container>
      </section>
      <section className="pt-6 category-section">
        <Category />
      </section>

      <section className="pt-4">
        <Container>
          <Row>
            <Col lg="12" className="text-center">
              <h5 className="mb-3">Easy way to make an order</h5>
              <h1 className="mb-4 hero__title">
                <span>Needed?</span> Just wait a second
              </h1>
              <h1>
                <span>your door</span>
              </h1>
            </Col>
          </Row>
        </Container>
      </section>
      <section className="pt-0 section__futured">
        <Feature />
      </section>
      <section>
        <Container>
          <Row>
            <Col lg="12" className="text-center">
              <h2>Populer Products</h2>
            </Col>
            {products.map((item) => (
              <Col lg="3" md="4" key={item.id} className="mt-5">
                <ProductCard item={item} />
              </Col>
            ))}
          </Row>
        </Container>
      </section>

      <section className="why__choose-us">
        <Container className="d-flex align-content-center">
          <Row>
            <Col lg="6" md="6">
              <img src={whyImg} alt="why-tasty-treat" className="w-100" />
            </Col>

            <Col lg="6" md="6">
              <div>
                <h2 className="tasty__treat-title mb-4">
                  Why <span>Logo Us?</span>
                </h2>
                <p className="tasty__treat-desc">
                  Lorem ipsum dolor sit amet consectetur adipisicing elit.
                  Dolorum, minus. Tempora reprehenderit a corporis velit,
                  laboriosam vitae ullam, repellat illo sequi odio esse iste
                  fugiat dolor, optio incidunt eligendi deleniti!
                </p>

                <ListGroup className="mt-4">
                  <ListGroupItem className="border-0 ps-0">
                    <p className=" choose__us-title d-flex align-items-center gap-2 ">
                      <i class="ri-checkbox-circle-line"></i>Quality product
                    </p>
                    <p className="choose__us-desc">
                      Lorem ipsum, dolor sit amet consectetur adipisicing elit.
                      Quia, voluptatibus.
                    </p>
                  </ListGroupItem>

                  <ListGroupItem className="border-0 ps-0">
                    <p className="choose__us-title d-flex align-items-center gap-2 ">
                      <i class="ri-checkbox-circle-line"></i> Quality support
                    </p>
                    <p className="choose__us-desc">
                      Lorem ipsum dolor sit amet consectetur adipisicing elit.
                      Qui, earum.
                    </p>
                  </ListGroupItem>
                  <ListGroupItem className="border-0 ps-0">
                    <p className="choose__us-title d-flex align-items-center gap-2 ">
                      <i class="ri-checkbox-circle-line"></i> Quality shipping
                    </p>
                    <p className="choose__us-desc">
                      Lorem ipsum dolor sit amet consectetur adipisicing elit.
                      Qui, earum.
                    </p>
                  </ListGroupItem>
                </ListGroup>
              </div>
            </Col>
          </Row>
        </Container>
      </section>
    </Helmet>
  );
};

export default Home;
