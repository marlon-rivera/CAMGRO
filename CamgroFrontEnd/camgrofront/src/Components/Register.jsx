import Styles from './../styles/Register.module.css';
import Button from './Button';
import { useState, useEffect } from 'react';

function Register() {
	const [places, setPlaces] = useState([])
	const [department, setDepartment] = useState('');
	const [cities, setCities] = useState([])
	useEffect(() => {
		handleOnChangeDepartment()
	  }, [department]);
	const handleOnChangeDepartment = ()=>{
		console.log(department)
		for (let i = 0; i < places.length; i++) {
			if(places[i].departamento === department){
				setCities(places[i].ciudades)
			}
			
		}
	}

	loadPlaces(setPlaces)

	return (
		<div className={Styles.containerFormRegister}>
			<form>
				<h1 className={Styles.textTitle}>REGISTRARSE</h1>
				<hr className={Styles.line} />
				<div className={Styles.content}>
					<div className={Styles.containerInputs}>
						<div className={Styles.inputArea}>
							<label className={Styles.label}>Nombre:*</label>
							<input className={Styles.input} type='text'></input>
						</div>
						<div className={Styles.inputArea}>
							<label className={Styles.label}>Apellido:*</label>
							<input className={Styles.input} type='text'></input>
						</div>
						<div className={Styles.inputArea}>
							<label className={Styles.label}>Teléfono:*</label>
							<input className={Styles.input} type='text'></input>
						</div>
						<div className={Styles.inputArea}>
							<label className={Styles.label}>Dirección:*</label>
							<input className={Styles.input} type='text'></input>
						</div>
						<div className={Styles.inputArea}>
							<label className={Styles.label}>Email:*</label>
							<input className={Styles.input} type='email'></input>
						</div>
						<div className={Styles.inputArea}>
							<label className={Styles.label}>Departamento:*</label>
							<select className={Styles.select} onChange={(e) => {setDepartment(e.target.value)}}>
								{places.length>0 && places.map(p => {
									return(
										<option key={p.id} value={p.departamento}>{p.departamento}</option>
									);
								})}
							</select>
						</div>
						<div className={Styles.inputArea}>
							<label className={Styles.label}>Ciudad:*</label>
							<select className={Styles.select}>
								{cities.length > 0 && cities.map((c, index)=>{
									return(
										<option key={index} value={c}>{c}</option>
									);
								})}
							</select>
						</div>
						<div className={Styles.inputArea}>
							<label className={Styles.label}>Contraseña:*</label>
							<input className={Styles.input} type='password'></input>
						</div>
						<div className={Styles.inputArea}>
							<label className={Styles.label}>Confirmar Contraseña:*</label>
							<input className={Styles.input} type='password'></input>
						</div>
					</div>
				</div>
				<div className={Styles.submit}>
					<span>(*) Campos obligatorios </span>
					<Button text='Registrarse' />
				</div>
			</form>
		</div>
	);
}

async function loadPlaces(call) {
	await fetch(
		'https://raw.githubusercontent.com/marcovega/colombia-json/master/colombia.min.json',
	).then((r) => r.json()).then(r => call(r));
}

export default Register;
