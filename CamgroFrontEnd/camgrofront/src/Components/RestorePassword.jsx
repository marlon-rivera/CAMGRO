import Input from './Input';
import Button from './Button';
import styles from './../styles/RestorePassword.module.css';
import { Link, useNavigate } from 'react-router-dom';
import { images } from './Images';
import { useState } from 'react';

function RestorePassword() {
	const navigate = useNavigate();

	const [vEmail, setVEmail] = useState(true);
	const [email, setEmail] = useState('');
	const [vCode, setVCode] = useState(false);
	const [code, setCode] = useState('');
	const [text, setText] = useState(
		'Por favor ingresar el correo que <br /> se uso en el registro:',
	);
	const [vNPassword, setVNPassword] = useState(false);
	const [nPassword, setNPassword] = useState('');
	const [nSPassword, setNSPassword] = useState('');

	const handleSumbitEmail = (e) => {
		e.preventDefault();
		setVCode(true);
		setVEmail(false);
		setText('Por favor ingresar el código de <br /> seguridad:');
		
	};

	const handleSumbitCode = (e) => {
		e.preventDefault();
		setVNPassword(true);
		setVCode(false);
		setText(' Por favor ingresar la nueva <br /> contraseña: ');
	};

	const handleSumbitPassword = (e) => {
		e.preventDefault();
		navigate('/login');
	};

	const disableEmail = ()=>{
		const regex = /^[\w-]+(\.[\w-]+)*@([\w-]+\.)+[a-zA-Z]{2,7}$/;
		return !regex.test(email);
	}

	const onChangeCode = (value) => {
		if(value >= 0 && value.length <= 4){
			setCode(value)
		}
	}

	const disabledCode = () => {
		return code.length < 4;
	}

	const disabledPasswords = () => {
		return nPassword === '' || nSPassword === '' || nPassword !== nSPassword
	}

	return (
		<section className={styles.containerRestorePassword}>
			<div className={styles.containerImg}>
				<Link to='/'>
					<img className={styles.img} src={images.logo} alt='CAMGRO' />
				</Link>
			</div>
			<div className={styles.containerForm}>
				<form className={styles.form}>
					<div className={styles.button}>
						<Button
							func={navigate}
							path={'/login'}
							source={images.x}
							sourceInv={images.xInv}
							type={'button'}
						/>
					</div>
					<h2 className={styles.recoverPass}>Recuperar contraseña</h2>
					<p
						dangerouslySetInnerHTML={{ __html: text }}
						className={styles.text}
					/>
					<div className={styles.inputs}>
						{vEmail && (
							<div className={styles.inputs}>
								<div className={styles.input}>
									<Input type={'email'} onChange={setEmail} value={email} />
								</div>
								<div className={styles.input}>
									<Button
										disabled={disableEmail()}
										type={'submit'}
										func={handleSumbitEmail}
										text={'Enviar'}
									/>
								</div>
							</div>
						)}
						{vCode && (
							<div className={styles.inputs}>
								<div className={styles.input}>
									<Input type={'text'} onChange={onChangeCode} value={code} />
								</div>
								<div className={styles.input}>
									<Button
										disabled={disabledCode()}
										type={'submit'}
										func={handleSumbitCode}
										text={'Enviar'}
									/>
								</div>
							</div>
						)}
						{vNPassword && (
							<div className={styles.inputs}>
								<div className={styles.input}>
									<Input
										type={'password'}
										onChange={setNPassword}
										value={nPassword}
									/>
								</div>
								<div className={styles.input}>
									<p className={styles.text}>
										Por favor confirmar la nueva <br /> contraseña:
									</p>
								</div>
								<div className={styles.input}>
									<Input
										type={'password'}
										onChange={setNSPassword}
										value={nSPassword}
									/>
								</div>
								<div className={styles.input}>
									<Button
										disabled={disabledPasswords()}
										type={'submit'}
										func={handleSumbitPassword}
										text={'Enviar'}
									/>
								</div>
							</div>
						)}
					</div>
				</form>
			</div>
		</section>
	);
}

export default RestorePassword;
