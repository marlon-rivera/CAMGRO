import { connect } from 'react-redux';
import styles from './../styles/MyPosts.module.css';
import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import PropTypes from 'prop-types';
import Button from './Button';
import { BounceLoader } from 'react-spinners';
import stylesLoading from './../styles/Loading.module.css';

function PostsSaved(props) {
	const navigate = useNavigate();
	const [posts, setPosts] = useState([]);
	const [ready, setReady] = useState(false);

	useEffect(() => {
		if (!props.token) {
			navigate('/login');
		}

		const url = `http://localhost:8080/post/savedPosts/${props.email}`;
        console.log(url)
		fetch(url, {
			method: 'GET',
			headers: {
				'Access-Control-Allow-Origin': '*',
				'Access-Control-Allow-Methods': '*',
				'Access-Control-Allow-Headers': '*',
				'Access-Control-Allow-Credentials': 'true',
				'Content-Type': 'application/json',
				Authorization: `Bearer ${props.token}`,
			},
		})
			.then((response) => response.json())
			.then((json) => {
                console.log(json)
				setPosts(json);
				setReady(true);
			})
			.catch((err) => console.log(err));
	}, []);

	const navigatePostDetails = (index) => {
		navigate('/post/' + index);
	};

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
					<h1 className={styles.textTitle}>Publicaciones guardadas</h1>
					<div className={styles.posts}>
						{posts.map((p, index) => {
							return (
								<div key={index} className={styles.myPost}>
									<div
										className={styles.imagePost}
										onClick={() => {
											navigatePostDetails(p.idPost);
										}}
									>
										<img
											src={`data:image/jpeg;base64,${p.image}`}
											className={styles.image}
										/>
									</div>
									<div className={styles.containerInfo}>
										<div
											className={styles.infoPost}
											onClick={() => {
												navigatePostDetails(p.idPost);
											}}
										>
											<span className={styles.title}>
												Titulo: {p.postTitle}
											</span>
											<span className={styles.price}>
												Precio: {p.priceProduct}
											</span>
											<hr className={styles.line} />
											<span className={styles.description}>
												Descripcion: {p.descriptionPost}
											</span>
											<hr className={styles.line} />
											<span className={styles.state}>
												Estado de la publicacion: {p.postStatus}
											</span>
											<hr className={styles.line} />
										</div>
										<div className={styles.modify}>
											<Button
												text={'Ir a publicación'}
												func={() => {
													navigatePostDetails(p.ickdPost);
												}}
											/>
										</div>
									</div>
								</div>
							);
						})}
					</div>{' '}
				</>
			)}
		</div>
	);
}

function mapStateToprops(state) {
	return {
		token: state.token,
		email: state.email,
	};
}

PostsSaved.propTypes = {
	token: PropTypes.string,
	email: PropTypes.string,
};

export default connect(mapStateToprops)(PostsSaved);
