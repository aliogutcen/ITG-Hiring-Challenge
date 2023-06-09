import axios from "axios";

const GET_ALL_PRODUCT = "http://localhost:8080/api/v1/products";
const GET_PRODUCT = "http://localhost:8080/api/v1/products/";
class ProductService {
  products() {
    return axios.get(GET_ALL_PRODUCT);
  }

  productDetail(id) {
    return axios.get(GET_PRODUCT + id);
  }
}

export default new ProductService();
