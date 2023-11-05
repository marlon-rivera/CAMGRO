import { connect } from 'react-redux';
import Styles from './../styles/Register.module.css';
import Button from './Button';
import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import PropTypes from 'prop-types';
import Error from './Error';
import Loading from './Loading';

function Modify(props) {
	const navigate = useNavigate();

	const [ready, setReady] = useState(false);

	const [places, setPlaces] = useState([]);
	const [department, setDepartment] = useState(
		props.person ? props.person.place.lugIdLug.namePlace : null,
	);
	const [cities, setCities] = useState([]);
	const [city, setCity] = useState(
		props.person ? props.person.place.namePlace : null,
	);
	const [name, setName] = useState(
		props.person ? props.person.name.split(' ')[0] : null,
	);
	const [lastname, setLastname] = useState(
		props.person ? props.person.name.split(' ')[1] : null,
	);
	const [phone, setPhone] = useState(props.person ? props.person.phone : null);
	const [address, setAddress] = useState(
		props.person ? props.person.address : null,
	);
	const [email, setEmail] = useState(props.person ? props.email : null);
	const [nameTouch, setNameTouch] = useState(false);
	const [lastNameTouch, setLastNameTouch] = useState(false);
	const [phoneTouch, setPhoneTouch] = useState(false);
	const [addressTouch, setAddressTouch] = useState(false);
	const [emailTouch, setEmailTouch] = useState(false);
	const [departmentTouch, setDepartmentTouch] = useState(false);
	const [cityTouch, setCityTouch] = useState(false);
	const [err, setErr] = useState(false);
	const [errMess, setErrMess] = useState('');

	useEffect(() => {
		if (!props.token && !props.person && !props.email) {
			navigate('/login');
			return;
		}
		if (places.length > 0) {
			loadCities(
				props.person.place.namePlace,
				props.person.place.lugIdLug.namePlace,
				places,
				setCities,
			);
		}
		if (places.length <= 0) {
			loadPlaces(setPlaces);
		}
	}, [places]);

	// TODO
	const handleSubmit = (e) => {
		setReady(true);
		e.preventDefault();
		const data = {
			name: `${name} ${lastname}`,
			phone,
			address,
			city,
			department,
			email,
		};
		const url = 'http://localhost:8080/person/modify';
		fetch(url, {
			method: 'POST',
			headers: {
				'Access-Control-Allow-Origin': '*',
				'Access-Control-Allow-Methods': '*',
				'Access-Control-Allow-Headers': '*',
				'Access-Control-Allow-Credentials': 'true',
				'Content-Type': 'application/json',
				Authorization: `Bearer ${props.token}`,
			},
			body: JSON.stringify(data),
		})
			.then((response) => response.json())
			.then((json) => {
				setReady(false);
				setErr(true);
				setErrMess(json.message);
				navigate('/modify-account');
			})
			.catch((err) => {
				console.log(err);
				setReady(false);
			});
	};

	const handleSumbitPhone = (e) => {
		if (e.target.value.length <= 10) {
			setPhone(e.target.value);
		}
	};

	const verifyAllInputsFull = () => {
		return name && lastname && phone && address && email && department && city;
	};

	return (
		<div className={Styles.containerFormRegister}>
			{err && <Error message={errMess} func={() => setErr(!err)} />}
			{ready && (
				<Loading />
			)}
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

function loadPlaces(call) {
	fetch(
		'https://raw.githubusercontent.com/marcovega/colombia-json/master/colombia.min.json',
	)
		.then((r) => r.json())
		.then((r) => {
			call(r);
		});
}

function loadCities(c, d, places, call) {
	for (let i = 0; i < places.length; i++) {
		if (places[i].departamento === d) {
			call(places[i].ciudades);
		}
	}
}

Modify.propTypes = {
	token: PropTypes.string,
	email: PropTypes.string,
	person: PropTypes.any,
};

function mapStateToProps(state) {
	return {
		token: state.token,
		email: state.email,
		person: state.person,
	};
}

export default connect(mapStateToProps, null)(Modify);
