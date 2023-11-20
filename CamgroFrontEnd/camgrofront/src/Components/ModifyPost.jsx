import UploadImages from './UploadImages';
import styles from './../styles/AddPost.module.css';
import { useEffect, useState } from 'react';
import Button from './Button';
import Error from './Error';
import { connect } from 'react-redux';
import { useNavigate, useParams } from 'react-router-dom';
import PropTypes from 'prop-types';
import Loading from './Loading';

function ModifyPost(props) {
	const [name, setName] = useState();
	const [nameTouch, setNameTouch] = useState(false);
	const [description, setDescription] = useState();
	const [desTouch, setDesTouch] = useState(false);
	const [price, setPrice] = useState();
	const [priceTouch, setPriceTouch] = useState(false);
	const [units, setUnits] = useState([]);
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
	const [modify, setModify] = useState(false);
	const [ready, setReady] = useState(false);

	const navigate = useNavigate();
	const { id } = useParams();

	useEffect(() => {
		if (!props.token) {
			navigate('/login');
		}
	}, []);

	useEffect(() => {
		if (!props.token) {
			navigate('/login');
		}

		const getDataPost = async () => {
			await fetch(`http://localhost:8080/post/get/${id}`, {
				headers: {
					'Access-Control-Allow-Origin': '*',
					'Access-Control-Allow-Methods': '*',
					'Access-Control-Allow-Headers': '*',
					'Access-Control-Allow-Credentials': 'true',
					'Content-Type': 'application/json',
					Authorization: `Bearer ${props.token}`,
				},
			})
				.then((r) => r.json())
				.then((data) => {
					console.log(data);
					setName(data.postTitle);
					setDescription(data.descriptionPost);
					setPrice(data.priceProduct);
					setUnit(data.unit);
					setPostDate(data.postDate);
					setHarvestDate(data.harvestDate);
					setQuantity(data.accountProducts);
					setPostState(data.postStatus);
					setReallyImage(data.image);
					setImage(data.image);
					setReady(true);
				})
				.catch((err) => {
					console.log(err);
					setErrMess('No se pudo guardar la publicacion.');
					setError(true);
				});
		};
		getDataPost();
	}, [modify]);

	loadUnits(setUnits);

	const verifyAllInputsFull = () => {
		return (
			image &&
			name &&
			description &&
			price &&
			unit &&
			postDate &&
			harvestDate &&
			quantity &&
			postState
		);
	};

	const handleSubmit = async (e) => {
		e.preventDefault();
		setReady(false)
		const formData = new FormData();
		formData.append('name', name);
		formData.append('description', description);
		formData.append('price', price);
		formData.append('unit', unit);
		formData.append('postDate', postDate);
		formData.append('harvestDate', harvestDate);
		formData.append('quantity', quantity);
		formData.append('postState', postState);
		formData.append(
			'image',
			image.includes('') ? image.replace('data:image/jpeg;base64,', '') : image,
		);
		formData.append('id_person', props.id);
		console.log(formData);
		fetch(`http://localhost:8080/post/modify/${id}`, {
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
				setErrMess(r.message);
				setError(true);
				setModify(!modify);
				setReady(true)
			})
			.catch((err) => {
				console.log(err);
				setErrMess('No se pudo guardar la publicacion.');
				setError(true);
				setReady(true)
			});
	};

	return (
		<>
			{!ready ? (
				< Loading />
			) : (
				<section className={styles.container}>
					<form>
						{error && <Error func={() => setError(false)} message={errMess} />}
						<h1 className={styles.textTitle}>Modificar Publicacion</h1>
						<hr className={styles.line} />
						<div className={styles.content}>
							<div className={styles.containerInputs}>
								<div className={styles.inputArea}>
									<label className={styles.label}>Fotos: *</label>
									<UploadImages
										setImage={setImage}
										image={image}
										setReallyImage={setReallyImage}
										reallyImage={reallyImage}
									/>
								</div>
								<div className={styles.inputArea}>
									<label className={styles.label}>Nombre: *</label>
									<input
										className={
											!name && nameTouch ? styles.inputRed : styles.input
										}
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
											setUnit(e.target.value);
										}}
										required
									>
										{units.length > 0 &&
											units.map((p) => {
												return (
													<option key={p.id} value={p.departamento}>
														{p.departamento}
													</option>
												);
											})}
									</select>
								</div>
								<div className={styles.inputArea}>
									<label className={styles.label}>Cantidad: *</label>
									<input
										className={
											!quantity && quantityTouch
												? styles.inputRed
												: styles.input
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
									<label className={styles.label}>
										Fecha de publicación: *
									</label>
									<input
										className={
											!postDate && postDateTouch
												? styles.inputRed
												: styles.input
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
									<label className={styles.label}>
										Estado de publicación:*
									</label>
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
								text='Modificar'
								func={handleSubmit}
							/>
						</div>
					</form>
				</section>
			)}
		</>
	);
}

async function loadUnits(call) {
	await fetch(
		'https://raw.githubusercontent.com/marcovega/colombia-json/master/colombia.min.json',
	)
		.then((r) => r.json())
		.then((r) => call(r));
}

function mapStateToProps(state) {
	return {
		token: state.token,
		id: state.person ? state.person.id : undefined,
	};
}

ModifyPost.propTypes = {
	id: PropTypes.string,
	token: PropTypes.string,
};

export default connect(mapStateToProps)(ModifyPost);
