import Post from './Post';
import styles from './../styles/Carrousel.module.css';

function Carrousel() {
	const posts = [1, 2, 3, 4, 5];
	return (
		<section className={styles.carrousel}>
			{posts.map((p) => {
				return <Post key={p} />;
			})}
		</section>
	);
}

export default Carrousel;
