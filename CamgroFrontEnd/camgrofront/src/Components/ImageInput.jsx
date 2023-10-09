import PropTypes from 'prop-types';
import styles from './../styles/ImageInput.module.css'

function ImageInput(props){

    return(
        <div className={styles.container}>
            <div className={styles.containerImage}>
                <img src={props.source} />
            </div>
            <input className={styles.input} value={props.value}
						onChange={(e) => {props.onChange(e.target.value)}} type={props.type} placeholder={props.placeholder} />
        </div>
    );
}

ImageInput.propTypes = {
    source: PropTypes.string,
    placeholder: PropTypes.string,
    type: PropTypes.string,
    value: PropTypes.string,
    onChange: PropTypes.func
}



export default ImageInput;