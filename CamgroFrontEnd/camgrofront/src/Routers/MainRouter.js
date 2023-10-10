import { Routes, Route, BrowserRouter } from 'react-router-dom';
import Register from '../Components/Register';
import Main from '../Components/Main';
import Header from '../Components/Header';
import Login from '../Components/Login'
import NotFound from '../Components/NotFound';

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
							<Header />
							<NotFound />
						</>
					}
				/>
			</Routes>
		</BrowserRouter>
	);
}

export default MainRouter;
