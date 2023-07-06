import React, { useEffect, useState } from 'react'
import { useLocation, useNavigate } from 'react-router-dom';
import { deleteReport, getReportById, updateReport } from '../api/ReportService';
import { useAuth } from '../security/AuthContext';

export default function ReportDetail() {

    const location = useLocation();
    const data = location.state.id;
    const navigate = useNavigate()

    const { role } = useAuth()

    const [report, setreport] = useState({})

    const [edit, setEdit] = useState(false)

    const [diagnosisHeader, setDiagnosisHeader] = useState(null)

    const [diagnosisDescription, setDiagnosisDescription] = useState(null)

    const handleDiagnosisHeader = event  => setDiagnosisHeader(event.target.value);

    const handleDiagnosisDescription = event  => setDiagnosisDescription(event.target.value);

    useEffect(() => {
        getReportById(data).then((response) => setreport(response.data))
    })

    function handleUpdateButton() {
        setEdit(!edit)
    }

    function updateTheReport() {
        updateReport(report.id, diagnosisHeader, diagnosisDescription).then((response) => {
            console.log(response)
        })
    }

    function deleteTheReport() {
        deleteReport(report.id).then((response) => {
            setreport({})
            if (response.status === 200)
                navigate("/report")
        })
    }

    return (
        <div className=' bg-gray-200 mt-36 m-24 w-2/3 ml-80 p-8 rounded-xl grid justify-items-stretch text-blue-900'>
            <div className='justify-self-start'>
                Hasta Adı:  {report.patientFirstName}
            </div>
            <div className='justify-self-start mt-2'>
                Hasta Soyadı: {report.patientLastName}
            </div>
            <div className='justify-self-start mt-2'>
                Hasta Kimlik Numarası: {report.patientIdentificationNumber}
            </div>
            {
                edit ? 
                <div className='grid justify-items-start'>
                    <div className='justify-self-start mt-8'>
                        Koyulan Tanı: <input
                        onChange={handleDiagnosisHeader}
                        value={diagnosisHeader} 
                        className="rounded-xl p-1 ml-4" 
                        />
                    </div>
                    <div className='justify-self-start mt-2'>
                        Tanı Detayları: <input
                        onChange={handleDiagnosisDescription}
                        value={diagnosisDescription} 
                        className="rounded-xl p-1 ml-3"
                        />
                    </div>
                    <div className='justify-self-start mt-2 ml-56'>
                        <button
                        onClick={updateTheReport} 
                            className='bg-green-400 text-white rounded-xl p-1'
                        >
                            Güncelle
                        </button>
                    </div>
                </div> :
                <div className='grid justify-items-start'>
                    <div className='justify-self-start mt-8'>
                        Koyulan Tanı: {report.diagnosisHeader}
                    </div>
                    <div className='justify-self-start mt-2'>
                        Tanı Detayları: {report.diagnosisDescription}
                    </div>
                </div>
            }
            
            <div className='justify-self-start mt-2'>
                Rapor Tarihi: {report.reportDate}
            </div>
            <div className='justify-self-start mt-8'>
                Laborant Adı: {report.laborantFirstName}
            </div>
            <div className='justify-self-start mt-2'>
                Laborant Soyadı: {report.laborantLastName}
            </div>
            <div className='justify-self-start mt-2'>
                Hastane Numarası: {report.hospitalNumber}
            </div>
            <div className='justify-self-end'>
                <button 
                    className='bg-orange-400 text-white p-2 rounded-xl'
                    onClick={handleUpdateButton}
                >
                    Düzenle
                </button>
                {role === "ADMIN" && <button
                    onClick={deleteTheReport} 
                    className='bg-red-600 text-white p-2 rounded-xl ml-4'
                >
                    Raporu Sil
                </button>}
            </div>
        </div>
    )
}