import React, { useEffect, useState, Fragment, useCallback } from 'react'
import { getAllLaborants } from '../api/LaborantService'
import { getAllPatients } from '../api/PatientService'
import { Listbox, Transition } from '@headlessui/react'
import { CheckIcon, ChevronUpDownIcon } from '@heroicons/react/20/solid'
import { useDropzone } from 'react-dropzone'
import { uploadPhotoOfReport } from '../api/ReportService'



export default function AddReport() {

    const dummyLaborant = {
        id: 1,
        firstName: "",
        lastName: "",
        hospitalNumber: ""
    }

    const dummyPatient = {
      id: 1,
      firstName: "",
      lastName: "",
      identificationNumber: ""
    }

    const [laborants, setlaborants] = useState([dummyLaborant])

    const [patients, setpatients] = useState([dummyPatient])

    const [selectedLaborant, setSelectedLaborant] = useState(laborants[0])

    const [selectedPatient, setselectedPatient] = useState(patients[0])

    const [diagnosisHeader, setdiagnosisHeader] = useState("")

    const [diagnosisDescription, setdiagnosisDescription] = useState("")

    useEffect(() => {
      
        getAllLaborants().then((response) => setlaborants(response.data))

        getAllPatients().then((response) => setpatients(response.data))

    }, [])

    const handleDiagnosisHeader = event  => setdiagnosisHeader(event.target.value);

    const handleDiagnosisDescription= event  => setdiagnosisDescription(event.target.value);

    return (
      <div>
        <div className='mt-24 w-1/4 ml-96 bg-gray-300 p-4 rounded-xl grid grid-cols-1'>
          <div className='mt-8'>
          Hasta
            <Listbox value={selectedPatient} onChange={setselectedPatient}>
              <div className="relative mt-1">
                <Listbox.Button className="relative w-full text-blue-500 cursor-default rounded-lg bg-white py-2 pl-3 pr-10 text-left shadow-md focus:outline-none focus-visible:border-indigo-500 focus-visible:ring-2 focus-visible:ring-white focus-visible:ring-opacity-75 focus-visible:ring-offset-2 focus-visible:ring-offset-orange-300 sm:text-sm">
                  <span className="block truncate">{selectedPatient.firstName} {selectedPatient.lastName} {selectedPatient.identificationNumber}</span>
                  <span className="pointer-events-none absolute inset-y-0 right-0 flex items-center pr-2">
                    <ChevronUpDownIcon
                      className="h-5 w-5 text-gray-400"
                      aria-hidden="true"
                    />
                  </span>
                </Listbox.Button>
                <Transition
                  as={Fragment}
                  leave="transition ease-in duration-100"
                  leaveFrom="opacity-100"
                  leaveTo="opacity-0"
                >
                  <Listbox.Options className="absolute mt-1 max-h-60 w-full overflow-auto rounded-md bg-white py-1 text-base shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none sm:text-sm">
                    {patients.map((patient, patientId) => (
                      <Listbox.Option
                        key={patientId}
                        className={({ active }) =>
                          `relative cursor-default select-none py-2 pl-10 pr-4 ${
                            active ? 'bg-amber-100 text-amber-900' : 'text-gray-900'
                          }`
                        }
                        value={patient}
                      >
                        {({ selectedPatient }) => (
                          <>
                            <span
                              className={`block truncate ${
                                selectedPatient ? 'font-medium' : 'font-normal'
                              }`}
                            >
                              {patient.firstName} {patient.lastName} Kimlik Numarası : {patient.identificationNumber}
                            </span>
                            {selectedPatient ? (
                              <span className="absolute inset-y-0 left-0 flex items-center pl-3 text-amber-600">
                                <CheckIcon className="h-5 w-5" aria-hidden="true" />
                              </span>
                            ) : null}
                          </>
                        )}
                      </Listbox.Option>
                    ))}
                  </Listbox.Options>
                </Transition>
              </div>
            </Listbox>
          </div>
          <div className='mt-8'>
          <input
                        type='text'
                        name='diagnosisHeader'
                        id='diagnosisHeader'
                        autoComplete='diagnosisHeader'
                        placeholder='Tanı Başlığı'
                        onChange={handleDiagnosisHeader}
                        value={diagnosisHeader}
                        className="block w-full rounded-md border-0 py-1.5 pl-7 pr-20 text-gray-900 ring-0 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-blue-500 sm:text-sm sm:leading-6"
                    />
          </div>
          <div className='mt-8'>
          <input
                        type='text'
                        name='diagnosisDescription'
                        id='diagnosisDescription'
                        autoComplete='diagnosisDescription'
                        placeholder='Tanı Detayları'
                        onChange={handleDiagnosisDescription}
                        value={diagnosisDescription}
                        className="block w-full rounded-md border-0 py-1.5 pl-7 pr-20 text-gray-900 ring-0 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-blue-500 sm:text-sm sm:leading-6"
                    />
          </div>
          <div className='mb-4 mt-4'>
          Laborant
            <Listbox value={selectedLaborant} onChange={setSelectedLaborant}>
              <div className="relative mt-1">
                <Listbox.Button className="relative w-full text-blue-500 cursor-default rounded-lg bg-white py-2 pl-3 pr-10 text-left shadow-md focus:outline-none focus-visible:border-indigo-500 focus-visible:ring-2 focus-visible:ring-white focus-visible:ring-opacity-75 focus-visible:ring-offset-2 focus-visible:ring-offset-orange-300 sm:text-sm">
                  <span className="block truncate">{selectedLaborant.firstName} {selectedLaborant.lastName} {selectedLaborant.hospitalNumber}</span>
                  <span className="pointer-events-none absolute inset-y-0 right-0 flex items-center pr-2">
                    <ChevronUpDownIcon
                      className="h-5 w-5 text-gray-400"
                      aria-hidden="true"
                    />
                  </span>
                </Listbox.Button>
                <Transition
                  as={Fragment}
                  leave="transition ease-in duration-100"
                  leaveFrom="opacity-100"
                  leaveTo="opacity-0"
                >
                  <Listbox.Options className="absolute mt-1 max-h-60 w-full overflow-auto rounded-md bg-white py-1 text-base shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none sm:text-sm">
                    {laborants.map((laborant, laborantId) => (
                      <Listbox.Option
                        key={laborantId}
                        className={({ active }) =>
                          `relative cursor-default select-none py-2 pl-10 pr-4 ${
                            active ? 'bg-amber-100 text-amber-900' : 'text-gray-900'
                          }`
                        }
                        value={laborant}
                      >
                        {({ selectedLaborant }) => (
                          <>
                            <span
                              className={`block truncate ${
                                selectedLaborant ? 'font-medium' : 'font-normal'
                              }`}
                            >
                              {laborant.firstName} {laborant.lastName} Hastane Numarası : {laborant.hospitalNumber}
                            </span>
                            {selectedLaborant ? (
                              <span className="absolute inset-y-0 left-0 flex items-center pl-3 text-amber-600">
                                <CheckIcon className="h-5 w-5" aria-hidden="true" />
                              </span>
                            ) : null}
                          </>
                        )}
                      </Listbox.Option>
                    ))}
                  </Listbox.Options>
                </Transition>
              </div>
            </Listbox>
          </div>
          <div>
              <button className='text-white w-24 bg-lime-500 rounded-2xl p-2'>
                Kaydet
              </button>
          </div>         
          </div>
          <div>
          <Dropzone/>
          </div>
        </div>
    )
}

function Dropzone() {
  const onDrop = useCallback(acceptedFiles => {
    const formData = new FormData();
    formData.append("image", acceptedFiles[0])
    uploadPhotoOfReport(1, formData).then((response) => console.log(response))
  })

  const { getRootProps, getInputProps } = useDropzone({ onDrop })

  return (
    <div {...getRootProps()}>
      <input {...getInputProps()}/>
      <p>Drag</p>
    </div>
  )
}


 