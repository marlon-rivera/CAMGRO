import { useState } from 'react';
import Button from './Button';
import ImageInput from './ImageInput';
import { images } from './Images';
import styles from './../styles/Login.module.css';

function Login() {
	const [email, setEmail] = useState('');
	const [password, setPassword] = useState('');

	const handleSubmit = (event) => {
		event.preventDefault();
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
						onChange={(e) => setEmail(e.target.value)}
						source={images.userLogo}
						placeholder='Email'
					/>
					<ImageInput
						type='password'
						value={password}
						onChange={(e) => setPassword(e.target.value)}
						source={images.lock}
						placeholder='Contraseña'
					/>
                    <span className={styles.restorePass}>Recuperar contraseña</span>
					<Button
						type='sumbit'
						source={images.login}
						sourceInv={images.loginInv}
						text='Ingresar'
					/>
					<Button
						source={images.addUser}
						sourceInv={images.addUserInv}
						text='Registrarse'
					/>
				</form>
			</div>
		</div>
	);
}

export default Login;
