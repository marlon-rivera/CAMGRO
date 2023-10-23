import PropTypes from 'prop-types'
import { images } from './Images';
import { useState } from 'react';
import styles from './../styles/CardImage.module.css'

function CardImage(props){

    const [image, setImage] = useState(images.xInv)

    return(
        <div className={styles.container}>
            <div className={styles.containerButton}>
                <button className={styles.button} onClick={props.func} onMouseEnter={() => setImage(images.x)}
			onMouseLeave={() => setImage(images.xInv)} >
                    <img className={styles.imageButton} src={image} />
                </button>
            </div>
            <div className={styles.containerImage}>
                <img src={props.image} className={styles.image} />
            </div>
        </div>
    )
}

CardImage.propTypes = {
    image: PropTypes.string,
    func: PropTypes.func
}

export default CardImage;