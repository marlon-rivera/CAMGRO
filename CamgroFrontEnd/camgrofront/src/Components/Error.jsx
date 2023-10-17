import PropTypes from 'prop-types';
import Button from './Button';
import styles from './../styles/Error.module.css'
import { useRef, useEffect } from 'react';

function Error(props){

    const popupRef = useRef();

    useEffect(()=>{
        document.addEventListener('mousedown', handleClickOutside);
    return () => {
      document.removeEventListener('mousedown', handleClickOutside);
    };
    },[])

    const handleClickOutside = (event) => {
        if (popupRef.current && !popupRef.current.contains(event.target)) {
          props.func();
        }
      };

    return (
        <div className={styles.error} ref={popupRef}>
            <div className={styles.messageE}>
                <p className={styles.message}>{props.message}</p>
            </div>
            <div>
                <Button text={'Aceptar'} func={props.func}  />
            </div>
        </div>
    );
}

Error.propTypes={
    message : PropTypes.string,
    func : PropTypes.func
}

export default Error;