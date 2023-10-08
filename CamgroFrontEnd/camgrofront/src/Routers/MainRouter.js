import { Routes, Route, BrowserRouter } from 'react-router-dom';
import Register from '../Components/Register';
import Main from '../Components/Main';
import Header from '../Components/Header';
import Login from '../Components/Login'

function MainRouter() {
	return (
		<BrowserRouter>
			<Routes>
				<Route path='/' element={<> <Header /> <Main /></>} />
				<Route path='/register' element={<> <Header /> <Register /> </>} />
        <Route path='/login' element={<Login />} />

				<Route
					path='*'
					element={
						<>
							<h1>Error 4040</h1>
							<strong>Esta página no existe</strong>
						</>
					}
				/>
			</Routes>
		</BrowserRouter>
	);
}

export default MainRouter;
