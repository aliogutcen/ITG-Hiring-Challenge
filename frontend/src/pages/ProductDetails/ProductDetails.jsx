import "./productdetails.scss";
import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import Helmet from "../../components/Helmet/Helmet";
import CommonSection from "../../components/UI/common-section/CommonSection";
import { Container, Row, Col } from "reactstrap";
import productImg from "../../assets/product_01.1.jpg";
import { useDispatch } from "react-redux";
import { cartActions } from "../../store/shopping-cart/cartSlice";
import ProductService from "../../service/ProductService";
import ProductCard from "../../components/UI/product-card/ProductCard";

const ProductDetails = () => {
  const { id } = useParams();

  const [product, setProduct] = useState([]);
  useEffect(() => {
    ProductService.products().then((response) => {
      setProduct([...response.data]);
    });
    return () => {};
  }, []);

  const [productDetail, setProductDetail] = useState([]);
  useEffect(() => {
    ProductService.products().then((response) => {
      setProductDetail(...response.data);
    });
    return () => {};
  }, []);

  const [previewImg, setPreviewImg] = useState(productDetail.image01);
  const { productName, price, category, desc, image01 } = productDetail;
  const dispatch = useDispatch();

  const relatedProduct = product
    .filter((item) => category === item.category)
    .slice(0, 4);

  const addItem = () => {
    dispatch(
      cartActions.addItem({
        id,
        productName,
        price,
        image01,
      })
    );
  };

  useEffect(() => {
    setPreviewImg(productImg);
  }, [product]);

  return (
    <Helmet title="Product Details">
      <CommonSection title={productDetail.productName} />
      <section className="product_details">
        <Container>
          <Row>
            <Col lg="2" md="2" sm="12" xs="12">
              <div className="product__images">
                <div
                  className="img__item mb-3"
                  onClick={() => setPreviewImg(productDetail.image01)}
                >
                  <img src={productDetail.image01} alt="" className="w-50" />
                </div>
                <div className="img__item mb-3">
                  <img
                    src={productDetail.image02}
                    alt=""
                    className="w-50"
                    onClick={() => setPreviewImg(productDetail.image02)}
                  />
                </div>
                <div
                  className="img__item mb-3"
                  onClick={() => setPreviewImg(productDetail.image03)}
                >
                  <img src={productDetail.image03} alt="" className="w-50" />
                </div>
              </div>
            </Col>
            <Col lg="4" md="4" sm="12" xs="12">
              <div className="product__main-img">
                <img src={productDetail.image01} alt="" className="w-100" />
              </div>
            </Col>

            <Col lg="6" md="6" sm="12" xs="12">
              <div className="single__product-content">
                <div>
                  <h2 className="product__title mb-3">{productName}</h2>
                  <span className="prodcut__price">
                    Price: <span>{price}</span>
                  </span>
                  <p className="category mb-5">
                    <span> Category: {productDetail.categoryName}</span>
                  </p>
                </div>

                <div>
                  <button onClick={addItem} className="add__to_cart">
                    Add to cart
                  </button>
                </div>
              </div>
            </Col>
            <Col lg="12">
              <div className="tabs">
                <h6>Description</h6>
              </div>
              <div className="tab__content">
                <p>{desc}</p>
              </div>
            </Col>

            <Col lg="12" className="mb-3 mt-2">
              <h2>You might also like</h2>
            </Col>
            {relatedProduct.map((item) => (
              <Col lg="3" md="4" sm="6" xs="6" key={item.id}>
                <ProductCard item={item} />
              </Col>
            ))}
          </Row>
        </Container>
      </section>
    </Helmet>
  );
};

export default ProductDetails;
