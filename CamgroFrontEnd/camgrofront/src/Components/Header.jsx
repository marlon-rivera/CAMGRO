import { images } from './Images';
import Button from './Button';
import styles from './../styles/header.module.css';
import SearchBar from './SearchBar';
import { useNavigate } from 'react-router-dom';

function Header() {
	const navigate = useNavigate();
	return (
		<header className={styles.header}>
			<nav>
				<img src={images.logo} alt='Logo camgro' className={styles.logo} />
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
						<Button
							source={images.login}
							sourceInv={images.loginInv}
							text='Ingresar'
							func={navigate}
							path='/login'
						/>
					</li>
				</ul>
			</nav>
		</header>
	);
}

export default Header;
