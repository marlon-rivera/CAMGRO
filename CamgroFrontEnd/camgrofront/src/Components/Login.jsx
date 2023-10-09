import { useState } from 'react';
import Button from './Button';
import ImageInput from './ImageInput';
import { images } from './Images';
import styles from './../styles/Login.module.css';
import { useNavigate } from 'react-router-dom/dist';

function Login() {
	const [email, setEmail] = useState('');
	const [password, setPassword] = useState('');

	const navigate = useNavigate();

	const handleSubmit = (event) => {
		event.preventDefault();
		const data = {
			email,
			password
		};
		console.log(data)
		const url = 'http://localhost:8080/auth/login';

		fetch(url, {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
			},
			body: JSON.stringify(data),
		})
			.then((response) => response.json())
			.then((json) => console.log(json))
			.catch((err) => console.log(err));
		console.log(`Username: ${email}, Password: ${password}`);
	};

	return (
		<div className={styles.containerLogin}>
			<div className={styles.containerImg}>
				<img className={styles.img} src={images.logo} alt='CAMGRO' />
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
                    <span className={styles.restorePass}>Recuperar contraseña</span>
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
		</div>
	);
}

export default Login;
