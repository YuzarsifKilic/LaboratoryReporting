import { apiClient } from "./BaseApiService";

export const getAllReports = () => apiClient.get("/api/v1/report/getall")

export const getReportById = (id) => apiClient.get(`/api/v1/report/getReport/${id}`)

export const createReport = (diagnosisHeader, diagnosisDescription, patientId, laborantId) => apiClient.post(
    "/api/v1/report/create", 
    {
        diagnosisHeader: diagnosisHeader,
        diagnosisDescription: diagnosisDescription,
        patientId: patientId,
        laborantId: laborantId
    }
)

export const updateReport = (id, diagnosisHeader, diagnosisDescription) => apiClient.put(`/api/v1/report/${id}`,
    {
        diagnosisHeader: diagnosisHeader,
        diagnosisDescription: diagnosisDescription
    }
)

export const deleteReport = (id) => apiClient.delete(`/api/v1/report/${id}`)

export const getReportsByLaborant = (firstName, lastName) => apiClient.post(
    "/api/v1/report/getByLaborant",
    {
        firstName:  firstName,
        lastName: lastName
    }  
)

export const getReportsByPatient = (firstName, lastName) => apiClient.post(
    "/api/v1/report/getByPatient",
    {
        firstName:  firstName,
        lastName: lastName
    }       
)

export const getReportByDate = () => apiClient.get("/api/v1/report/getByDate")

export const uploadPhotoOfReport = (id, file) => apiClient.post(`/api/v1/report/saveImage/${id}`,
    file,
    {
        'Content-Type' : 'multipart/form-data'
    }
)

export const downloadTheImage = (id) => apiClient.get(`http://localhost:8080/api/v1/report/getImage/${id}`, { responseType: 'arraybuffer' })