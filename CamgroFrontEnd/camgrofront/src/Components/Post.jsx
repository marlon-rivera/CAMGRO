import { Link } from 'react-router-dom';
import styles from './../styles/Post.module.css';
import PropTypes from 'prop-types';

function Post(props) {
	console.log("POST:" + props.i)

	return (
		<Link to={`/post/${props.i}`}>
			<article className={styles.post}>
				<img
					src={'data:image/jpeg;base64,' + props.post.image}
					className={styles.image}
				/>
				<span className={styles.title}>Titulo: {props.post.postTitle}</span>
				<span className={styles.price}>Precio: {props.post.priceProduct}</span>
			</article>
		</Link>
	);
}

Post.propTypes = {
	post: PropTypes.object,
	i: PropTypes.string
};

export default Post;
