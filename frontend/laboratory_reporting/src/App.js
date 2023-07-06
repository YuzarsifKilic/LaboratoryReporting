import './App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import LoginPage from './pages/LoginPage';
import ReportPage from './pages/ReportPage';
import ReportDetail from './pages/ReportDetail';
import AddReport from './pages/AddReport';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
      <Routes>
        <Route path='/' element={<LoginPage/>}/>
        <Route path='/report' element={<ReportPage/>}/>
        <Route path='/reportDetail' element={<ReportDetail/>}/>
        <Route path='/addReport' element={<AddReport/>}/>
      </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
