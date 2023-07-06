import React, { useEffect, useState, Fragment } from 'react'
import { getAllLaborants } from '../api/LaborantService'
import { getAllPatients } from '../api/PatientService'
import { Listbox, Transition } from '@headlessui/react'
import { CheckIcon, ChevronUpDownIcon } from '@heroicons/react/20/solid'

export default function AddReport() {

    const dummyLaborant = {
        id: 1,
        firstName: "",
        lastName: "",
        hospitalNumber: ""
    }

    const [laborants, setlaborants] = useState([dummyLaborant])

    const [patients, setpatients] = useState([])

    const [selected, setSelected] = useState(laborants[0])

    useEffect(() => {
      
        getAllLaborants().then((response) => setlaborants(response.data))

        getAllPatients().then((response) => setpatients(response.data))

    })
    

    return (
        <div className='mt-24 w-96'>
            <Listbox value={selected} onChange={setSelected}>
        <div className="relative mt-1">
          <Listbox.Button className="relative w-full text-blue-500 cursor-default rounded-lg bg-white py-2 pl-3 pr-10 text-left shadow-md focus:outline-none focus-visible:border-indigo-500 focus-visible:ring-2 focus-visible:ring-white focus-visible:ring-opacity-75 focus-visible:ring-offset-2 focus-visible:ring-offset-orange-300 sm:text-sm">
            <span className="block truncate">{selected.firstName} {selected.lastName} Hastane Numarası : {selected.hospitalNumber}</span>
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
                  {({ selected }) => (
                    <>
                      <span
                        className={`block truncate ${
                          selected ? 'font-medium' : 'font-normal'
                        }`}
                      >
                        {laborant.firstName} {laborant.lastName} Hastane Numarası : {laborant.hospitalNumber}
                      </span>
                      {selected ? (
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
    )
}