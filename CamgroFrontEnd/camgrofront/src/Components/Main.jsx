import styles from './../styles/Main.module.css';
import Carrousel from './Carrousel';
import { BounceLoader } from 'react-spinners';
import stylesLoading from './../styles/Loading.module.css';
import { useState, useEffect } from 'react';


function Main(props) {
	const [ready, setReady] = useState(false);
    const [posts, setPosts] = useState([])
    const [postShow, setPostShow] = useState([]);

    useEffect(() => {
		if (postShow.length <= 0) {
			fetch('http://localhost:8080/post/all', {
				method: 'GET',
				headers: {
					'Access-Control-Allow-Origin': '*',
					'Access-Control-Allow-Methods': '*',
					'Access-Control-Allow-Headers': '*',
					'Access-Control-Allow-Credentials': 'true',
					'Content-Type': 'application/json',
				},
			})
				.then((r) => r.json())
				.then((r) => {
					setPosts(r);
					setPostShow(r.slice(0, 5));
					setReady(true)
				});
		}
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
				<main className={styles.main}>
					<div className={styles.info}>
						<h1>CAMGRO</h1>
						<h2>Del campo a tu mesa</h2>
					</div>
					<Carrousel postShow={postShow} setPostShow={setPostShow} posts={posts} />
				</main>
			)}
		</div>
	);
}

export default Main;
