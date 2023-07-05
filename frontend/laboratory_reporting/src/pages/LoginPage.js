
import React, { useContext } from 'react';
import { useState } from 'react';
import { useAuth } from '../security/AuthContext';

export default function LoginPage() {

    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")

    const { login } = useAuth()

    const handleUsername = event  => setUsername(event.target.value);

    const handlePassword = event  => setPassword(event.target.value);
    

     function handleClick() {
        console.log(username)
        console.log(password)
        login(username, password)
    }

    return (
        <div className='bg-gray-300 p-28 rounded-xl mt-24'>
            <div>
                <label>Kullanıcı Adı</label>
                <div className='mt-2 w-96'>
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
            <div className='mt-4'>
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
                    className='mt-8 bg-slate-800 rounded-xl p-2 text-white ml-36'
                >
                    Giriş yap
                </button>
            </div>
        </div>
    )
}