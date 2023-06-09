import "./products.scss";

import { useState, useEffect } from "react";
import Helmet from "../../components/Helmet/Helmet";
import CommonSection from "../../components/UI/common-section/CommonSection";
import { Container, Row, Col } from "reactstrap";
import ProductCard from "../../components/UI/product-card/ProductCard";
import ReactPaginate from "react-paginate";
import ProductService from "../../service/ProductService";
const Products = () => {
  const [product, setProduct] = useState([]);

  useEffect(() => {
    ProductService.products().then((response) => {
      console.log(response);
      setProduct([...response.data]);
    });
    return () => {};
  }, []);

  const [searchTerm, setSearchTerm] = useState("");
  const [pageNumber, setPageNumber] = useState(0);
  const searchProduct = product.filter((item) => {
    if (searchTerm === "") return item;
    if (item.productName.toLowerCase().includes(searchTerm.toLowerCase()))
      return item;
  });
  const productPerPage = 12;
  const visitedPage = pageNumber * productPerPage;
  const displayPage = searchProduct.slice(
    visitedPage,
    visitedPage + productPerPage
  );

  const pageCount = Math.ceil(product.length / productPerPage);
  const changePage = ({ selected }) => {
    setPageNumber(selected);
  };

  return (
    <Helmet title="All Products">
      <CommonSection title="All Products" />
      <section>
        <Container>
          <Row>
            <Col lg="6" md="6" sm="6">
              <div className="search_widget">
                <input
                  type="text"
                  placeholder="I'm looking for"
                  value={searchTerm}
                  onChange={(e) => setSearchTerm(e.target.value)}
                />
                <span>
                  <i class="ri-search-line"></i>
                </span>
              </div>
            </Col>
            <Col lg="6" md="6" sm="6" className="mb-5">
              <div className="sorting__widget">
                <select className="w-50">
                  <option>Default</option>
                  <option value="ascending">Alphabetically, A-Z</option>
                  <option value="descending">Alphabetically, Z-A</option>
                  <option value="high-price">High Price</option>
                  <option value="low-price">Low Price</option>
                </select>
              </div>
            </Col>
            {displayPage.map((item) => (
              <Col lg="3" md="4" sm="6" xs="6" key={item.id}>
                <ProductCard item={item}></ProductCard>
              </Col>
            ))}

            <ReactPaginate
              pageCount={pageCount}
              onPageChange={changePage}
              previousLabel="Prev"
              nextLabel="Next"
              containerClassName="paginationBttns"
            />
          </Row>
        </Container>
      </section>
    </Helmet>
  );
};

export default Products;
