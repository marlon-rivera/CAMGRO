import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import styles from './../styles/Catalog.module.css';
import { useEffect, useState } from 'react';
import Post from './Post';
import { useNavigate } from 'react-router-dom';
import { BounceLoader } from 'react-spinners';
import stylesLoading from './../styles/Loading.module.css';

function Catalog(props) {
	const [posts, setPosts] = useState([]);
	const [ready, setReady] = useState(false);
	const navigate = useNavigate();

	useEffect(() => {
		if (!props.token) {
			navigate('/login');
		}
		fetch('http://localhost:8080/post/all', {
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
			.then((r) => r.json())
			.then((r) => {
				setPosts(r);
				setReady(true);
			});
	}, []);

	return (
		<div>
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
				<div className={styles.container}>
					<h1 className={styles.textTitle}>Cátalogo</h1>
					<div className={styles.posts}>
						{posts.length > 0 &&
							posts.map((p) => {
								return <Post post={p} i={p.idPost} key={p.idPost} />;
							})}
					</div>
				</div>
			)}
		</div>
	);
}

Catalog.propTypes = {
	token: PropTypes.string,
};

function mapStateToProps(state) {
	return {
		token: state.token,
	};
}

export default connect(mapStateToProps)(Catalog);
