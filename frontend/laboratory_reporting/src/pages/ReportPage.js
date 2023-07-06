import React, { useEffect, useState } from 'react'
import { Space, Table, Tag } from 'antd';
import { getAllReports } from '../api/ReportService'
import Column from 'antd/es/table/Column';
import { useNavigate } from 'react-router-dom';

export default function ReportPage() {

    const [report, setReport] = useState([])

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

    function addReport() {
        navigate("/addReport")
    }


    return (
        <div className='m-24'>
            <button
            onClick={addReport} 
                className='m-4 bg-lime-400 text-white p-2 rounded-xl w-48'
            >
                Rapor Ekle
            </button>
            <Table dataSource={report}>
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