import UploadImages from './UploadImages';
import styles from './../styles/AddPost.module.css';
import { useState } from 'react';
import Button from './Button';

function AddPost(props) {
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
	const [postStateTouch, setPostStateTouch] = useState(false)

	loadUnits(setUnits);

	const verifyAllInputsFull = () =>{
		return name && description && price && unit & postDate && harvestDate && quantity & postState
	}

	const handleSubmit = (e) => {
		e.preventDefault()
	}

	return (
		<section className={styles.container}>
			<form>
				<h1 className={styles.textTitle}>Agregar Producto</h1>
				<hr className={styles.line} />
				<div className={styles.content}>
					<div className={styles.containerInputs}>
						<div className={styles.inputArea}>
							<label className={styles.label}>Fotos: *</label>
							<UploadImages />
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
									!postState && postStateTouch ? styles.selectRed : styles.select
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
						text='Agregar'
						func={handleSubmit}
					/>
				</div>
			</form>
		</section>
	);
}

async function loadUnits(call) {
	await fetch(
		'https://raw.githubusercontent.com/marcovega/colombia-json/master/colombia.min.json',
	)
		.then((r) => r.json())
		.then((r) => call(r));
}

export default AddPost;
