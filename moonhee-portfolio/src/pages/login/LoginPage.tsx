import { useState, useEffect } from "react";
import useLogin from "../../reactQuery/login/loginQuery";

export const LoginPage = () => {

    const [id, setId] = useState("");
    const [pass, setPass] = useState("");

    const loginMutation = useLogin();

    const [buttonText, setButtonText] = useState("로그인");
    
    useEffect(() => {
        if (loginMutation.isPending) setButtonText("로그인 중...");
        else if (loginMutation.isSuccess) setButtonText("로그인 성공");
        else if (loginMutation.isError) setButtonText("로그인 실패");
        else setButtonText("로그인");
    }, [loginMutation.isPending, loginMutation.isSuccess, loginMutation.isError]);


    return (
    <div className="w-full flex bg-gray-500">
        <div className="flex w-full items-center justify-center">
            <div className="flex flex-col my-2 py-3">
                <p className="text-xl text-center justify-center mb-4">로그인</p>
                <input 
                className="bg-white border rounded-sm"
                placeholder="아이디"
                value={id}
                onChange={(e) => setId(e.target.value)}
                ></input>
                <input 
                className="bg-white border rounded-sm"
                placeholder="비밀번호"
                type="password"
                value={pass}
                onChange={(e) => setPass(e.target.value)}
                ></input>
                <button
                    onClick={() => loginMutation.mutate({ id, pass })}
                    className="bg-blue-500 text-white px-4 py-2 rounded mt-2"
                >
                    {buttonText}
                </button>
            </div>
        </div>
    </div>
    );
};