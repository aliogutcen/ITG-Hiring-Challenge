import "./checkout.scss";
import { useState, useEffect } from "react";
import { Container, Row, Col } from "reactstrap";
import CommonSection from "../../components/UI/common-section/CommonSection";
import Helmet from "../../components/Helmet/Helmet";
import { useDispatch, useSelector } from "react-redux";
import { Link, useNavigate } from "react-router-dom";
import UserService from "../../service/UserService";
import OrderService from "../../service/OrderService";

import CreditCard from "../../components/credit-card/PaymentForm";

const Checkout = () => {
  const [token, setToken] = useState(null);
  const navigate = useNavigate();
  const [id, setId] = useState(localStorage.getItem("userId"));
  const [userInfo, setUserInfo] = useState({});

  const cartProducts = useSelector((state) => state.cart.cartItems);

  useEffect(() => {
    const storedToken = localStorage.getItem("token");
    if (storedToken) {
      setToken(storedToken);
    } else {
      navigate("/login");
    }
  }, [navigate]);

  useEffect(() => {
    if (token) {
      UserService.profile(id, token).then((response) => {
        setUserInfo({ ...response.data });
      });
    }
  }, [id, token]);

  const totalAmount = useSelector((state) => state.cart.totalAmount);
  const [order, setOrder] = useState({
    userId: id,
    items: cartProducts,
    address: "",
    phone: "",
    amount: totalAmount,
  });

  const handleSubmit = (e) => {
    e.preventDefault();

    if (token) {
      OrderService.order(order, token).then((response) => {
        console.log(response);
      });
    }
  };

  return (
    <Helmet title="Checkout">
      <CommonSection title="Checkout" />
      {token ? (
        <Container className="checkout">
          <form action="" className="form mb-5" onSubmit={handleSubmit}>
            <Row>
              <Col lg="6" md="12" sm="12" xs="12">
                <div className="form__group">
                  <input
                    className="mb-3"
                    type="email"
                    name=""
                    placeholder="Email"
                    required
                    value={userInfo.email}
                  />
                </div>
                <div className="form__group">
                  <input
                    className="mb-3"
                    type="text"
                    name=""
                    placeholder="Name"
                    required
                    value={userInfo.firstname}
                  />
                </div>
                <div className="form__group">
                  <input
                    className="mb-3"
                    type="text"
                    name=""
                    placeholder="Surname"
                    required
                    value={userInfo.lastname}
                  />
                </div>
                <div className="form__group">
                  <input
                    className="mb-3"
                    type="text"
                    name=""
                    placeholder="Address"
                    required
                    onChange={(e) => {
                      {
                        setOrder({ ...order, address: e.target.value });
                      }
                    }}
                  />
                </div>
                <div className="form__group">
                  <input
                    className="mb-3"
                    type="text"
                    name=""
                    placeholder="Phone Number"
                    required
                    onChange={(e) => {
                      {
                        setOrder({ ...order, phone: e.target.value });
                      }
                    }}
                  />
                </div>
              </Col>
              <Col lg="6" md="12" sm="12" xs="12">
                <CreditCard />
              </Col>
              <Col lg="6" md="12" sm="12" xs="12">
                <div>
                  <h6 className="mt-3">
                    Subtotal :
                    <span className="subtotal-checkout">${totalAmount}</span>
                  </h6>
                </div>
                <div className="mt-3">
                  <button className="check-btn">Checkout</button>
                </div>
              </Col>
            </Row>
          </form>
        </Container>
      ) : (
        <Link to="/login">
          <i class="ri-user-line"></i>
        </Link>
      )}
    </Helmet>
  );
};

export default Checkout;
