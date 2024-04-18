import axios from "axios";
import { TextField } from "../components/TextField";
import { SIGN_UP_API } from "../constants/api_constant";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { LOGIN } from "../constants/page_constant";

export default function SignUpPage() {
    
    const [id,setId] = useState("");
    const [name,setName] =useState("");
    const [password,setPassword] =useState("");
    const [error,setError] = useState("");
    const API_BASE_URL = process.env.REACT_APP_API_BASE_URL;
    const navigate = useNavigate();

    const handleSignUp = async (e)=> {
        e.preventDefault();

        try{
            const formData = new FormData();
            //뒤의 값은 state의 id
            formData.append("id",id);
            formData.append("name",name);
            formData.append("password",password);

            //비동기 post요청(axios)
            const response = await axios.post(
                //.env , api_constant 에 선언
                API_BASE_URL +  SIGN_UP_API,
                formData
            )
            alert(response.data);
            navigate(LOGIN);
            
        }catch(error){
            
            setError(error.response.data);
        }
    }


    return (
        <>
        <form onSubmit={handleSignUp}>
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

                label="이름"
                type="text"
                // ...props
                name="name"
                required
                value={name}
                onChange={(e) => setName(e.target.value)}
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
            <button type="submit">회원가입</button>

        </form>
        
        </>
    )
}