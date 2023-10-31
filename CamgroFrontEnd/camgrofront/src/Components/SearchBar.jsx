import Button from './Button';
import { images } from './Images';
import styles from './../styles/SearchBar.module.css';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function SearchBar() {

	const [searched, setSearched] = useState();

	const navigate = useNavigate()

	return (
		<div className={styles.searchBar}>
			<input type='text' value={searched} onChange={(e) => {setSearched(e.target.value); console.log(e.target.value)}} className={styles.input} />
			<Button source={images.magnifying} type={'sumbit'} func={navigate} path={`/search/results/${searched}`} />
		</div>
	);
}

export default SearchBar;
