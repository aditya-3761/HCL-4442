
import React from 'react';
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import Login from './component/Login';
import Registration from './component/Registration';
import Home from './component/Home';
import Dashboard from './component/Dashboard';

import './hrms.css';

function App() {
    return (
        <BrowserRouter>
            <div className="hrms-app">
                <Routes>
                    <Route
                        path="/"
                        element={<Navigate to="/login" replace />}
                    />

                    <Route path="/register" element={<Registration />} />
                    <Route path="/login" element={<Login />} />
                    <Route path="/home" element={<Home />} />
                    <Route path="/dashboard" element={<Dashboard />} />

                    <Route
                        path="*"
                        element={<Navigate to="/login" replace />}
                    />
                </Routes>
            </div>
        </BrowserRouter>
    );
}

export default App;
