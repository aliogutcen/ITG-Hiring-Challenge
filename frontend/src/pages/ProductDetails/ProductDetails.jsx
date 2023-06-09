import "./productdetails.scss";
import { useState, useEffect } from "react";
import products from "../../assets/fake-data/products";
import { useParams } from "react-router-dom";
import Helmet from "../../components/Helmet/Helmet";
import CommonSection from "../../components/UI/common-section/CommonSection";
import { Container, Row, Col } from "reactstrap";
import productImg from "../../assets/product_01.1.jpg";
import { useDispatch } from "react-redux";
import { cartActions } from "../../store/shopping-cart/cartSlice";

import ProductCard from "../../components/UI/product-card/ProductCard";

const ProductDetails = () => {
  const [tab, setTab] = useState("desc");
  const { id } = useParams();
  const product = products.find((product) => product.id === id);
  const [previewImg, setPreviewImg] = useState(product.image01);
  const { title, price, category, desc, image01 } = product;
  const dispatch = useDispatch();

  const relatedProduct = products
    .filter((item) => category === item.category)
    .slice(0, 4);

  const addItem = () => {
    dispatch(
      cartActions.addItem({
        id,
        title,
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
      <CommonSection title={title} />

      <section className="product_details">
        <Container>
          <Row>
            <Col lg="2" md="2">
              <div className="product__images">
                <div
                  className="img__item mb-3"
                  onClick={() => setPreviewImg(product.image01)}
                >
                  <img src={product.image01} alt="" className="w-50" />
                </div>
                <div className="img__item mb-3">
                  <img
                    src={product.image02}
                    alt=""
                    className="w-50"
                    onClick={() => setPreviewImg(product.image02)}
                  />
                </div>
                <div
                  className="img__item mb-3"
                  onClick={() => setPreviewImg(product.image03)}
                >
                  <img src={product.image03} alt="" className="w-50" />
                </div>
              </div>
            </Col>

            <Col lg="4" md="4">
              <div className="product__main-img">
                <img src={previewImg} alt="" className="w-100" />
              </div>
            </Col>

            <Col lg="6" md="6">
              <div className="single__product-content">
                <div>
                  <h2 className="product__title mb-3">{title}</h2>
                  <span className="prodcut__price">
                    Price: <span>{price}</span>
                  </span>
                  <p className="category mb-5">
                    <span> {category}</span>
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
