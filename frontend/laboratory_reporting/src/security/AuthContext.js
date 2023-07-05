
import { createContext, useContext } from "react";
import { useState } from 'react';
import { executeJwtToken } from "../api/AuthenticationService";
import { apiClient } from "../api/BaseApiService";

export const AuthContext = createContext()

export const useAuth = () => useContext(AuthContext)

export default function AuthProvider( { children } ) {

    const [isAuthenticated, setIsAuthenticated] = useState(false)

    const [username, setUsername] = useState("")

    const [role, setRole] = useState("")

    const [jwtToken, setJwtToken] = useState("")

    async function login(username, password) {
        try {
            const response = await executeJwtToken(username, password)

            if (response.status == 200) {
                setJwtToken("Bearer " + response.data.accessToken)
                setRole(response.data.user.role)
                setUsername(response.data.user.username)

                apiClient.interceptors.request.use(
                    (config) => {
                        console.log("its working for now :)")
                        config.headers.Authorization = jwtToken
                        return config
                    }
                )
            }
        } catch(error) {
            console.log(error)
        }
    }

    function logout() {
        setIsAuthenticated(false)
    }

    return (
        <AuthContext.Provider value={ {isAuthenticated, login} } >
            {children}
        </AuthContext.Provider>
    )
}