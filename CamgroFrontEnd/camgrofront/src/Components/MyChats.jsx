import { connect } from 'react-redux';
import styles from './../styles/MyChats.module.css';
import PropTypes from 'prop-types';
import { images } from './Images';
import Chat from './Chat';
import Loading from './Loading';
import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

function MyChats(props) {
	const [ready, setReady] = useState(false);
	const [chats, setChats] = useState([])
	const [chatBool, setChatBool] = useState(false)
	const [chatSelected, setChatSelected] = useState()

	const navigate = useNavigate()

	useEffect(() => {
		if(!props.token){
			navigate('/login')
		}
		fetch('http://localhost:8080/chat/all/' + props.email, {
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
							setChats(r)
							console.log(r)
							setReady(true)
						});
	}, [])
	
	const openChat = (chat) => {
		setChatSelected(chat)
		setChatBool(true)
	}

	return (
		<>
			{!ready ? (
				<Loading />
			) : (
				<div className={styles.container}>
					<h1 className={styles.textTitle}>Mensajes</h1>
					<div>
						<div className={styles.containerChats}>
							<div className={styles.chats}>
								{chats.map((c, index) => {
									return (
										<>
											<div key={index} className={styles.chat} onClick={() => openChat(c)}>
												<div className={styles.img}>
													<img src={images.userInv} alt='' />
												</div>
												<div>
													<div>
														<h3 className={styles.h3}>Nombre de usuario:</h3>
														<span className={styles.span}>{c.personName}</span>
													</div>
													<div>
														<span className={styles.span}>
															{c.lastMessage.split(' ').length > 5 ? c.lastMessage.split(' ').slice(0,5).join(' ') : c.lastMessage} ...
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
							{chatBool && <Chat emailPost={chatSelected.email} nameOwnerPost={chatSelected.personName} setChat={() => {setChatBool(!chatBool)}} />}
							
						</div>
					</div>
				</div>
			)}
		</>
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
