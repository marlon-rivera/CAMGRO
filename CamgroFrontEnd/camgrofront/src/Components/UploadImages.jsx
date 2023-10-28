import styles from './../styles/UploadImages.module.css';
import CardImage from './CardImage';
import { images } from './Images';
import { useRef } from 'react';
import PropTypes from 'prop-types';

function UploadImages(props) {
	const handleImageRemove = (index) => {
		props.setImage();
	};

	const handleFileChange = (event) => {
		const file = event.target.files[0];
		if (file) {
			console.log(file);
			const reader = new FileReader();
			reader.onload = (e) => {
				props.setReallyImage(file);
				props.setImage(e.target.result);
			};
			reader.readAsDataURL(file);
		}
	};

	const fileInputRef = useRef(null);

	const handleFileUpload = () => {
		fileInputRef.current.click();
	};

	return (
		<div className={styles.container}>
			<div className={styles.images}>
				{props.image && (
					<CardImage
						image={
							!props.image.includes('data:image/jpeg;base64,')
								? `data:image/jpeg;base64,${props.image}`
								: props.image
						}
						func={() => handleImageRemove()}
					/>
				)}
			</div>
			<div>
				{!props.image && (
					<div
						className={styles.upload}
						onClick={handleFileUpload}
						onChange={handleFileChange}
					>
						<img src={images.plus} alt='' className={styles.imageAdd} />
						<input
							type='file'
							accept='image/*'
							ref={fileInputRef}
							onChange={handleFileChange}
							style={{ display: 'none' }}
						/>
					</div>
				)}
			</div>
		</div>
	);
}

UploadImages.propTypes = {
	image: PropTypes.any,
	setImage: PropTypes.func,
	setReallyImage: PropTypes.func,
	reallyImage: PropTypes.any,
};

export default UploadImages;
