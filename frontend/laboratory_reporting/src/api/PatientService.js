import { apiClient } from "./BaseApiService";

export const getAllPatients = () => apiClient.get("/api/v1/patient/getall")