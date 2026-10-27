import { useState } from 'react'
import heroImg from './assets/hero.png'
import reactLogo from './assets/react.svg'
import viteLogo from './assets/vite.svg'
import './App.css'
import SampleCode from './component/SampleCode'
import UseFetchApi from './component/UseFetchApi'
import{BrowserRouter, Routes, Route} from 'react-router-dom';
import Home from './component/Home';
import Login from './component/Login';
import Registration from './component/Registration';
import Dashboard from './component/Dashboard';
function App() {


  return (
    
    <div>
      <BrowserRouter>
      <Routes>
      <Route path='/' element={<Home></Home>}></Route> 
      <Route path='/login' element={<Login></Login>}></Route>
      <Route path='/register' element={<Registration></Registration>}></Route>
      <Route path='/dashboard' element={<Dashboard></Dashboard>}></Route>
      </Routes>
      </BrowserRouter>


    </div>
  )
}
export default App
