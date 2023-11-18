import UploadImages from './UploadImages';
import styles from './../styles/AddPost.module.css';
import { useEffect, useState } from 'react';
import Button from './Button';
import Error from './Error';
import { connect } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import PropTypes from 'prop-types';
import Loading from './Loading'
import units from './../Static/units.json'

function AddPost(props) {
	const [name, setName] = useState();
	const [nameTouch, setNameTouch] = useState(false);
	const [description, setDescription] = useState();
	const [desTouch, setDesTouch] = useState(false);
	const [price, setPrice] = useState();
	const [priceTouch, setPriceTouch] = useState(false);
	const [unitsTouch, setUnitsTouch] = useState(false);
	const [unit, setUnit] = useState();
	const [postDate, setPostDate] = useState();
	const [postDateTouch, setPostDateTouch] = useState(false);
	const [harvestDate, setHarvestDate] = useState();
	const [harvestDateTouch, setHarvestDateTouch] = useState(false);
	const [quantity, setQuantity] = useState();
	const [quantityTouch, setQuantityTouch] = useState(false);
	const [postState, setPostState] = useState();
	const [postStateTouch, setPostStateTouch] = useState(false);
	const [image, setImage] = useState();
	const [reallyImage, setReallyImage] = useState();
	const [error, setError] = useState(false);
	const [errMess, setErrMess] = useState();
	const [ready, setReady] = useState(false);
	const navigate = useNavigate();

	useEffect(() => {
		if (!props.token) {
			navigate('/login');
		}
	}, []);

	const verifyAllInputsFull = () => {
		console.log(postState)
		return (
			image &&
			name &&
			description &&
			price &&
			unit !== 'Seleccione una opción' &&
			postDate &&
			harvestDate &&
			quantity &&
			postState !== 'Seleccione una opción'
			&&
			postState !== ''
			&&
			postState
		);
	};

	const handleSubmit = async (e) => {
		setReady(true);
		e.preventDefault();
		const formData = new FormData();
		formData.append('name', name);
		formData.append('description', description);
		formData.append('price', price);
		formData.append('unit', unit);
		formData.append('postDate', postDate);
		formData.append('harvestDate', harvestDate);
		formData.append('quantity', quantity);
		formData.append('postState', postState);
		formData.append('image', reallyImage);
		formData.append('id_person', props.id);
		console.log(formData.get('image'));
		fetch('http://localhost:8080/post/add', {
			method: 'POST',
			headers: {
				'Access-Control-Allow-Origin': '*',
				'Access-Control-Allow-Methods': '*',
				'Access-Control-Allow-Headers': '*',
				'Access-Control-Allow-Credentials': 'true',
				Authorization: `Bearer ${props.token}`,
			},
			body: formData,
		})
			.then((r) => r.json())
			.then((r) => {
				setReady(false);
				setErrMess(r.message);
				setError(true);
				navigate('/my-posts');
			})
			.catch((err) => {
				setReady(false);
				console.log(err);
				setErrMess('No se pudo guardar la publicacion.');
				setError(true);
			});
	};

	return (
		<section className={styles.container}>
			<form>
				{error && <Error func={() => setError(false)} message={errMess} />}
				{ready && (
					<Loading />
				)}
				<h1 className={styles.textTitle}>Agregar Publicacion</h1>
				<hr className={styles.line} />
				<div className={styles.content}>
					<div className={styles.containerInputs}>
						<div className={styles.inputArea}>
							<label className={styles.label}>Fotos: *</label>
							<UploadImages
								setImage={setImage}
								image={image}
								setReallyImage={setReallyImage}
							/>
						</div>
						<div className={styles.inputArea}>
							<label className={styles.label}>Nombre: *</label>
							<input
								className={!name && nameTouch ? styles.inputRed : styles.input}
								value={name}
								onBlur={() => setNameTouch(true)}
								type='text'
								onChange={(e) => {
									setName(e.target.value);
								}}
								required
							/>
						</div>
						<div className={styles.inputArea}>
							<label className={styles.label}>Descripción: *</label>
							<input
								className={
									!description && desTouch ? styles.inputRed : styles.input
								}
								value={description}
								onBlur={() => setDesTouch(true)}
								type='text'
								onChange={(e) => {
									setDescription(e.target.value);
								}}
								required
							/>
						</div>
						<div className={styles.inputArea}>
							<label className={styles.label}>Precio: *</label>
							<input
								className={
									!price && priceTouch ? styles.inputRed : styles.input
								}
								value={price}
								onBlur={() => setPriceTouch(true)}
								type='text'
								onChange={(e) => {
									setPrice(e.target.value);
								}}
								required
							/>
						</div>
						<div className={styles.inputArea}>
							<label className={styles.label}>Unidad de medida:*</label>
							<select
								className={
									!units && unitsTouch ? styles.selectRed : styles.select
								}
								value={unit}
								onBlur={() => setUnitsTouch(true)}
								onChange={(e) => {
									console.log(e.target.value)
									setUnit(e.target.value);
								}}
								required
							>
								<option value={null}>Seleccione una opción</option>
								{units.length > 0 &&
									units.map((u, i) => {
										return (
											<option key={i} value={u.nombre}>
												{u.nombre}
											</option>
										);
									})}
							</select>
						</div>
						<div className={styles.inputArea}>
							<label className={styles.label}>Cantidad: *</label>
							<input
								className={
									!quantity && quantityTouch ? styles.inputRed : styles.input
								}
								value={quantity}
								onBlur={() => setQuantityTouch(true)}
								type='text'
								onChange={(e) => {
									setQuantity(e.target.value);
								}}
								required
							/>
						</div>
						<div className={styles.inputArea}>
							<label className={styles.label}>Fecha de publicación: *</label>
							<input
								className={
									!postDate && postDateTouch ? styles.inputRed : styles.input
								}
								value={postDate}
								onBlur={() => setPostDateTouch(true)}
								type='date'
								onChange={(e) => {
									setPostDate(e.target.value);
								}}
								required
							/>
						</div>
						<div className={styles.inputArea}>
							<label className={styles.label}>Fecha de cosecha: *</label>
							<input
								className={
									!harvestDate && harvestDateTouch
										? styles.inputRed
										: styles.input
								}
								value={harvestDate}
								onBlur={() => setHarvestDateTouch(true)}
								type='date'
								onChange={(e) => {
									setHarvestDate(e.target.value);
								}}
								required
							/>
						</div>
						<div className={styles.inputArea}>
							<label className={styles.label}>Estado de publicación:*</label>
							<select
								className={
									!postState && postStateTouch
										? styles.selectRed
										: styles.select
								}
								value={postState}
								onBlur={() => setPostStateTouch(true)}
								onChange={(e) => {
									setPostState(e.target.value);
								}}
								required
							>
								<option value={null}>Seleccione una opción</option>
								<option value='Proxima'>Proxima</option>
								<option value='Activa'>Activa</option>

								<option value='Finalizada'>Finalizada</option>
							</select>
						</div>
					</div>
				</div>
				<div className={styles.submit}>
					<span>(*) Campos obligatorios </span>
					<Button
						disabled={!verifyAllInputsFull()}
						text='Agregar'
						func={handleSubmit}
					/>
				</div>
			</form>
		</section>
	);
}

function mapStateToProps(state) {
	return {
		token: state.token,
		id: state.person.id,
	};
}

AddPost.propTypes = {
	id: PropTypes.string,
	token: PropTypes.string,
};

export default connect(mapStateToProps)(AddPost);
