import React from "react";
import { ListGroupItem } from "reactstrap";

import "./cartitem.scss";

import { useDispatch } from "react-redux";
import { cartActions } from "../../../store/shopping-cart/cartSlice";

const CartItem = ({ item }) => {
  const { id, productName, price, image01, quantity, totalPrice } = item;

  const dispatch = useDispatch();

  const incrementItem = () => {
    dispatch(
      cartActions.addItem({
        id,
        productName,
        price,
        image01,
      })
    );
  };

  const decreaseItem = () => {
    dispatch(cartActions.removeItem(id));
  };

  const deleteItem = () => {
    dispatch(cartActions.deleteItem(id));
  };

  return (
    <ListGroupItem className="border-0 cart__item">
      <div className="cart__item-info">
        <div className="cart__item-left">
          <img src={image01} alt="product-img" />
          <div className="cart__product-info w-100 d-flex align-items-center gap-4 justify-content-between">
            <div>
              <h6 className="cart__product-title">{productName}</h6>
              <p className="cart__product-price">
                {quantity}x <span>${totalPrice}</span>
              </p>
              <div className="increase__decrease-btn">
                <span className="increase__btn" onClick={incrementItem}>
                  <i class="ri-add-line"></i>
                </span>
                <span className="quantity">{quantity}</span>
                <span className="decrease__btn" onClick={decreaseItem}>
                  <i class="ri-subtract-line"></i>
                </span>
              </div>
            </div>
          </div>
        </div>
        <div>
          <span className="delete__btn" onClick={deleteItem}>
            <i class="ri-close-line"></i>
          </span>
        </div>
      </div>
    </ListGroupItem>
  );
};

export default CartItem;
