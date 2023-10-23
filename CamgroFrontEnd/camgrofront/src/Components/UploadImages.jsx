import { useState, useRef } from 'react';
import styles from './../styles/UploadImages.module.css';
import CardImage from './CardImage';
import { images } from './Images';
function UploadImages() {
	const [image, setImage] = useState();

	const handleImageRemove = (index) => {
		setImage();
	};

	const handleFileChange = (event) => {
		const file = event.target.files[0];
		if (file) {
      const reader = new FileReader();
      reader.onload = (e) => {
        setImage(e.target.result);
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
				{image && <CardImage image={image} func={() => handleImageRemove()} />}
			</div>
			<div>
				{!image && (
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

export default UploadImages;
