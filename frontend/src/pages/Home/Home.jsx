import "./home.scss";
import Helmet from "../../components/Helmet/Helmet";
import { Container, Row, Col } from "reactstrap";
import carHero from "../../assets/car.svg";
import { Link } from "react-router-dom";
import Category from "../../components/UI/category/Category";
import Feature from "../../components/UI/feature/Feature";
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
      <section className="pt-6">
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
    </Helmet>
  );
};

export default Home;
