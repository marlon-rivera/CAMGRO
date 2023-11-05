import { BounceLoader } from 'react-spinners';
import stylesLoading from './../styles/Loading.module.css';
import { useState, useEffect } from 'react';

function Loading() {

    const text = "Cargando..."; // El texto que deseas mostrar
    const [displayText, setDisplayText] = useState('');
  
    useEffect(() => {
      let i = 0;
      const interval = setInterval(() => {
        if (i <= text.length) {
          setDisplayText(text.substring(0, i));
          i++;
        } else {
          i = 0; // Reiniciar el contador cuando se haya mostrado completamente el texto
        }
      }, 100); // Intervalo de tiempo en milisegundos entre cada letra
  
      return () => clearInterval(interval);
    }, [text]);

	return (
		<div className={stylesLoading.container}>
			<BounceLoader
				color={'#619002'}
				loading={true}
				className={stylesLoading.loading}
				size={150}
			/>
			<p className={stylesLoading.p}>{displayText}</p>
		</div>
	);
}

export default Loading;
