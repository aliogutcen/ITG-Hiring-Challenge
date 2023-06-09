import "./login.scss";
import Helmet from "../../components/Helmet/Helmet";
import CommonSection from "../../components/UI/common-section/CommonSection";
import { Container, Row, Col } from "reactstrap";
import { Link } from "react-router-dom";
import { useState } from "react";
import AuthService from "../../service/AuthService";

const Login = () => {
  const [login, setLogin] = useState({
    email: "",
    password: "",
  });

  const handleSubmit = (e) => {
    e.preventDefault();
    AuthService.login(login).then(
      (response) => {
        console.log(response);
        localStorage.setItem("token", response.data.token);
        window.location.replace("/");
      },
      () => {}
    );
  };

  return (
    <Helmet title="login">
      <CommonSection title="login" />
      <section className="login">
        <Container>
          <Row>
            <Col lg="6" md="6" sm="12" className="m-auto text-center">
              <form action="" className="form mb-5" onSubmit={handleSubmit}>
                <div className="form__group">
                  <input
                    type="text"
                    name=""
                    placeholder="Email"
                    required
                    onChange={(e) => {
                      {
                        setLogin({ ...login, email: e.target.value });
                      }
                    }}
                  />
                </div>
                <div className="form__group">
                  <input
                    type="password"
                    name=""
                    placeholder="Password"
                    required
                    onChange={(e) => {
                      {
                        setLogin({ ...login, password: e.target.value });
                      }
                    }}
                  />
                </div>
                <button>Login</button>
              </form>
              <Link to="/register">Create an Account</Link>
            </Col>
          </Row>
        </Container>
      </section>
    </Helmet>
  );
};

export default Login;
