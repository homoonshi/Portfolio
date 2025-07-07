import { useMutation } from "@tanstack/react-query";
import axios from "axios";

const loginUrl = import.meta.env.VITE_LOGIN_URL;

function useLogin() {
    return useMutation({
        mutationFn : async({id, pass} : {id :string; pass: string}) => {
        try{    
            const response = await axios.post(`http://localhost:8080${loginUrl!}`, {
                    username : id,
                    password : pass
                },{
                    withCredentials: true,
                    headers: {
                        'Content-Type' : 'application/x-www-form-urlencoded',
                    },
                });
                localStorage.setItem('accessToken', response.headers.authorization.split(" ")[1]);
                return response.status;
            } catch(error){
                console.log(error);
                throw error;
            }
        },
    });
}

export default useLogin;