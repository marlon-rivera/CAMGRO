import { connect } from 'react-redux';
import styles from './../styles/MyChats.module.css';
import PropTypes from 'prop-types';
import { images } from './Images';
import Chat from './Chat';

function MyChats(props) {
	const chats = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12];

	return (
		<div className={styles.container}>
			<h1 className={styles.textTitle}>Mensajes</h1>
			<div>
				<div className={styles.containerChats}>
					<div className={styles.chats}>
						{chats.map((c, index) => {
							return (
								<>
									<div key={index} className={styles.chat}>
										<div className={styles.img}>
											<img src={images.userInv} alt='' />
										</div>
										<div>
											<div>
												<h3 className={styles.h3}>Nombre de usuario:</h3>
												<span className={styles.span}>Marlon Rivera</span>
											</div>
											<div>
												<span className={styles.span}>
													{`Soy el mensaje y estoy supremamente largo como para no
													caber en la pantalla`.slice(0, 80) + `...`}
												</span>{' '}
											</div>
										</div>
									</div>
									<hr className={styles.line} />
								</>
							);
						})}
					</div>
				</div>
				<div className={styles.chatComponent}>
					<Chat />
				</div>
			</div>
		</div>
	);
}

function mapStateToProps(state) {
	return {
		token: state.token,
		email: state.email,
	};
}

MyChats.propTypes = {
	token: PropTypes.string,
	email: PropTypes.string,
};

export default connect(mapStateToProps)(MyChats);
