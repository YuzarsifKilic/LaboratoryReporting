import './App.css';
import {BrowserRouter, Navigate, Route, Routes, useNavigate} from "react-router-dom";
import LoginPage from './pages/LoginPage';
import ReportPage from './pages/ReportPage';
import ReportDetail from './pages/ReportDetail';
import AddReport from './pages/AddReport';
import { useAuth } from './security/AuthContext';

function AuthenticatedRoute({children}) {
  const {  isAuthenticated } = useAuth()
  const navigate = useNavigate()

  if (isAuthenticated)
    return children

  return <Navigate to={"/"} />
}

function App() {
  return (
    <div className="App">
      <BrowserRouter>
      <Routes>
        <Route path='/' element={<LoginPage/>}/>
        <Route path='/report' element={
          <AuthenticatedRoute>
            <ReportPage/>
          </AuthenticatedRoute>
          
        }/>
        <Route path='/reportDetail' element={
          <AuthenticatedRoute>
            <ReportDetail/>
          </AuthenticatedRoute>
        }/>
        <Route path='/addReport' element={
          <AuthenticatedRoute>
            <AddReport/>    
          </AuthenticatedRoute>
        }/>
      </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
