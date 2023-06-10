import axios from "axios";

const LOGIN = "http://localhost:8080/api/v1/auth/authenticate";
const REGISTER = "http://localhost:8080/api/v1/auth/register";
class AuthService {
  login(login) {
    return axios
      .post(LOGIN, login)
      .then((response) => {
        return response;
      })
      .catch((error) => {
        if (error.response) {
        } else if (error.request) {
          console.log(error.request);
        } else {
          console.log("Error", error.message);
        }
        throw error;
      });
  }

  register(register) {
    return axios
      .post(REGISTER, register)
      .then((response) => {
        return response;
      })
      .catch((error) => {
        if (error.response) {
        } else if (error.request) {
          console.log(error.request);
        } else {
          console.log("Error", error.message);
        }
        throw error;
      });
  }
}

export default new AuthService();
