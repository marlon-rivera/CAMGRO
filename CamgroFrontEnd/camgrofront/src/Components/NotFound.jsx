import { images } from './Images';
import styles from './../styles/NotFound.module.css';
import Button from './Button';
import { useNavigate } from 'react-router-dom';

function NotFound() {
    const navigate = useNavigate();
	return (
		<section className={styles.containerNotFound}>
			<div className={styles.info}>
				<div >
					<img src={images.notFound} alt='NotFound' className={styles.img} />
				</div>
				<div className={styles.containerTextNotFound}>
					<h2 className={styles.notFound}>OOPS!</h2>
					<h3 className={styles.textNotFound}>
						Te equivocaste! Vuelve al inicio y sigue <br /> disfrutando de
						nuestra página.
					</h3>
				</div>
			</div>
            <div className={styles.button}>
			    <Button func={navigate} path='/' text='Inicio' source={images.house} sourceInv={images.houseInv} />
            </div>
		</section>
	);
}

export default NotFound;
