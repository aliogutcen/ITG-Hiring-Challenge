import React, { useState, useRef } from "react";
import Cards from "react-credit-cards-2";
import "react-credit-cards-2/dist/es/styles-compiled.css";

const PaymentForm = () => {
  const numberInputRef = useRef(null); // number input için bir ref oluşturun

  const [state, setState] = useState({
    number: "",
    expiry: "",
    cvc: "",
    name: "",
    focus: "",
  });

  const handleInputChange = (evt) => {
    const { name, value } = evt.target;

    setState((prev) => ({ ...prev, [name]: value }));
  };

  const handleInputFocus = (evt) => {
    setState((prev) => ({ ...prev, focus: evt.target.name }));
  };

  const handleCardClick = () => {
    numberInputRef.current.focus(); // Kart'a tıklanınca number input'a focus ayarla
  };

  return (
    <div className="car-side d-flex">
      <Cards
        number={state.number}
        expiry={state.expiry}
        cvc={state.cvc}
        name={state.name}
        focused={state.focus}
        onClick={handleCardClick} // Kart'a tıklama eventini ekle
      />
      <form className="d-flex flex-column">
        <input
          type="number"
          name="number"
          className="form-control mb-2"
          placeholder="Card Number"
          value={state.number}
          onChange={handleInputChange}
          onFocus={handleInputFocus}
          ref={numberInputRef} // number input'a ref'i atayın
        />

        <input
          type="text"
          name="name"
          className="form-control mb-2"
          placeholder="Name"
          required
          onChange={handleInputChange}
          onFocus={handleInputFocus}
        />
        <input
          type="tel"
          name="expiry"
          className="form-control mb-2"
          placeholder="Valid Thru"
          pattern="\d\d/\d\d"
          required
          onChange={handleInputChange}
          onFocus={handleInputFocus}
        />
        <input
          type="tel"
          name="cvc"
          className="form-control mb-2"
          placeholder="CVC"
          pattern="\d{3,4}"
          required
          onChange={handleInputChange}
          onFocus={handleInputFocus}
        />
      </form>
    </div>
  );
};

export default PaymentForm;
