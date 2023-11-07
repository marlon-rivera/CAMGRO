import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import styles from './../styles/Chat.module.css';
import { images } from './Images';
import { useEffect, useRef, useState } from 'react';
import { io } from 'socket.io-client';
import Loading from './Loading';

function Chat(props) {
	const [messages, setMessages] = useState([]);
	const [message, setMessage] = useState('');
	const [socket, setSocket] = useState(null);
	const [chat, setChat] = useState(false);
	let chatEndRef = useRef(null);
	const [ready, setReady] = useState(false);

	const scrollToBottom = () => {
		chatEndRef.scrollIntoView({ behavior: 'smooth' });
	};

	useEffect(() => {
		setReady(false)
		setChat(null)
		fetch(`http://localhost:8080/chat/${props.email}/${props.emailPost}`, {
			method: 'GET',
			headers: {
				'Access-Control-Allow-Origin': '*',
				'Access-Control-Allow-Methods': '*',
				'Access-Control-Allow-Headers': '*',
				'Access-Control-Allow-Credentials': 'true',
				'Content-Type': 'application/json',
				Authorization: 'Bearer ' + props.token,
			},
		})
			.then((r) => r.json())
			.then((r) => {
				if (typeof r.message === 'string' && isNaN(r.message)) {
					setChat(true);
					setReady(true);
				} else {
					
					fetch('http://localhost:8080/chat/all/messages/' + r.message, {
						method: 'GET',
						headers: {
							'Access-Control-Allow-Origin': '*',
							'Access-Control-Allow-Methods': '*',
							'Access-Control-Allow-Headers': '*',
							'Access-Control-Allow-Credentials': 'true',
							'Content-Type': 'application/json',
							Authorization: 'Bearer ' + props.token,
						},
					})
						.then((r) => {
							return r.json();
						})
						.then((r) => {
							setMessages(r);
							setChat(true);
							setReady(true);
						});
				}
			});
	}, [props.emailPost]);

	useEffect(() => {
		const newSocket = io('http://localhost:3001');
		setSocket(newSocket);
		return () => {
			newSocket.disconnect();
		};
	}, [props.email]);

	useEffect(() => {
		if (socket === null) return;
		socket.emit('addNewUser', props.email);
		return () => {
			socket.off('getOnlineUsers');
		};
	}, [socket]);

	const sendMessage = () => {
		if (socket === null) return;
		messages.push({ from: props.email, message, to: props.emailPost });
		setMessage('');
		setMessages(messages);
		fetch('http://localhost:8080/chat/send/message', {
			method: 'POST',
			headers: {
				'Access-Control-Allow-Origin': '*',
				'Access-Control-Allow-Methods': '*',
				'Access-Control-Allow-Headers': '*',
				'Access-Control-Allow-Credentials': 'true',
				'Content-Type': 'application/json',
				Authorization: 'Bearer ' + props.token,
			},
			body: JSON.stringify({from: props.email, message, to: props.emailPost })
		})
			.then((r) => r.json())
			.then((r) => {
				console.log(r)
			})
			.catch(err => {
				console.log("Dio un error al enviar el mensaje: " + err)
			});
		socket.emit('sendMessage', {
			from: props.email,
			message,
			to: props.emailPost,
		});
	};

	useEffect(() => {
		if (socket === null) return;
		socket.on('getMessage', (res) => {
			if (props.email !== res.to || res.from !== props.emailPost) return;
			setMessages((prev) => [...prev, res]);
		});
		if(chat){
			scrollToBottom();
		}
		return () => {
			socket.off('getMessage');
		};
	});

	return (
		<>
			{!ready && <Loading /> }

			{chat && (
				<div className={styles.container}>
					<div className={styles.chatContainer}>
						<div className={styles.header}>
							<img
								onClick={props.setChat}
								src={images.backChat}
								className={styles.back}
							/>
							<img src={images.userInv} className={styles.imgUser} />
							<p className={styles.user}>{props.nameOwnerPost}</p>
						</div>
						<div className={styles.chat} id='chat'>
							{messages.map((m, index) => {
								if (m.from === props.email) {
									return (
										<div key={index} className={styles.message}>
											<p>{m.message}</p>
											<div className={styles.triangleMessage}></div>
										</div>
									);
								} else {
									return (
										<div key={index} className={styles.messageOther}>
											<p>{m.message}</p>
											<div className={styles.triangleMessageOther}></div>
										</div>
									);
								}
							})}
							<div
								style={{ float: 'left', clear: 'both' }}
								ref={(el) => {
									chatEndRef = el;
								}}
							></div>
						</div>
						<div className={styles.footer}>
							<div className={styles.input}>
								<input
									type='text'
									className={styles.inputMess}
									value={message}
									onChange={(e) => setMessage(e.target.value)}
								/>
								<img
									src={images.send}
									className={styles.send}
									onClick={() => sendMessage()}
								/>
							</div>
						</div>
					</div>
				</div>
			)}
		</>
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
	nameOwnerPost: PropTypes.string,
	setChat: PropTypes.func,
};

export default connect(mapStateToProps)(Chat);
