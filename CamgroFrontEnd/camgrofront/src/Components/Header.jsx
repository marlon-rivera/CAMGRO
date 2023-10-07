import { images } from "./Images";
import Button from "./Button";
import styles from './../styles/header.module.css'

function Header(){
    return(
        <header className={styles.header}>
            <nav >
                <img src={images.logo} alt="Logo camgro" width="150px"/>
                <ul>
                    <li>
                        <Button source={images.house} sourceInv={images.houseInv} text="Nosotros" />
                    </li>
                    <li>
                        <Button source={images.catalog} sourceInv={images.catalogInv}  text="Cátalogo" />
                    </li>
                    <li>
                        <Button source={images.login} sourceInv={images.loginInv} text="Ingresar" />
                    </li>
                    
                </ul>
            </nav>
        </header>
    )
}



export default Header;