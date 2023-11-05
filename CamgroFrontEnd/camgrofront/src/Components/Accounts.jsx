import { connect } from 'react-redux';
import styles from './../styles/Accounts.module.css';
import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import PropTypes from 'prop-types';
import { images } from './Images';
import Button from './Button';
import Error from './Error';
import Loading from './Loading'

function Accounts(props) {
	const navigate = useNavigate();
	const [accounts, setAccounts] = useState([]);
	const [ready, setReady] = useState(false);
	const [error, setError] = useState(false)
	const [errMess, setErrMess] = useState('')

	useEffect(() => {
		if (!props.token) {
			navigate('/login');
		}

		fetch('http://localhost:8080/admin/all-accounts', {
			method: 'GET',
			headers: {
				'Access-Control-Allow-Origin': '*',
				'Access-Control-Allow-Methods': '*',
				'Access-Control-Allow-Headers': '*',
				'Access-Control-Allow-Credentials': 'true',
				'Content-Type': 'application/json',
				Authorization: `Bearer ${props.token}`,
				Role: props.role,
			},
		})
			.then((r) => r.json())
			.then((r) => {
				console.log(r)
				setAccounts(r);
				setReady(true)
			});
	}, []);

	const deleteAccount = (idAccount, index) => {
		const url = 'http://localhost:8080/admin/delete-account/' + idAccount
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
				accounts.splice(index, 1)
				setAccounts(accounts)
			})
			.catch((err) => console.log(err));
	}

	return (
		<div className={styles.container}>
			{error && <Error message={errMess} func={()=>setError(false)} /> }
			{!ready ? (
				<Loading />
			) : (
				<>
					<h1 className={styles.textTitle}>Cuentas</h1>

					<div className={styles.accounts}>
						{accounts.map((ac, index) => {
							return (
								<div className={styles.account} key={index}>
									<div className={styles.allAccount}>
										<div className={styles.infoAccount}>
											<div className={styles.image}>
												<img src={images.userInv} className={styles.img} />
											</div>
											<div>
												<div>
													<h3 className={styles.title}>Nombre:</h3>
													<span className={styles.content}>{ac.person.name}</span>
												</div>
												<div>
													<h3 className={styles.title}>Correo:</h3>
													<span className={styles.content}>
														{ac.email}
													</span>
												</div>
											</div>
										</div>
										<Button text={'Eliminar'} func={() => deleteAccount(ac.idAccount, index)} />
									</div>
									<hr className={styles.line} />
								</div>
							);
						})}
					</div>
				</>
			)}
		</div>
	);
}

function mapStateToprops(state) {
	return {
		role: state.role,
		token: state.token,
	};
}

Accounts.propTypes = {
	role: PropTypes.string,
	token: PropTypes.string,
};

export default connect(mapStateToprops)(Accounts);
