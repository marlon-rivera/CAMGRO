import { images } from './Images';
import Button from './Button';
import Menu from './Menu';
import styles from './../styles/header.module.css';
import SearchBar from './SearchBar';
import { useEffect, useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';

function Header() {
	const [isLogged, setIsLogged] = useState(true);
	const [menu, setMenu] = useState(false);

	useEffect(()=>{
		setMenu(false)
	}, [])

	const navigate = useNavigate();
	return (
		<header className={styles.header}>
			<nav>
				<Link to='/'>
					<img src={images.logo} alt='Logo camgro' className={styles.logo} />
				</Link>
				<ul>
					<li>
						<Button
							source={images.house}
							sourceInv={images.houseInv}
							text='Inicio'
							func={navigate}
							path='/'
						/>
					</li>
					<li>
						<Button
							source={images.catalog}
							sourceInv={images.catalogInv}
							text='Cátalogo'
							func={navigate}
							path='/catalog'
						/>
					</li>
					<li>
						<SearchBar />
					</li>
					<li>
						{!isLogged ? (
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
								func={()=>{setMenu(!menu)}}
							/>
						)}

						{menu && <Menu func={setIsLogged} />}
					</li>
				</ul>
			</nav>
		</header>
	);
}

export default Header;
