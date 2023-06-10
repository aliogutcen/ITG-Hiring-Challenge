import axios from "axios";

const CREATE_ORDER = "http://localhost:8080/api/v1/orders";
class OrderService {
  order(order, token) {
    return axios.post(CREATE_ORDER, order, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
  }
}

export default new OrderService();
