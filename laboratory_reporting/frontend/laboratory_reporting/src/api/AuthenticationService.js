import { apiClient } from "./BaseApiService";

export const executeJwtToken = (username, password) => apiClient.post(
    `/api/v1/user/login`,
    {
        username: username,
        password: password
    }
)