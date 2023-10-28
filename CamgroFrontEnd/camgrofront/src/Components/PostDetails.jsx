import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import styles from './../styles/PostDetails.module.css';
import { images } from './Images';
import Button from './Button';
import { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { BounceLoader } from 'react-spinners';
import stylesLoading from './../styles/Loading.module.css';

function PostDetails(props) {
	const { id } = useParams('id');

	const [data, setData] = useState();
	const [ready, setReady] = useState(false);

	const navigate = useNavigate();

	useEffect(() => {
		if (!props.token) {
			navigate('/login');
		}
		const fetchData = async () => {
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
					setData(data);
					setReady(true);
				})
				.catch((err) => {
					console.log(err);
				});
		};

		fetchData();

		console.log(data);
	}, []);

	return (
		<div className={styles.container}>
			{!ready ? (
				<div className={stylesLoading.container}>
					<BounceLoader
						color={'#619002'}
						loading={true}
						className={stylesLoading.loading}
						size={150}
					/>
					<p className={stylesLoading.p}>Cargando...</p>
				</div>
			) : (
				<>
					<div className={styles.containerImage}>
						<div>
							<img
								src={data ? 'data:image/jpeg;base64,' + data.image : ''}
								className={styles.image}
							/>
						</div>
						<div className={styles.save}>
							<span className={styles.savePost}>Guardar publicacion:</span>
							<Button source={images.save} sourceInv={images.saveInv} />
						</div>
					</div>
					<div className={styles.containerInfo}>
						<div className={styles.infoPost}>
							<span className={`${styles.label} ${styles.title}`}>
								Titulo: {data && data.postTitle}
							</span>
							<span className={`${styles.label} ${styles.price}`}>
								Precio: {data && data.priceProduct}
							</span>
							<hr className={styles.line} />
							<span className={`${styles.label} ${styles.description}`}>
								Descripcion: {data && data.descriptionPost}
							</span>
							<hr className={styles.line} />
							<span className={styles.label}>
								Cantidad: {data && data.accountProducts}
							</span>
							<hr className={styles.line} />
							<span className={styles.label}>
								Unidad de medida: {data && data.unit}
							</span>
							<hr className={styles.line} />
							<span className={styles.label}>
								Fecha de publicacion: {data && data.postDate}
							</span>
							<hr className={styles.line} />
							<span className={styles.label}>
								Fecha de cosecha: {data && data.harvestDate}
							</span>
							<hr className={styles.line} />
							<span className={styles.label}>
								Estado de la publicacion: {data && data.postStatus}
							</span>
							<hr className={styles.line} />
						</div>
						<div className={styles.chat}>
							<span className={styles.chatMessage}>Chat con el vendedor:</span>
							<Button source={images.chat} sourceInv={images.chatInv} />
						</div>
					</div>
				</>
			)}
		</div>
	);
}

function mapStateToProps(state) {
	return {
		token: state.token,
	};
}

PostDetails.propTypes = {
	token: PropTypes.string,
};

export default connect(mapStateToProps)(PostDetails);
