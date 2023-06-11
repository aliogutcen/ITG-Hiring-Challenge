import "./register.scss";
import Helmet from "../../components/Helmet/Helmet";
import CommonSection from "../../components/UI/common-section/CommonSection";
import { Container, Row, Col } from "reactstrap";
import { Link } from "react-router-dom";
import { useState } from "react";
import AuthService from "../../service/AuthService";

import Swal from "sweetalert2";
const Register = () => {
  const [register, setRegister] = useState({
    email: "",
    password: "",
    firstname: "",
    lastname: "",
    role: "USER",
  });
  const [errorMsg, setErrorMsg] = useState("");

  const handleSubmit = (e) => {
    console.log(register);
    e.preventDefault();
    AuthService.register(register)
      .then((response) => {
        if (response.status === 201) {
          const Toast = Swal.mixin({
            toast: true,
            position: "top-end",
            showConfirmButton: false,
            timer: 3000,
            timerProgressBar: true,
            didOpen: (toast) => {
              toast.addEventListener("mouseenter", Swal.stopTimer);
              toast.addEventListener("mouseleave", Swal.resumeTimer);
            },
          });

          Toast.fire({
            icon: "success",
            title: "Signed in successfully",
          });
        }

        window.location.replace("/");
      })
      .catch((error) => {
        // Handle error here
        if (error.response && error.response.data.code) {
          setErrorMsg(error.response.data.message);
        }
      });
  };

  return (
    <Helmet title="login">
      <CommonSection title="Register" />
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
                        setRegister({ ...register, email: e.target.value });
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
                        setRegister({ ...register, password: e.target.value });
                      }
                    }}
                  />
                </div>
                <div className="form__group">
                  <input
                    type="text"
                    name=""
                    placeholder="Firstname"
                    required
                    onChange={(e) => {
                      {
                        setRegister({ ...register, firstname: e.target.value });
                      }
                    }}
                  />
                </div>
                <div className="form__group">
                  <input
                    type="text"
                    name=""
                    placeholder="Surname"
                    required
                    onChange={(e) => {
                      {
                        setRegister({ ...register, lastname: e.target.value });
                      }
                    }}
                  />
                </div>
                <button>Login</button>
              </form>
            </Col>
          </Row>
        </Container>
      </section>
    </Helmet>
  );
};

export default Register;
