import axios from "axios";

const GET_USER_INFO = "http://localhost:8080/api/v1/users/";

class UserService {
  profile(id, token) {
    return axios.get(GET_USER_INFO + id, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
  }

  my_order(id, token) {
    const GET_MY_ORDER = `http://localhost:8080/api/v1/users/${id}/orders`;

    return axios.get(GET_MY_ORDER, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
  }
}

export default new UserService();
