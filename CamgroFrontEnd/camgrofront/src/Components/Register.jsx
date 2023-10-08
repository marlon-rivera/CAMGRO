import Styles from "./../styles/Register.module.css"

function Register() {
  return (
    <div className={Styles.containerFormRegister}>
        <form>
            <div className={Styles.title}>
                <span className={Styles.textTitle}>REGISTRARSE</span>
            </div>
            <hr className={Styles.line}/>
            <div className={Styles.content}>
                <div className="">
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
                </div>
                
            </div>
            <div className={Styles.submit}>
                
            </div>
        </form>
    </div>
  )
}

export default Register;