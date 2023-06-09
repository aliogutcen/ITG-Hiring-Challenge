import axios from "axios";

const GET_ALL_CATEGORY = "http://localhost:8080/api/v1/categories";
class CategoryService {
  categories() {
    return axios.get(GET_ALL_CATEGORY);
  }
}

export default new CategoryService();
