import axios from "axios";

const LOGIN = "http://localhost:8080/api/v1/auth/authenticate";
class AuthService {
  login(login) {
    return axios.post(LOGIN, login);
  }
}

export default new AuthService();
