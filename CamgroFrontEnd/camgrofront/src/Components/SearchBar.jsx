import Button from './Button';
import { images } from './Images';
import styles from './../styles/SearchBar.module.css';

function SearchBar() {
	return (
		<div className={styles.searchBar}>
			<input type='text' name='' id='' className={styles.input} />
			<Button source={images.magnifying} />
		</div>
	);
}

export default SearchBar;
