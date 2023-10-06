function Button(props) {
	return (
		<button>
			<img src={this.props.src} width={this.props.width} />
			<span>{this.props.text}</span>
		</button>
	);
}

export default Button;
