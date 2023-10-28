import { Routes, Route, BrowserRouter } from 'react-router-dom';
import Register from '../Components/Register';
import Main from '../Components/Main';
import Header from '../Components/Header';
import Login from '../Components/Login'
import NotFound from '../Components/NotFound';
import RestorePassword from '../Components/RestorePassword'
import Modify from '../Components/ModifyAccount';
import AddPost from '../Components/AddPost';
import MyPosts from '../Components/MyPosts';
import ModifyPost from '../Components/ModifyPost';
import PostDetails from '../Components/PostDetails';
import Catalog from '../Components/Catalog';

function MainRouter() {
	return (
		<BrowserRouter>
			<Routes>
				<Route path='/' element={<> <Header /> <Main /></>} />
				<Route path='/register' element={<> <Header /> <Register /> </>} />
        		<Route path='/login' element={<Login />} />
				<Route path='/login/restore-password' element={<RestorePassword />} />
				<Route path='/modify-account' element={<><Header/><Modify /></>} />
				<Route path='/add-post' element={<><Header /> <AddPost /></>} />
				<Route path='/my-posts' element={<><Header /> <MyPosts /></>} />
				<Route path='/modify-post/:id' element={<><Header /> <ModifyPost /> </>} />
				<Route path='/post/:id' element={<> <Header /> <PostDetails /> </>} />
				<Route path='/catalog' element={<> <Header /> <Catalog /> </>} />
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
