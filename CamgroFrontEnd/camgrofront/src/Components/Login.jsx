import { useState } from 'react';
import Button from './Button'
import ImageInput from './ImageInput';
import {images} from './Images';

function Login() {

	const [email, setEmail] = useState('');
	const [password, setPassword] = useState('');

	const handleSubmit = (event) => {
		event.preventDefault();
		console.log(`Username: ${email}, Password: ${password}`);
	};

	return (
		<form onSubmit={handleSubmit}>
			<ImageInput
				type='text'
				value={email}
				onChange={(e) => setEmail(e.target.value)}
                source={images.userLogo}
                placeholder="Email"
			/>
			<ImageInput
				type='password'
				value={password}
				onChange={(e) => setPassword(e.target.value)}
                source={images.lock}
                placeholder="Contraseña"
			/>
            <Button type='sumbit' source={images.login} sourceInv={images.loginInv} text='Ingresar' />
		</form>
	);
}

export default Login;
