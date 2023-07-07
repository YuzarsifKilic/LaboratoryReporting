
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

            if (response.status === 200) {
                setJwtToken("Bearer " + response.data.accessToken)
                setRole(response.data.user.role)
                setUsername(response.data.user.username)
                setIsAuthenticated(true)
                console.log(jwtToken)

                apiClient.interceptors.request.use(
                    (config) => {
                        config.headers.Authorization = "Bearer " + response.data.accessToken
                        return config
                    }
                )
                return true
            } else {
                return false
            }
        } catch(error) {
            console.log(error)
            return false
        }
    }

    function logout() {
        setIsAuthenticated(false)
    }

    return (
        <AuthContext.Provider value={ {isAuthenticated, login, role} } >
            {children}
        </AuthContext.Provider>
    )
}