import "./footer.scss";
import { Container, Row, Col, ListGroup, ListGroupItem } from "reactstrap";

const Footer = () => {
  return (
    <footer className="footer">
      <Container>
        <Row>
          <Col lg="4" md="4" sm="6">
            <div className="logo">
              <h4>LOGO</h4>
              <p>Lorem20</p>
            </div>
          </Col>

          <Col lg="4" md="4" sm="6"></Col>
          <Col lg="4" md="4" sm="6"></Col>
        </Row>
      </Container>
    </footer>
  );
};

export default Footer;
