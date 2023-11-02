import { Link } from 'react-router-dom';
import { images } from './Images';
import styles from './../styles/Menu.module.css';
import { connect } from 'react-redux';
import {
	updateEmail,
	login,
	updateToken,
	updateInfoPerson,
} from '../redux/actions/actionsCreators';
import PropTypes from 'prop-types';

function Menu(props) {
	function resetData() {
		props.login(false);
		props.updateEmail(null);
		props.updateToken(null);
		props.updateInfoPerson(null);
		props.setMenu(false);
	}
	console.log(props.role)
	return (
		<div className={styles.containerMenu}>
			{props.role === 'ADMIN' ? (
				<ul className={styles.options}>
				<li>
					<Link to='/accounts' className={styles.option}>
						<img
							src={images.newUser}
							alt='icon accounts'
							className={styles.img}
						/>
						<span className={styles.text}>Cuentas</span>
					</Link>
				</li>
				<li>
					<Link to='/posts' className={styles.option}>
						<img
							src={images.posts}
							alt='icon post'
							className={styles.img}
						/>
						<span className={styles.text}>Publicaciones</span>
					</Link>
				</li>
				<li>
						<Link className={styles.option} to='/' onClick={resetData}>
							<img
								src={images.logout}
								alt='icon close session'
								className={styles.img}
							/>
							<span className={styles.text}>Cerrar sesión</span>
						</Link>
					</li>
			</ul>
			) : (
				<ul className={styles.options}>
					<li>
						<Link to='/modify-account' className={styles.option}>
							<img
								src={images.newUser}
								alt='icon modify account'
								className={styles.img}
							/>
							<span className={styles.text}>Modificar cuenta</span>
						</Link>
					</li>
					<li>
						<Link to='/add-post' className={styles.option}>
							<img
								src={images.addPost}
								alt='icon add post'
								className={styles.img}
							/>
							<span className={styles.text}>Agregar publicación </span>
						</Link>
					</li>
					<li>
						<Link to='/my-posts' className={styles.option} v>
							<img src={images.posts} alt='icon posts' className={styles.img} />
							<span className={styles.text}>Mis publicaciones </span>
						</Link>
					</li>
					<li>
						<Link className={styles.option}>
							<img
								src={images.messages}
								alt='icon messages'
								className={styles.img}
							/>
							<span className={styles.text}>Mensajes</span>
						</Link>
					</li>
					<li>
						<Link className={styles.option} to='/' onClick={resetData}>
							<img
								src={images.logout}
								alt='icon close session'
								className={styles.img}
							/>
							<span className={styles.text}>Cerrar sesión</span>
						</Link>
					</li>
				</ul>
			)}
		</div>
	);
}

function mapDispatchToProps(dispatch) {
	return {
		updateToken: (token) => dispatch(updateToken(token)),
		login: (loginBool) => dispatch(login(loginBool)),
		updateInfoPerson: (person) => dispatch(updateInfoPerson(person)),
		updateEmail: (email) => dispatch(updateEmail(email)),
	};
}

Menu.propTypes = {
	updateToken: PropTypes.func,
	login: PropTypes.func,
	updateInfoPerson: PropTypes.func,
	updateEmail: PropTypes.func,
	setMenu: PropTypes.func,
	role: PropTypes.string,
};

function mapStateToprops(state) {
	return {
		role: state.role,
	};
}

export default connect(mapStateToprops, mapDispatchToProps)(Menu);
