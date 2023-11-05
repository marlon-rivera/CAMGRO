import { connect } from 'react-redux';
import styles from './../styles/MyPosts.module.css';
import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import PropTypes from 'prop-types';
import Button from './Button';
import Loading from './Loading';
import Error from './Error'

function MyPosts(props) {
	const navigate = useNavigate();
	const [posts, setPosts] = useState([]);
	const [ready, setReady] = useState(false);
	const [error, setError] = useState(false)
	const [errMess, setErrMess] = useState('')

	useEffect(() => {
		if (!props.token) {
			navigate('/login');
		}
	
		let url = `http://localhost:8080/post/all`;
		if(props.role === 'USER'){
			url += `/${props.email}`;
		}
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
				setPosts(json);
				setReady(true);
			})
			.catch((err) => console.log(err));
	}, []);

	const navigatePostDetails = (index) => {
		navigate('/post/' + index);
	};

	const deletePost = (idPost, index) => {
		const url = 'http://localhost:8080/admin/delete-post/' + idPost
		fetch(url, {
			method: 'DELETE',
			headers: {
				'Access-Control-Allow-Origin': '*',
				'Access-Control-Allow-Methods': '*',
				'Access-Control-Allow-Headers': '*',
				'Access-Control-Allow-Credentials': 'true',
				'Content-Type': 'application/json',
				Authorization: `Bearer ${props.token}`,
				'Role' : props.role
			},
		})
			.then((response) => response.json())
			.then((json) => {
				setError(true)
				setErrMess(json.message)
				setReady(true);
				posts.splice(index, 1)
				setPosts(posts)
			})
			.catch((err) => console.log(err));
	}

	return (
		<div className={styles.container}>
			{error && <Error message={errMess} func={()=>setError(false)} /> }
			{!ready ? (
				< Loading />
			) : (
				<>
					<h1 className={styles.textTitle}> {props.role === 'ADMIN' ? 'Publicaciones' : 'Mis publicaciones'}</h1>
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
												text={props.role === 'ADMIN' ? 'Eliminar' : 'Modificar'}
												func={() => {
													if(props.role === 'ADMIN'){
														deletePost(p.idPost, index)
													}else{
														navigate(`/modify-post/${p.idPost}`);
													}
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
		role: state.role
	};
}

MyPosts.propTypes = {
	token: PropTypes.string,
	email: PropTypes.string,
	role: PropTypes.string
};

export default connect(mapStateToprops)(MyPosts);
