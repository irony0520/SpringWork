import { useEffect, useState } from "react";
import Axios from 'axios';
import { MAIN_INIT_DATA_API } from "../constants/api_constant";


export default function MainPage() {


    const [initData, setInitData] = useState("서버 통신 불가");

    const API_BASE_URL = process.env.REACT_APP_API_BASE_URL;
     


    const fetchInitData = async () => {
        try{
            //서버로부터 localhost:8080/main 으로 get 요청, 데이터 받아옴(먼저 수행되어야함 -> 다른 명령들은 기다림(await))
            //받아온 데이터 setInitData를 사용, initData(state) 를 변경 
            const response =await Axios.get(
                API_BASE_URL +  MAIN_INIT_DATA_API
            );
            setInitData(response.data);
        }catch(error) {
            console.error("데이터 가져오기 오류",error);
        }
    }


    useEffect(()=> {
        //서버에서 데이터 받아옴 - initData(state)에 set하는 함수
        fetchInitData();
    },[]);




    return(
        <>
        <h1> 메인페이지 입니다.</h1>
        <h3> {initData} </h3>
        </>
    )

}


