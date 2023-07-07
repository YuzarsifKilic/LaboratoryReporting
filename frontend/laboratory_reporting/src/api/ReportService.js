import { apiClient } from "./BaseApiService";

export const getAllReports = () => apiClient.get("/api/v1/report/getall")

export const getReportById = (id) => apiClient.get(`/api/v1/report/getReport/${id}`)

export const updateReport = (id, diagnosisHeader, diagnosisDescription) => apiClient.put(`/api/v1/report/${id}`,
    {
        diagnosisHeader: diagnosisHeader,
        diagnosisDescription: diagnosisDescription
    }
)

export const deleteReport = (id) => apiClient.delete(`/api/v1/report/${id}`)

export const uploadPhotoOfReport = (id, image) => apiClient.post(`/api/v1/report/saveImage/${id}`,
    image,
    {
        'Content-Type' : 'multipart/form-data'
    }
)