import { Link } from 'react-router-dom';
import {images} from './Images'
import styles from './../styles/Menu.module.css'

function Menu() {
	return (
		<div className={styles.containerMenu}>
			<ul className={styles.options}>
				<li>
					<Link to='/modify-account' className={styles.option}>
						<img src={images.newUser} alt='icon modify account' className={styles.img} />
						<span className={styles.text}>Modificar cuenta</span>
					</Link>
				</li>
				<li>
					<Link className={styles.option}>
						<img src={images.addPost} alt='icon add post' className={styles.img} />
						<span className={styles.text}>Agregar publicación </span>
					</Link>
				</li>
				<li>
					<Link className={styles.option}>
						<img src={images.modPost} alt='icon modify post' className={styles.img} />
						<span className={styles.text}>Modificar publicación</span>
					</Link>
				</li>
				<li>
					<Link className={styles.option}v>
						<img src={images.posts} alt='icon posts' className={styles.img} />
						<span className={styles.text}>Mis publicaciones </span>
					</Link>
				</li>
				<li>
					<Link className={styles.option}>
						<img src={images.messages} alt='icon messages' className={styles.img} />
						<span className={styles.text}>Mensajes</span>
					</Link>
				</li>
				<li>
					<Link className={styles.option}>
						<img src={images.logout} alt="icon close session" className={styles.img} />
						<span className={styles.text}>Cerrar sesión</span>
					</Link>
				</li>
			</ul>
		</div>
	);
}

export default Menu;
