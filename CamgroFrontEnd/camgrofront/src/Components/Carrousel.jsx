import Post from './Post';
import styles from './../styles/Carrousel.module.css';
import Button from './Button';
import { images } from './Images';
import PropTypes from 'prop-types'

let countForward = 4;
let countBackward = 0;
const positions = [0, 1, 2, 3, 4];

function Carrousel(props) {
	

	const nextPost = () => {
		countBackward = countBackward + 1;
		countForward = countForward + 1;
		if (countForward > props.posts.length - 1) {
			countForward = 0;
		}
		if (countBackward > props.posts.length - 1) {
			countBackward = 0;
		}
		positions.shift();
		positions.push(countForward);
		props.setPostShow(getArrayByPositions());
	};

	const previousPost = () => {
		countBackward = countBackward - 1;
		countForward = countForward - 1;
		if (countBackward <= 0) {
			countBackward = props.posts.length - 1;
		}
		if (countForward <= 0) {
			countForward = props.posts.length - 1;
		}
		positions.unshift(countBackward);
		positions.pop();
		props.setPostShow(getArrayByPositions());
	};

	const getArrayByPositions = () => {
		const aux = [];
		for (let i = 0; i < positions.length; i++) {
			aux.push(props.posts[positions[i]]);
		}
		return aux;
	};

	return (
		<section className={styles.carrousel}>
			<div className={styles.arrowL}>

				<Button
					source={images.arrowLInv}
					sourceInv={images.arrowL}
					func={previousPost}
				/>
			</div>
			{props.postShow.map((p) => {
				return <Post i={p.idPost} key={p.idPost} post={p} />;
			})}
			<div className={styles.arrowR}>
				<Button
					source={images.arrowRInv}
					sourceInv={images.arrowR}
					func={nextPost}
				/>
			</div>
		</section>
	);
}

Carrousel.propTypes = {
	posts: PropTypes.array,
	postShow: PropTypes.array,
	setPostShow: PropTypes.func
}

export default Carrousel;
