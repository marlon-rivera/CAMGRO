import PropTypes from "prop-types";

function Button(props) {
	return (
		<button>
			<img src={props.src} width={props.width} />
			<span>{props.text}</span>
		</button>
	);
}

Button.propTypes = {
	src: PropTypes.string,
	text: PropTypes.string,
	width: PropTypes.string
}

export default Button;
