import "./header.css";
import { Container } from "reactstrap";
import { NavLink, Link } from "react-router-dom";
import { useRef, useState } from "react";
const nav__links = [
  {
    display: "Home",
    path: "/home",
  },
  {
    display: "Products",
    path: "/products",
  },
  {
    display: "Cart",
    path: "/cart",
  },
];

const Header = () => {
  const menuRef = useRef(null);
  const [isMenuActive, setMenuActive] = useState(false);
  const toggleMenu = () => {
    menuRef.current.classList.toggle("show__menu");
    setMenuActive(!isMenuActive);
  };

  return (
    <header className="header">
      <Container>
        <div className="nav__wrapper d-flex align-items-center justify-content-between">
          <div className="logo">
            <h2>LOGO</h2>
          </div>
          <div className="navigation" ref={menuRef} onClick={toggleMenu}>
            <div className="menu d-flex align-content-center gap-5 ">
              {nav__links.map((item, index) => (
                <NavLink
                  onClick={toggleMenu}
                  to={item.path}
                  key={index}
                  className={(navClass) =>
                    navClass.isActive ? "active__menu" : ""
                  }
                >
                  {item.display}
                </NavLink>
              ))}
            </div>
          </div>
          <div className="nav__right d-flex align-items-center ">
            {!isMenuActive && (
              <span className="cart_icon">
                <i class="ri-shopping-basket-line"></i>
                <span className="cart__badge">2</span>
              </span>
            )}
            <span className="user">
              <Link to="/login">
                <i class="ri-user-line"></i>
              </Link>
            </span>
            <span className="mobile__menu" onClick={toggleMenu}>
              <i class="ri-menu-line"></i>
            </span>
          </div>
        </div>
      </Container>
    </header>
  );
};

export default Header;
