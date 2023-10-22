import { useState } from 'react';
import Button from './Button';
import ImageInput from './ImageInput';
import { images } from './Images';
import styles from './../styles/Login.module.css';
import { useNavigate, Link } from 'react-router-dom';
import Error from './Error';
import { connect } from 'react-redux';
import { updateToken, login } from '../redux/actions/actionsCreators';
import PropTypes from 'prop-types';

function Login(props) {
	const [email, setEmail] = useState('');
	const [password, setPassword] = useState('');
	const [error, setError] = useState(false);

	const navigate = useNavigate();

	const handleSubmit = (event) => {
		event.preventDefault();
		let data = {
			email,
			password,
		};
		const url = 'http://localhost:8080/auth/login';

		fetch(url, {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
			},
			body: JSON.stringify(data),
		})
			.then((response) => response.json())
			.then((json) => {
				const headers = new Headers();

				headers.append('Content-Type', 'application/json');
				headers.append('Authorization', 'Bearer ' + json.token);

				navigate('/');
				props.updateToken(json.token);
				props.login(true);
				data = {
					email,
				};
				fetch(`http://localhost:8080/person/search/${email}`, {
					mode: 'no-cors',
					method: 'GET',
					headers : {
						'Content-Type': 'application/json',
						'Authorization': json.token
					},
				})
					.then((r) => r.json())
					.then((r) => {
						console.log(r);
					})
					.catch(err => console.log(err));
			})
			.catch(() => {
				setError(true);
			});
	};

	const changeError = () => {
		setError(false);
	};

	return (
		<div
			className={
				error
					? `${styles.disabled} ${styles.containerLogin}`
					: styles.containerLogin
			}
		>
			<div className={styles.containerImg}>
				<Link to='/'>
					<img className={styles.img} src={images.logo} alt='CAMGRO' />
				</Link>
			</div>
			<div className={styles.containerForm}>
				<form onSubmit={handleSubmit} className={styles.form}>
					<ImageInput
						type='text'
						value={email}
						onChange={setEmail}
						source={images.userLogo}
						placeholder='Email'
					/>
					<ImageInput
						type='password'
						value={password}
						onChange={setPassword}
						source={images.lock}
						placeholder='Contraseña'
					/>
					<Link to={'/login/restore-password'}>
						<span className={styles.restorePass}>Recuperar contraseña</span>
					</Link>
					<Button
						type='sumbit'
						source={images.login}
						sourceInv={images.loginInv}
						text='Ingresar'
						func={handleSubmit}
					/>
					<Button
						source={images.addUser}
						sourceInv={images.addUserInv}
						text='Registrarse'
						func={navigate}
						path='/register'
					/>
				</form>
			</div>
			{error && (
				<Error
					func={changeError}
					message='Correo electronico o contraseña incorrectos. Intentelo nuevamente.'
				/>
			)}
		</div>
	);
}

Login.propTypes = {
	updateToken: PropTypes.func,
	login: PropTypes.func,
};

function mapDispatchToProps(dispatch) {
	return {
		updateToken: (token) => dispatch(updateToken(token)),
		login: (loginBool) => dispatch(login(loginBool)),
	};
}

export default connect(null, mapDispatchToProps)(Login);
