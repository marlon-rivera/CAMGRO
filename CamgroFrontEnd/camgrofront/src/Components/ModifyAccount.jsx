import { connect } from 'react-redux';
import Styles from './../styles/Register.module.css';
import Button from './Button';
import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import PropTypes from 'prop-types'

function Modify(props) {
	const [places, setPlaces] = useState([]);
	const [department, setDepartment] = useState('');
	const [cities, setCities] = useState([]);
	const [city, setCity] = useState('');
	const [name, setName] = useState('');
	const [lastname, setLastname] = useState('');
	const [phone, setPhone] = useState('');
	const [address, setAddress] = useState('');
	const [email, setEmail] = useState('');
	const [nameTouch, setNameTouch] = useState(false);
	const [lastNameTouch, setLastNameTouch] = useState(false);
	const [phoneTouch, setPhoneTouch] = useState(false);
	const [addressTouch, setAddressTouch] = useState(false);
	const [emailTouch, setEmailTouch] = useState(false);
	const [departmentTouch, setDepartmentTouch] = useState(false);
	const [cityTouch, setCityTouch] = useState(false);
	const navigate = useNavigate();

	useEffect(() => {
		console.log("token: ", props.token)
		if(!props.token){
			navigate('/login')
		}
		handleOnChangeDepartment();
	}, [department]);

	const handleOnChangeDepartment = () => {
		for (let i = 0; i < places.length; i++) {
			if (places[i].departamento === department) {
				setCities(places[i].ciudades);
			}
		}
	};

	const handleSubmit = (e) => {
		e.preventDefault()
		const data = {
			name : `${name} ${lastname}`,
			phone,
			address,
			city,
			department,
			email
		};
		 const url = 'http://localhost:8080/person/modify-account';
		fetch(url, {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
			},
			body: JSON.stringify(data),
		})
			.then((response) => response.json())
			.then((json) => {navigate('/')})
			.catch((err) => console.log(err));
	};
	loadPlaces(setPlaces);

	const handleSumbitPhone = (e) => {
		if (e.target.value.length <= 10) {
			setPhone(e.target.value);
		}
	};

	const verifyAllInputsFull = () => {
		return (
			name &&
			lastname &&
			phone &&
			address &&
			email &&
			department &&
			city
		);
	};

	return (
		<div className={Styles.containerFormRegister}>
			<form>
				<h1 className={Styles.textTitle}>Modificar cuenta</h1>
				<hr className={Styles.line} />
				<div className={Styles.content}>
					<div className={Styles.containerInputs}>
						<div className={Styles.inputArea}>
							<label className={Styles.label}>Nombre:</label>
							<input
								className={!name && nameTouch ? Styles.inputRed : Styles.input}
								value={name}
								onBlur={() => setNameTouch(true)}
								type='text'
								onChange={(e) => {
									setName(e.target.value);
								}}
								required
							/>
						</div>
						<div className={Styles.inputArea}>
							<label className={Styles.label}>Apellido:</label>
							<input
								className={
									!lastname && lastNameTouch ? Styles.inputRed : Styles.input
								}
								type='text'
								value={lastname}
								onBlur={() => setLastNameTouch(true)}
								onChange={(e) => {
									setLastname(e.target.value);
								}}
								required
							/>
						</div>
						<div className={Styles.inputArea}>
							<label className={Styles.label}>Teléfono:</label>
							<input
								className={
									!phone && phoneTouch ? Styles.inputRed : Styles.input
								}
								type='text'
								value={phone}
								onBlur={() => {
									setPhoneTouch(true);
								}}
								onChange={(e) => {
									handleSumbitPhone(e);
								}}
								required
							/>
						</div>
						<div className={Styles.inputArea}>
							<label className={Styles.label}>Dirección:</label>
							<input
								className={
									!address && addressTouch ? Styles.inputRed : Styles.input
								}
								type='text'
								value={address}
								onBlur={() => {
									setAddressTouch(true);
								}}
								onChange={(e) => {
									setAddress(e.target.value);
								}}
								required
							/>
						</div>
						<div className={Styles.inputArea}>
							<label className={Styles.label}>Email:</label>
							<input
								className={
									!email && emailTouch ? Styles.inputRed : Styles.input
								}
								type='email'
								value={email}
								onBlur={() => {
									setEmailTouch(true);
								}}
								onChange={(e) => {
									setEmail(e.target.value);
								}}
								required
                                disabled={true}
							/>
						</div>
						<div className={Styles.inputArea}>
							<label className={Styles.label}>Departamento:</label>
							<select
								className={
									!department && departmentTouch
										? Styles.selectRed
										: Styles.select
								}
								value={department}
								onBlur={() => setDepartmentTouch(true)}
								onChange={(e) => {
									setDepartment(e.target.value);
								}}
								required
							>
								{places.length > 0 &&
									places.map((p) => {
										return (
											<option key={p.id} value={p.departamento}>
												{p.departamento}
											</option>
										);
									})}
							</select>
						</div>
						<div className={Styles.inputArea}>
							<label className={Styles.label}>Ciudad:</label>
							<select
								className={
									!city && cityTouch ? Styles.selectRed : Styles.select
								}
								value={city}
								onBlur={() => setCityTouch(true)}
								onChange={(e) => setCity(e.target.value)}
								required
							>
								{cities.length > 0 &&
									cities.map((c, index) => {
										return (
											<option key={index} value={c}>
												{c}
											</option>
										);
									})}
							</select>
						</div>
						<div className={Styles.inputArea}>
							<label className={Styles.label}>Contraseña:</label>
                            <div>
                                <Button
                                text='Cambiar Contraseña'
                                func={navigate}
                                path='/login/restore-password'
                                />
                            </div>							
						</div>
						
					</div>
				</div>
				<div className={Styles.submit}>
					<span>(*) Campos obligatorios </span>
					<Button
						disabled={!verifyAllInputsFull()}
						text='Modificar'
						func={handleSubmit}
					/>
				</div>
			</form>
		</div>
	);
}

async function loadPlaces(call) {
	await fetch(
		'https://raw.githubusercontent.com/marcovega/colombia-json/master/colombia.min.json',
	)
		.then((r) => r.json())
		.then((r) => call(r));
}

Modify.propTypes = {
	token: PropTypes.string
}

function mapStateToProps(state){
	return{
		token: state.token
	}
}

export default connect(mapStateToProps, null)(Modify);
