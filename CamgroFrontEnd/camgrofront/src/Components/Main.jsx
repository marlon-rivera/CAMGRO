import styles from './../styles/Main.module.css'
import Carrousel from "./Carrousel";

function Main(){
    return (
        <div>
            <main className={styles.main}>
                <div className={styles.info}>
                    <h1>CAMGRO</h1>
                    <h2>DEL CAMPO A TU MESA</h2>
                </div>
                <Carrousel />
            </main>
        </div>
    );
}
export default Main;