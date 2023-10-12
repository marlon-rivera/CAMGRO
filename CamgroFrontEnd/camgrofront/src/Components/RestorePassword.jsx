import Input from "./Input";
import Button from './Button'
import styles from './../styles/RestorePassword.module.css'
import {Link, useNavigate} from 'react-router-dom'
import { images } from "./Images";

function RestorePassword(){
    const navigate = useNavigate();
    return (
        <section className={styles.containerRestorePassword}>
            <div className={styles.containerImg}>
				<Link to='/'>
					<img className={styles.img} src={images.logo} alt='CAMGRO' />
				</Link>
			</div>
            <div className={styles.containerForm}>
                <form className={styles.form}>
                    <Button func={navigate} path={'/login'} source={images.x} sourceInv={images.xInv} />
                    <Input type={'email'} />
                </form>
                
            </div>
            
        </section>
    );
}

export default RestorePassword;
