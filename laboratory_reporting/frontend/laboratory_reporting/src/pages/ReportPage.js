import React, { useEffect, useState } from 'react'
import { Space, Table, Tag } from 'antd';
import { getAllReports, getReportByDate, getReportsByLaborant, getReportsByPatient } from '../api/ReportService'
import Column from 'antd/es/table/Column';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { apiClient } from '../api/BaseApiService';
import { Buffer } from 'buffer';

export default function ReportPage() {

    const [report, setReport] = useState([])

    const [imageSource, setImageSource] = useState(null)

    const [patientFirstName, setpatientFirstName] = useState(null)

    const [patientLastName, setpatientLastName] = useState(null)

    const [patientSearchButton, setpatientSearchButton] = useState(false)

    const [laborantFirstName, setlaborantFirstName] = useState(null)

    const [laborantLastName, setlaborantLastName] = useState(null)

    const [laborantSearchButton, setlaborantSearchButton] = useState(false)

    const navigate = useNavigate()

    useEffect(() => {
        refrechReports()
    }, [])

    function refrechReports() {
        getAllReports().then((response) => {
            console.log(response)
            setReport(response.data)
            console.log(response.data)
            }
        )
    }

    const handlePatientFirstName = event  => setpatientFirstName(event.target.value);

    const handlePatientLastName = event  => setpatientLastName(event.target.value);

    const handleLaborantFirstName = event  => setlaborantFirstName(event.target.value);

    const handleLaborantLastName = event  => setlaborantLastName(event.target.value);

    function addReport() {
        navigate("/addReport")
    }

    function searchByPatient() {
        getReportsByPatient(patientFirstName, patientLastName).then((response) => setReport(response.data)
        )
    }

    function searchByLaborant() {
        getReportsByLaborant(laborantFirstName, laborantLastName).then((response) => setReport(response.data))
    }

    function searchByDate() {
        getReportByDate().then((response) => {
            console.log(response)
            setReport(response.data.reverse())

        })
    }


    return (
        <div className='m-24'>
            <div>
            <button
                onClick={addReport} 
                className='m-4 bg-lime-400 text-white p-2 rounded-xl w-48'
            >
                Rapor Ekle
            </button>
            <div className='grid grid-cols-4'>
                <input
                        type='text'
                        name='search'
                        id='search'
                        autoComplete='search'
                        placeholder='Hasta Adı'
                        onChange={handlePatientFirstName}
                        value={patientFirstName}
                        className="block bg-gray-200 w-full rounded-md border-0 py-1.5 pl-7 pr-20 text-gray-900 ring-0 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-blue-500 sm:text-sm sm:leading-6"
                />
                <input
                        type='text'
                        name='search'
                        id='search'
                        autoComplete='search'
                        placeholder='Hasta Soyadı'
                        onChange={handlePatientLastName}
                        value={patientLastName}
                        className="block ml-4 bg-gray-200 w-full rounded-md border-0 py-1.5 pl-7 pr-20 text-gray-900 ring-0 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-blue-500 sm:text-sm sm:leading-6"
                />
                <button 
                    className='ml-8 bg-lime-400 text-white p-2 rounded-xl w-48'
                    onClick={searchByPatient}
                >
                        Ara
                </button>
            </div>
            <div className='grid grid-cols-4 mt-4'>
                <input
                    type='text'
                    name='search'
                    id='search'
                    autoComplete='search'
                    placeholder='Laborant Adı'
                    onChange={handleLaborantFirstName}
                    value={laborantFirstName}
                    className="block bg-gray-200 w-full rounded-md border-0 py-1.5 pl-7 pr-20 text-gray-900 ring-0 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-blue-500 sm:text-sm sm:leading-6"
                />
                <input
                    type='text'
                    name='search'
                    id='search'
                    autoComplete='search'
                    placeholder='Laborant Soyadı'
                    onChange={handleLaborantLastName}
                    value={laborantLastName}
                    className="block ml-4 bg-gray-200 w-full rounded-md border-0 py-1.5 pl-7 pr-20 text-gray-900 ring-0 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-blue-500 sm:text-sm sm:leading-6"
                />
                <button 
                    className='ml-8 bg-lime-400 text-white p-2 rounded-xl w-48'
                    onClick={searchByLaborant}
                >
                        Ara
                </button>
                <button className='ml-48 bg-lime-400 text-white p-2 rounded-xl w-48' onClick={searchByDate}>Tarihe Göre Sırala</button>   
            </div>
                   
            </div>
            <Table dataSource={report} className='mt-4'>
                <Column title="Hasta Adı" dataIndex="patientFirstName" key="patientFirstName" />
                <Column title="Hasta Soyadı" dataIndex="patientLastName" key="patientLastName" />
                <Column title="Hasta Kimlik Numarası" dataIndex="patientIdentificationNumber" key="patientIdentificationNumber" />
                <Column title="Tanı Başlığı" dataIndex="diagnosisHeader" key="diagnosisHeader" />
                <Column title="Rapor Tarihi" dataIndex="reportDate" key="reportDate" />
                <Column
                    key="action"
                    render={(_,record) => (
                        <button 
                            className='text-blue-600'
                            onClick={() => {
                                const id = _.id
                                navigate("/reportDetail", {state: {id}})
                            }} 
                        >
                            Raporu İncele
                        </button>
                    )}
                    />
            </Table>
        </div>
    )
}