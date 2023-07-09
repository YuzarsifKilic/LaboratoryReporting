
import React, { useContext } from 'react';
import { useState } from 'react';
import { useAuth } from '../security/AuthContext';
import { useNavigate } from 'react-router-dom';
import { Button, Result } from 'antd';

export default function LoginPage() {

    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")
    const [error, setError] = useState(false)

    const { login, isAuthenticated } = useAuth()

    const navigate = useNavigate()

    const handleUsername = event  => setUsername(event.target.value);

    const handlePassword = event  => setPassword(event.target.value);
    

     async function handleClick() {
        console.log(username)
        console.log(password)
        if (await login(username, password))
            navigate("/report")

        if (!isAuthenticated)
            setError(true)
    }

    return (
        <div className='grid justify-items-stretch'>
            <div className='justify-self-center bg-gray-300 w-1/3 mt-48 p-28 rounded-xl grid justify-items-stretch'>
                <div className='justify-self-start'>
                    <label>Kullanıcı Adı</label>
                    <div className='mt-4 w-96'>
                        <input
                            type='text'
                            name='username'
                            id='username'
                            autoComplete='username'
                            placeholder='username'
                            onChange={handleUsername}
                            value={username}
                            className="block w-full rounded-md border-0 py-1.5 pl-7 pr-20 text-gray-900 ring-0 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-blue-500 sm:text-sm sm:leading-6"

                        />
                    </div>
                </div>
                <div className='mt-4 justify-self-start'>
                    <label>Parola</label>
                    <div className='mt-2 w-96'>
                        <input
                            type='password'
                            name='password'
                            id='password'
                            autoComplete='password'
                            onChange={handlePassword}
                            value={password}
                            placeholder='parola'
                            className="block w-full rounded-md border-0 py-1.5 pl-7 pr-20 text-gray-900 ring-0 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-blue-500 sm:text-sm sm:leading-6"

                        />
                    </div>
                    <button 
                        onClick={handleClick}
                        type='button'
                        name='button'
                        className='mt-8 bg-slate-800 rounded-xl p-2 text-white ml-4'
                    >
                        Giriş Yap
                    </button>
                    {
                        error &&
                            <div className='mt-4 text-white bg-red-600 rounded-xl'>Giriş Bilgileri Yanlış Tekrar Deneyin</div>
                    }
                </div>
            </div>
        </div>
    )
}