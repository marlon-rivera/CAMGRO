import PropTypes from 'prop-types';
import styles from './../styles/Input.module.css'

function Input(props){
    return (
        <input className={styles.input} type={props.type} value={props.value} />
    );
}

Input.propTypes={
    type : PropTypes.string,
    value : PropTypes.string
}

export default Input;