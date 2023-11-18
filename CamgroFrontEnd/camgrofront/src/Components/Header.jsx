import { images } from './Images';
import Button from './Button';
import Menu from './Menu';
import styles from './../styles/header.module.css';
import SearchBar from './SearchBar';
import { useEffect, useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';

function Header(props) {
	const [menu, setMenu] = useState(false);

	useEffect(() => {
		setMenu(false);
	}, []);

	const navigate = useNavigate();
	return (
		<header className={styles.header}>
			<nav>
				<Link to='/'>
					<img src={images.logo} alt='Logo camgro' className={styles.logo} />
				</Link>
				<ul>
					<li className={styles.option}>
						<Button
							source={images.house}
							sourceInv={images.houseInv}
							text='Inicio'
							func={navigate}
							path='/'
						/>
					</li>
					<li className={styles.option}>
						<Button
							source={images.catalog}
							sourceInv={images.catalogInv}
							text='Cátalogo'
							func={navigate}
							path='/catalog'
						/>
					</li>
					<li className={styles.option}>
						<SearchBar />
					</li >
					{props.isLogged && (
						<li className={styles.option}>
							<Button
								source={images.saveInv}
								sourceInv={images.save}
								func={navigate}
								path={'/posts-saved'}
							/>
						</li >
					)}
					<li className={styles.option}>
						{!props.isLogged ? (
							<Button
								source={images.login}
								sourceInv={images.loginInv}
								text='Ingresar'
								func={navigate}
								path='/login'
							/>
						) : (
							<Button
								source={images.user}
								sourceInv={images.userInv}
								text='Mi cuenta'
								func={() => {
									setMenu(!menu);
								}}
							/>
						)}
					</li>
					<li className={styles.option}>

						{menu && <Menu setMenu={setMenu} />}
					</li>
				</ul>
			</nav>
		</header>
	);
}

function mapStateToProps(state) {
	return {
		isLogged: state.login,
	};
}

Header.propTypes = {
	isLogged: PropTypes.bool,
};

export default connect(mapStateToProps, null)(Header);
