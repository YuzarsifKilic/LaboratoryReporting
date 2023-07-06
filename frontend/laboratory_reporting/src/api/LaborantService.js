import { apiClient } from "./BaseApiService";

export const getAllLaborants = () => apiClient.get("/api/v1/laborant/getall")