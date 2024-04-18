import { useState } from "react"
import { TextField } from "../components/TextField";
import { Link } from "react-router-dom";
import { SIGN_UP } from "../constants/page_constant";

export default function LoginPage() {

    const [id,setId] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");
    const API_BASE_URL = process.env.REACT_APP_API_BASE_URL;

    const handleLogin = async (e) => {
        e.preventDefault();
    }



    return(
        <>
            <form onSubmit={handleLogin}>
            <TextField

                label="아이디"
                type="text"
                // ...props
                name="id"
                required
                value={id}
                onChange={(e) => setId(e.target.value)}
            />
        
            <TextField

                label="비밀번호"
                type="password"
                // ...props
                name="password"
                required
                value={password}
                onChange={(e) => setPassword(e.target.value)}
            />
            {error && <p style={{color:'red'}}>{error}</p>}
            <button type="submit">로그인</button>
            <Link to={SIGN_UP}>회원가입 하러가기</Link>

        </form>
        
        </>
    


    )
}