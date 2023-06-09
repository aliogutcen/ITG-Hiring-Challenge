import "./header.css";
import { Container } from "reactstrap";
import { NavLink, Link } from "react-router-dom";
import { useRef, useState, useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import { cartUiActions } from "../../store/shopping-cart/cartUiSlice";
import {
  Dropdown,
  DropdownToggle,
  DropdownMenu,
  DropdownItem,
} from "reactstrap";
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
  const headerRef = useRef(null);
  const [isMenuActive, setMenuActive] = useState(false);
  const dispatch = useDispatch();
  const [dropdownOpen, setDropdownOpen] = useState(false);
  const toggle = () => setDropdownOpen((prevState) => !prevState);
  const toggleMenu = () => {
    menuRef.current.classList.toggle("show__menu");
  };
  const toggleCart = () => {
    dispatch(cartUiActions.toggle());
  };
  useEffect(() => {
    const onScroll = () => {
      if (headerRef.current) {
        if (
          document.body.scrollTop > 80 ||
          document.documentElement.scrollTop > 80
        ) {
          headerRef.current.classList.add("header__shrink");
        } else {
          headerRef.current.classList.remove("header__shrink");
        }
      }
    };

    window.addEventListener("scroll", onScroll);

    return () => window.removeEventListener("scroll", onScroll);
  }, []);
  const totalQuantity = useSelector((state) => state.cart.totalQuantity);

  const [token, setToken] = useState(null);

  useEffect(() => {
    const storedToken = localStorage.getItem("token");
    if (storedToken) {
      setToken(storedToken);
    }
  }, []);

  return (
    <header className="header" ref={headerRef}>
      <Container>
        <div className="nav__wrapper d-flex align-items-center justify-content-between">
          <div className="logo">
            <h2 className="logo">LOGO</h2>
          </div>
          <div className="navigation" ref={menuRef} onClick={toggleMenu}>
            <div className="menu d-flex align-content-center gap-5 ">
              {nav__links.map((item, index) => (
                <NavLink
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
              <span className="cart_icon" onClick={toggleCart}>
                <i class="ri-shopping-basket-line"></i>
                <span className="cart__badge">{totalQuantity}</span>
              </span>
            )}
            <span className="user">
              {token ? (
                <Dropdown isOpen={dropdownOpen} toggle={toggle}>
                  <DropdownToggle
                    tag="span"
                    data-toggle="dropdown"
                    aria-expanded={dropdownOpen}
                  >
                    <i class="ri-user-line"></i>
                  </DropdownToggle>
                  <DropdownMenu>
                    <DropdownItem>
                      <Link to="/profile">Profile</Link>
                    </DropdownItem>
                    <DropdownItem onClick={logout}>Logout</DropdownItem>
                  </DropdownMenu>
                </Dropdown>
              ) : (
                <Link to="/login">
                  <i class="ri-user-line"></i>
                </Link>
              )}
            </span>
            <span className="mobile__menu" onClick={toggleMenu}>
              {isMenuActive ? (
                <i className="ri-close-line"></i> // Kapatma işareti olarak kullanılabilir SVG ikonu
              ) : (
                <i className="ri-menu-line"></i> // Açma işareti olarak kullanılabilir SVG ikonu
              )}
            </span>
          </div>
        </div>
      </Container>
    </header>
  );
};

const logout = () => {
  localStorage.removeItem("token");
  window.location.replace("/");
};

export default Header;
