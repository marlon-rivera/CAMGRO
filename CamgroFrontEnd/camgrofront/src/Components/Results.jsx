import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import { useNavigate, useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import Loading from './Loading';
import styles from './../styles/Catalog.module.css';
import Post from './Post';

function Results(props) {
	const navigate = useNavigate();
	const [results, setResults] = useState([]);
	const [ready, setReady] = useState(false);

	const { searched } = useParams();

	useEffect(() => {
		if (!props.token) {
			navigate('/login');
		}

        console.log(searched)

		fetch('http://localhost:8080/post/search/' + searched, {
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
				setResults(r);
				setReady(true);
			});
	}, [searched]);

	return (
		<>
			{!ready ? (
				< Loading />
			) : (
				<div className={styles.container}>
					<h1 className={styles.textTitle}>Cátalogo</h1>
					<div className={styles.posts}>
						{results.length > 0 &&
							results.map((p) => {
								return <Post post={p} i={p.idPost} key={p.idPost} />;
							})}
					</div>
				</div>
			)}
		</>
	);
}

function mapStateToProps(state) {
	return {
		token: state.token,
	};
}

Results.propTypes = {
	token: PropTypes.string,
};

export default connect(mapStateToProps)(Results);
