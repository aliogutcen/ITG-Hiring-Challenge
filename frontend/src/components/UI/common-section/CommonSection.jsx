import "./commonsection.scss";
import { Container } from "reactstrap";
const CommonSection = (props) => {
  return (
    <section className="common__section">
      <Container>
        <h2>{props.title}</h2>
      </Container>
    </section>
  );
};

export default CommonSection;
