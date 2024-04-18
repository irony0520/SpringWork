import {Routes, Route} from "react-router-dom"
import MainPage from "./pages/MainPage";
import UserPage from "./pages/UserPage";
import AdminPage from "./pages/AdminPage";
import LoginPage from "./pages/LoginPage";



import {
    MAIN,
    ADMIN_MAIN,
    USER_MAIN,
    SIGN_UP,
    LOGIN
} from "./constants/page_constant";
import SignUpPage from "./pages/SignUpPage";

function App() {
    return (
    <>
        <Routes>
            <Route path={MAIN} element = {<MainPage/>} />
            <Route path={USER_MAIN} element = {<UserPage/>} />
            <Route path={ADMIN_MAIN} element = {<AdminPage/>} />
            <Route path={SIGN_UP} element = {<SignUpPage/>} />
            <Route path={LOGIN} element = {<LoginPage/>} />
        </Routes>
    </>
  )
    
  
}

export default App;
