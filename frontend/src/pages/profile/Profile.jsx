import "./profile.scss";

import CommonSection from "../../components/UI/common-section/CommonSection";
import Helmet from "../../components/Helmet/Helmet";
import { useSelector, useDispatch } from "react-redux";
import { Container, Row, Col } from "reactstrap";
import { cartActions } from "../../store/shopping-cart/cartSlice";
import { Link } from "react-router-dom";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import UserService from "../../service/UserService";
const Profile = () => {
  const [token, setToken] = useState(null);
  const navigate = useNavigate();
  const [id, setId] = useState(localStorage.getItem("userId"));
  const [myOrder, setMyOrder] = useState([{}]);

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
      UserService.my_order(id, token).then((response) => {
        console.log(response);
        setMyOrder(response.data);
      });
    }
  }, [id, token]);

  return (
    <Helmet title="Cart">
      <CommonSection title="Your Profile" />
      <section className="cart-area">
        <Container>
          <Row>
            <Col lg="12" md="12" sm="12" xs="12">
              <h4>Last Orders</h4>
              <table class="table table-striped">
                <thead>
                  <tr>
                    <th scope="col">#</th>
                    <th scope="col">Amount</th>
                    <th scope="col">Address</th>
                    <th scope="col">Delivery Status</th>
                  </tr>
                </thead>

                <tbody>
                  {myOrder.map((order, index) => (
                    <tr key={index}>
                      <th scope="row">{index + 1}</th>
                      <td>{order.amount ? order.amount : "N/A"}</td>
                      <td>{order.address ? order.address : "N/A"}</td>{" "}
                      <td>
                        {order.etrackingStatus ? order.etrackingStatus : "N/A"}
                      </td>{" "}
                    </tr>
                  ))}
                </tbody>
              </table>
            </Col>
          </Row>
        </Container>
      </section>
    </Helmet>
  );
};

export default Profile;
