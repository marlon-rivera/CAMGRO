import { connect } from 'react-redux';
import styles from './../styles/Main.module.css'
import Carrousel from "./Carrousel";
import { useEffect } from 'react';
import PropTypes from 'prop-types'

function Main(props){

    useEffect(()=>{
        console.log(props.token)
    }, [])

    return (
        <div>
            <main className={styles.main}>
                <div className={styles.info}>
                    <h1>CAMGRO</h1>
                    <h2>Del campo a tu mesa</h2>
                </div>
                <Carrousel />
            </main>
        </div>
    );
}

function mapStateToProps(state){
    return {
        token: state.token
    }
}

Main.propTypes = {
    token: PropTypes.string
}

export default connect(mapStateToProps)(Main);