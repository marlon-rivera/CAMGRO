import { Routes, Route, BrowserRouter} from 'react-router-dom'
import Login from '../Components/Login'
import Header from '../Components/Header'
import Register from '../Components/Register'

function MainRouter() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Login/>}/>
        <Route path='/registrar' element={<Register/>}/>
        <Route path='*' element={
        <>
          <h1>Error 4040</h1> 
          <strong>Esta página no existe</strong>
        </>}/>
      </Routes>
    </BrowserRouter>
  )
} 
export default MainRouter;
