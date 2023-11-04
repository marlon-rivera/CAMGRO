import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import styles from './../styles/Chat.module.css';
import { images } from './Images';
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';
import { useEffect, useState /* useState */ } from 'react';

function Chat(props) {
	const prueba = [1, 2, 3];
	/* const [messages, setMessages] = useState([]) */
	const [message, setMessage] = useState('');
	const [emailPost, setEmailPost] = useState('');
	let stompClient = null;

	useEffect(() => {
		if (props.func) {
			const getOwner = async () => {
				const owner = await props.func();
				setEmailPost(owner);
			};
			getOwner();
		}
		console.log(emailPost)
		const Sock = new SockJS(
			'http://localhost:8080/chat/' + props.email
			);
		stompClient = Stomp.over(Sock)
		stompClient.connect({}, onConnected, onError)
	}, []);

	const onConnected = () => {
		
		/* stompClient.suscribe(
			`user/ + ${props.emailPost ? props.emailPost : emailPost} + /private`,
			onPrivateMessageReceived,
		); */
	};

	/* const onPrivateMessageReceived = (payload) => {
		console.log('Payload: ' + payload);
		/* const payloadData = JSON.parse(payload);
		console.log(payloadData);
		messages.push(payloadData.content)
		setMessages(messages) 
	}; */

	const onError = (err) => {
		console.log('Error: ' + err);
	};

	const sendPrivateMessage = () => {
		if (stompClient) {
			const chatMessage = {
				sender: props.email,
				receiver: props.emailPost,
				message,
			};
			stompClient.send('app/private-message', {}, JSON.stringify(chatMessage));
			setMessage('');
		}
	};

	return (
		<div className={styles.container}>
			<div className={styles.chatContainer}>
				<div className={styles.header}>
					<img src={images.backChat} className={styles.back} />
					<img src={images.userInv} className={styles.imgUser} />
					<p className={styles.user}>Marlon Rivera</p>
				</div>
				<div className={styles.chat}>
					{prueba.map((m, index) => {
						return (
							<>
								<div className={styles.message}>
									<p>
										Soy el mensaje y estoy supremamente largo como para no caber
										en la pantalla
									</p>
									<div className={styles.triangleMessage}></div>
								</div>
								<div className={styles.messageOther}>
									<p>
										Soy el mensaje del otro que sigue siendo mucho mas largo
										porque me gusta ser bastante largo
									</p>{' '}
									<div className={styles.triangleMessageOther}></div>
								</div>
							</>
						);
					})}
				</div>
				<div className={styles.footer}>
					<div className={styles.input}>
						<input type='text' className={styles.inputMess} />
						<img
							src={images.send}
							className={styles.send}
							onClick={sendPrivateMessage}
						/>
					</div>
				</div>
			</div>
		</div>
	);
}

function mapStateToProps(state) {
	return {
		email: state.email,
		token: state.token,
	};
}

Chat.propTypes = {
	email: PropTypes.string,
	token: PropTypes.string,
	emailPost: PropTypes.string,
	func: PropTypes.func,
};

export default connect(mapStateToProps)(Chat);
