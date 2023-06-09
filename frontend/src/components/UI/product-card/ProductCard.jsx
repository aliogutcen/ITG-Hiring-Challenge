import "./productcard.scss";
import productImg from "../../../assets/car.svg";
import { Link } from "react-router-dom";
import { useDispatch } from "react-redux";
import { cartActions } from "../../../store/shopping-cart/cartSlice";
const ProductCard = (props) => {
  const { id, productName, image01, stock, price } = props.item;
  const dispatch = useDispatch();
  const addToCart = () => {
    dispatch(
      cartActions.addItem({
        id,
        productName,
        image01,
        stock,
        price,
      })
    );
  };
  return (
    <div className="product__item">
      <div className="product__img">
        <img src={image01} alt="" className="w-100 product__card-img" />
      </div>
      <div className="product__content">
        <h5>
          <Link to={`/products/${id}`}>{productName}</Link>
        </h5>
        <div className="product__price d-flex align-items-center justify-content-around">
          <span>${price}</span>
          <button className="add__to_cart" onClick={addToCart}>
            Add To Cart
          </button>
        </div>
      </div>
    </div>
  );
};

export default ProductCard;
