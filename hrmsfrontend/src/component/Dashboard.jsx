
import React, { useCallback, useEffect, useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';

function cleanEmployee(data) {
    if (!data) return null;

    const { password, ...safeEmployee } = data;
    return safeEmployee;
}

function getSavedEmployee() {
    try {
        const saved = sessionStorage.getItem('hrmsEmployee');

        return saved ? cleanEmployee(JSON.parse(saved)) : null;
    } catch {
        return null;
    }
}

function formatDate(value) {
    if (!value) return '-';

    const parts = String(value).split('T')[0].split('-');

    if (parts.length !== 3) return value;

    return `${parts[2]}-${parts[1]}-${parts[0]}`;
}

function formatDateTime(value) {
    if (!value) return null;

    const parts = String(value).split('T');
    const timePart = parts[1];

    if (!timePart) {
        return (
            <div>{formatDate(parts[0])}</div>
        );
    }

    const values = timePart.split(':');
    const hours = Number(values[0]);
    const minutes = values[1] || '00';
    const seconds = (values[2] || '00').split('.')[0];

    const time =
        `${String(hours % 12 || 12).padStart(2, '0')}:` +
        `${minutes}:${seconds} ${hours >= 12 ? 'PM' : 'AM'}`;

    return (
        <div>
            <div>{formatDate(parts[0])}</div>
            <small className="hrms-table-subtext">{time}</small>
        </div>
    );
}

function Dashboard() {
    const location = useLocation();
    const navigate = useNavigate();

    const [employee] = useState(
        () => cleanEmployee(location.state?.employee) || getSavedEmployee()
    );

    const [attendance, setAttendance] = useState([]);
    const [loading, setLoading] = useState(true);
    const [message, setMessage] = useState('');

    useEffect(() => {
        if (!employee) {
            navigate('/login', { replace: true });
            return;
        }

        sessionStorage.setItem(
            'hrmsEmployee',
            JSON.stringify(cleanEmployee(employee))
        );
    }, [employee, navigate]);

    const loadAttendance = useCallback(async () => {
        if (!employee?.emp_id) {
            setLoading(false);
            return;
        }

        setLoading(true);
        setMessage('');

        try {
            const response = await fetch(
                `http://localhost:8080/v1/attendance/employee/${employee.emp_id}`
            );

            if (!response.ok) {
                throw new Error('Unable to load attendance records.');
            }

            const data = await response.json();

            setAttendance(Array.isArray(data) ? data : []);
        } catch (error) {
            console.error('Dashboard attendance error:', error);
            setMessage(
                'Unable to load attendance records. Please try again.'
            );
        } finally {
            setLoading(false);
        }
    }, [employee?.emp_id]);

    useEffect(() => {
        if (employee?.emp_id) {
            loadAttendance();
        }
    }, [employee?.emp_id, loadAttendance]);

    function logout() {
        sessionStorage.removeItem('hrmsEmployee');
        navigate('/login', { replace: true });
    }

    if (!employee) {
        return <div className="hrms-page">Redirecting to login...</div>;
    }

    const completedDays = attendance.filter(
        (item) => item.outTime
    ).length;

    const openDays = attendance.filter(
        (item) => !item.outTime
    ).length;

    return (
        <div className="hrms-app">
            <header className="hrms-navbar">
                <div className="hrms-brand">
                    <div className="hrms-brand-mark">HR</div>
                    <div>
                        <h2 className="hrms-brand-name">HRMS Portal</h2>
                        <span className="hrms-brand-caption">
                            Employee workspace
                        </span>
                    </div>
                </div>

                <nav className="hrms-nav-actions">
                    <button
                        className="hrms-nav-link"
                        onClick={() => navigate('/home', {
                            state: { employee }
                        })}
                    >
                        Home
                    </button>

                    <span className="hrms-nav-link active">
                        Dashboard
                    </span>

                    <button
                        className="hrms-button hrms-button-danger"
                        onClick={logout}
                    >
                        Log out
                    </button>
                </nav>
            </header>

            <main className="hrms-page">
                <section className="hrms-hero">
                    <span className="hrms-eyebrow">
                        EMPLOYEE DASHBOARD
                    </span>

                    <h1>Attendance & time records</h1>

                    <p>
                        Review your recorded attendance, check-in times,
                        and check-out times in one place.
                    </p>
                </section>

                <div className="hrms-page-heading">
                    <div>
                        <span className="hrms-eyebrow">
                            YOUR RECORDS
                        </span>

                        <h1>Attendance history</h1>

                        <p>
                            {employee.emp_name} · Employee ID {employee.emp_id}
                        </p>
                    </div>

                    <button
                        className="hrms-button hrms-button-light"
                        onClick={() => navigate('/home', {
                            state: { employee }
                        })}
                    >
                        ← Back to Home
                    </button>
                </div>

                <section className="hrms-grid">
                    <div className="hrms-stat-card">
                        <div className="hrms-stat-top">
                            <span className="hrms-stat-icon">#</span>
                        </div>

                        <div className="hrms-stat-label">
                            Total attendance records
                        </div>

                        <div className="hrms-stat-value">
                            {attendance.length}
                        </div>
                    </div>

                    <div className="hrms-stat-card">
                        <div className="hrms-stat-top">
                            <span className="hrms-stat-icon">✓</span>
                        </div>

                        <div className="hrms-stat-label">
                            Completed check-outs
                        </div>

                        <div className="hrms-stat-value">
                            {completedDays}
                        </div>
                    </div>

                    <div className="hrms-stat-card">
                        <div className="hrms-stat-top">
                            <span className="hrms-stat-icon">◷</span>
                        </div>

                        <div className="hrms-stat-label">
                            Check-out pending
                        </div>

                        <div className="hrms-stat-value">
                            {openDays}
                        </div>
                    </div>

                    <div className="hrms-stat-card">
                        <div className="hrms-stat-top">
                            <span className="hrms-stat-icon">ID</span>
                        </div>

                        <div className="hrms-stat-label">
                            Employee ID
                        </div>

                        <div className="hrms-stat-value">
                            {employee.emp_id}
                        </div>
                    </div>
                </section>

                <section className="hrms-section-card">
                    <div className="hrms-section-header">
                        <div>
                            <h2>Attendance records</h2>
                            <p>
                                Your personal attendance details
                            </p>
                        </div>

                        <button
                            className="hrms-button hrms-button-light"
                            onClick={loadAttendance}
                            disabled={loading}
                        >
                            {loading ? 'Loading...' : '↻ Refresh'}
                        </button>
                    </div>

                    {message && (
                        <div className="hrms-section-body">
                            <div className="hrms-message" role="alert">
                                {message}
                            </div>
                        </div>
                    )}

                    {loading ? (
                        <div className="hrms-section-body">
                            <div className="hrms-empty-state">
                                <h3>Loading attendance records</h3>
                                <p>Please wait while your records are loaded.</p>
                            </div>
                        </div>
                    ) : attendance.length === 0 ? (
                        <div className="hrms-section-body">
                            <div className="hrms-empty-state">
                                <div className="hrms-empty-icon">◷</div>
                                <h3>No attendance records yet</h3>
                                <p>
                                    After you mark attendance from Home,
                                    your records will appear here.
                                </p>

                                <button
                                    className="hrms-button hrms-button-primary mt-3"
                                    onClick={() => navigate('/home', {
                                        state: { employee }
                                    })}
                                >
                                    Go to Home
                                </button>
                            </div>
                        </div>
                    ) : (
                        <div className="hrms-table-wrap">
                            <table className="hrms-table">
                                <thead>
                                    <tr>
                                        <th>DATE</th>
                                        <th>EMPLOYEE ID</th>
                                        <th>EMPLOYEE NAME</th>
                                        <th>EMAIL</th>
                                        <th>DESIGNATION</th>
                                        <th>TECH STACK</th>
                                        <th>CHECK IN</th>
                                        <th>CHECK OUT</th>
                                        <th>STATUS</th>
                                    </tr>
                                </thead>

                                <tbody>
                                    {attendance.map((item) => (
                                        <tr key={item.attendanceId}>
                                            <td>
                                                {formatDate(item.attendancedate)}
                                            </td>

                                            <td>
                                                {item.employee?.emp_id || employee.emp_id}
                                            </td>

                                            <td>
                                                {item.employee?.emp_name || employee.emp_name}
                                            </td>

                                            <td>
                                                {item.employee?.email || employee.email}
                                            </td>

                                            <td>
                                                {item.employee?.designation || employee.designation}
                                            </td>

                                            <td>
                                                {item.employee?.stack || employee.stack}
                                            </td>

                                            <td>
                                                {formatDateTime(item.inTime)}
                                            </td>

                                            <td>
                                                {formatDateTime(item.outTime) || '-'}
                                            </td>

                                            <td>
                                                {item.outTime ? (
                                                    <span className="hrms-status">
                                                        Completed
                                                    </span>
                                                ) : (
                                                    <span className="hrms-status hrms-status-pending">
                                                        Checked in
                                                    </span>
                                                )}
                                            </td>
                                        </tr>
                                    ))}
                                </tbody>
                            </table>
                        </div>
                    )}
                </section>
            </main>

            <footer className="hrms-footer">
                HRMS Employee Workspace · Attendance Dashboard
            </footer>
        </div>
    );
}

export default Dashboard;
