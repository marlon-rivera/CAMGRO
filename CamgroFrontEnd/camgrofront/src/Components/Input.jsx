import PropTypes from 'prop-types';
import styles from './../styles/Input.module.css'
import {useState} from 'react'
function Input(props){

    const [touch, setTouch] = useState(false)
    
    return (
        <input className={ !props.value && touch ? styles.inputRed : styles.input} onBlur={()=>setTouch(true)} onChange={(e) => {props.onChange(e.target.value)}} type={props.type} value={props.value} />
    );
}

Input.propTypes={
    type : PropTypes.string,
    value : PropTypes.string,
    onChange : PropTypes.func
}

export default Input;