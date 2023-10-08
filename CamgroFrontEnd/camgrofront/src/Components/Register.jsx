import Styles from "./../styles/Register.module.css"
import Button from './Button'

function Register() {
  return (
    <div className={Styles.containerFormRegister}>
        <form>
            <h1 className={Styles.textTitle}>REGISTRARSE</h1>
            <hr className={Styles.line}/>
            <div className={Styles.content}>
                <div className={Styles.containerInputs}>
                    <div className={Styles.inputArea}>
                        <label className={Styles.label}>Nombre:*</label>
                        <input className={Styles.input} type="text"></input>
                    </div>
                    <div className={Styles.inputArea}>
                        <label className={Styles.label}>Apellido:*</label>
                        <input className={Styles.input} type="text"></input>
                    </div>
                    <div className={Styles.inputArea}>
                        <label className={Styles.label}>Teléfono:*</label>
                        <input className={Styles.input} type="text"></input>
                    </div>
                    <div className={Styles.inputArea}>
                        <label className={Styles.label}>Dirección:*</label>
                        <input className={Styles.input} type="text"></input>
                    </div>
                    <div className={Styles.inputArea}>
                        <label className={Styles.label}>Email:*</label>
                        <input className={Styles.input} type="email"></input>
                    </div>
                    <div className={Styles.inputArea}>
                        <label className={Styles.label}>Ciudad:*</label>
                        <input className={Styles.input} type="text"></input>
                    </div>
                    <div className={Styles.inputArea}>
                        <label className={Styles.label}>Departamento:*</label>
                        <input className={Styles.input} type="text"></input>
                    </div>
                    <div className={Styles.inputArea}>
                        <label className={Styles.label}>Contraseña:*</label>
                        <input className={Styles.input} type="password"></input>
                    </div>
                    <div className={Styles.inputArea}>
                        <label className={Styles.label}>Confirmar Contraseña:*</label>
                        <input className={Styles.input} type="password"></input>
                    </div>
                </div>
            </div>
            <div className={Styles.submit}>
                <span>(*) Campos obligatorios </span>
                <Button text='Registrarse'/>
            </div>
        </form>
    </div>
  )
}

export default Register;