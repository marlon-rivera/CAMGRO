import PropTypes from 'prop-types';
import styles from './../styles/button.module.css';
import { useState } from 'react';

function Button(props) {

	const [image, setImage] = useState(props.source);


	if (!props.source && !props.sourceInv) {
		return (
			<button disabled={props.disabled} className={ props.disabled ? styles.buttonDisabled : styles.buttonWithText} onClick={(e) => {
				if(props.path){
					props.func(props.path)
				}else{
					props.func(e)
				}
			} } type={props.type}>
				<span className={ props.disabled ? styles.textTextDisable : styles.textText}>{props.text}</span>
			</button>
		);
	}

	if(!props.text && props.sourceInv && props.source){
		return (
			<button onMouseEnter={() => setImage(props.sourceInv)}
			type={props.type}
			onMouseLeave={() => setImage(props.source)}  className={styles.buttonOnlyImg}  onClick={()=>props.func(props.path)}>
				<img src={image} />
			</button>
		);
	}


	if (!props.text && !props.sourceInv) {
		return (
			<button onClick={()=>props.func(props.path)} className={styles.buttonWithoutText}>
				<img className={styles.img} src={props.source} />
			</button>
		);
	}

	return (
		<button
			className={styles.button}
			onMouseEnter={() => setImage(props.sourceInv)}
			onMouseLeave={() => setImage(props.source)}
			onClick={(e) => {
				if(props.path){
					props.func(props.path)
				}else{
					props.func(e)
				}

			}}
		>
			<img src={image} width={props.width} className={styles.img} />
			<span className={styles.text}>{props.text}</span>
		</button>
	);
}

Button.propTypes = {
	source: PropTypes.string,
	sourceInv: PropTypes.string,
	text: PropTypes.string,
	width: PropTypes.string,
	func: PropTypes.func,
	path: PropTypes.string,
	disabled: PropTypes.bool,
	type : PropTypes.string
};

export default Button;
