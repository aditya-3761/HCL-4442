
import React, { useCallback, useEffect, useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';

function cleanEmployee(data) {
    if (!data) {
        return null;
    }

    const { password, ...safeEmployee } = data;
    return safeEmployee;
}

function getSavedEmployee() {
    try {
        const saved = sessionStorage.getItem('hrmsEmployee');

        if (!saved) {
            return null;
        }

        return cleanEmployee(JSON.parse(saved));
    } catch (error) {
        console.error('Unable to read saved employee:', error);
        return null;
    }
}

function getTodayString() {
    const date = new Date();

    return (
        date.getFullYear() + '-' +
        String(date.getMonth() + 1).padStart(2, '0') + '-' +
        String(date.getDate()).padStart(2, '0')
    );
}

function formatDate(value) {
    if (!value) {
        return '-';
    }

    const datePart = String(value).split('T')[0];
    const parts = datePart.split('-');

    if (parts.length !== 3) {
        return datePart;
    }

    const [year, month, day] = parts;

    return `${day}-${month}-${year}`;
}

function formatTime(value) {
    if (!value) {
        return '--:--';
    }

    const timePart = String(value).split('T')[1];

    if (!timePart) {
        return '--:--';
    }

    const parts = timePart.split(':');
    const hours = Number(parts[0]);
    const minutes = parts[1] || '00';
    const seconds = (parts[2] || '00').split('.')[0];

    const period = hours >= 12 ? 'PM' : 'AM';
    const formattedHours = String(hours % 12 || 12).padStart(2, '0');

    return `${formattedHours}:${minutes}:${seconds} ${period}`;
}

function Home() {
    const location = useLocation();
    const navigate = useNavigate();

    const [employee, setEmployee] = useState(() =>
        cleanEmployee(location.state?.employee) || getSavedEmployee()
    );

    const [attendance, setAttendance] = useState([]);
    const [message, setMessage] = useState('');
    const [loading, setLoading] = useState(false);
    const [attendanceLoaded, setAttendanceLoaded] = useState(false);

    const today = getTodayString();

    const todayAttendance = attendance.find(
        (item) => String(item.attendancedate).split('T')[0] === today
    );

    // Save safe employee details and redirect if no employee is available.
    useEffect(() => {
        if (!employee || !employee.emp_id) {
            navigate('/login', { replace: true });
            return;
        }

        sessionStorage.setItem(
            'hrmsEmployee',
            JSON.stringify(cleanEmployee(employee))
        );
    }, [employee, navigate]);

    // Load attendance records for the logged-in employee.
    const loadAttendance = useCallback(async () => {
        if (!employee?.emp_id) {
            setAttendanceLoaded(true);
            return;
        }

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
            console.error('Attendance loading error:', error);
            setMessage('Unable to load attendance history. Please try again.');
        } finally {
            setAttendanceLoaded(true);
        }
    }, [employee?.emp_id]);

    useEffect(() => {
        if (employee?.emp_id) {
            loadAttendance();
        }
    }, [employee?.emp_id, loadAttendance]);

    // Mark attendance IN.
    async function markAttendance() {
        if (!employee?.emp_id || loading) {
            return;
        }

        setLoading(true);
        setMessage('');

        try {
            const response = await fetch(
                'http://localhost:8080/v1/attendance/mark',
                {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        employee: {
                            emp_id: employee.emp_id
                        },
                        comment: 'Present'
                    })
                }
            );

            const result = await response.text();

            setMessage(result || 'Attendance request completed.');

            await loadAttendance();
        } catch (error) {
            console.error('Attendance check-in error:', error);
            setMessage('Unable to mark attendance. Please check the server.');
        } finally {
            setLoading(false);
        }
    }

    // Mark attendance OUT on the same attendance record.
    async function markMeOut() {
        if (!employee?.emp_id || loading) {
            return;
        }

        setLoading(true);
        setMessage('');

        try {
            const response = await fetch(
                `http://localhost:8080/v1/attendance/markout/${employee.emp_id}`,
                {
                    method: 'PUT'
                }
            );

            const result = await response.text();

            setMessage(result || 'Check-out request completed.');

            await loadAttendance();
        } catch (error) {
            console.error('Attendance check-out error:', error);
            setMessage('Unable to mark out. Please check the server.');
        } finally {
            setLoading(false);
        }
    }

    function goToDashboard() {
        navigate('/dashboard', {
            state: { employee }
        });
    }

    function logout() {
        sessionStorage.removeItem('hrmsEmployee');
        setEmployee(null);
        navigate('/login', { replace: true });
    }

    if (!employee || !employee.emp_id) {
        return (
            <div className="hrms-page">
                Redirecting to login...
            </div>
        );
    }

    const completedAttendance = attendance.filter(
        (item) => item.outTime
    ).length;

    const attendanceStatus = !attendanceLoaded
        ? 'Loading...'
        : !todayAttendance
            ? 'Not checked in'
            : todayAttendance.outTime
                ? 'Completed'
                : 'Checked in';

    return (
        <div className="hrms-app">

            {/* Navigation bar */}
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
                    <span className="hrms-nav-link active">
                        Home
                    </span>

                    <button
                        type="button"
                        className="hrms-nav-link"
                        onClick={goToDashboard}
                    >
                        Dashboard
                    </button>

                    <button
                        type="button"
                        className="hrms-button hrms-button-danger"
                        onClick={logout}
                    >
                        Log out
                    </button>
                </nav>
            </header>

            <main className="hrms-page">

                {/* Welcome banner */}
                <section className="hrms-hero">
                    <span className="hrms-eyebrow">
                        EMPLOYEE WORKSPACE
                    </span>

                    <h1>
                        Welcome back, {employee.emp_name}
                    </h1>

                    <p>
                        Manage your workday, record your attendance,
                        and keep track of your employee workspace
                        from one place.
                    </p>
                </section>

                {/* Page heading */}
                <div className="hrms-page-heading">
                    <div>
                        <span className="hrms-eyebrow">
                            OVERVIEW
                        </span>

                        <h1>Your workday</h1>

                        <p>
                            Employee ID: {employee.emp_id}
                            {' · '}
                            {employee.designation}
                        </p>
                    </div>

                    <button
                        type="button"
                        className="hrms-button hrms-button-primary"
                        onClick={goToDashboard}
                    >
                        View attendance records →
                    </button>
                </div>

                {/* Overview cards */}
                <section className="hrms-grid">

                    <div className="hrms-stat-card">
                        <div className="hrms-stat-top">
                            <span className="hrms-stat-icon">✓</span>

                            <span className="hrms-status">
                                Attendance
                            </span>
                        </div>

                        <div className="hrms-stat-label">
                            Today's status
                        </div>

                        <div className="hrms-stat-value">
                            {attendanceStatus}
                        </div>

                        <div className="hrms-stat-note">
                            Daily check-in and check-out
                        </div>
                    </div>

                    <div className="hrms-stat-card">
                        <div className="hrms-stat-top">
                            <span className="hrms-stat-icon">◷</span>
                        </div>

                        <div className="hrms-stat-label">
                            Completed attendance days
                        </div>

                        <div className="hrms-stat-value">
                            {completedAttendance}
                        </div>

                        <div className="hrms-stat-note">
                            Records with check-out completed
                        </div>
                    </div>

                    <div className="hrms-stat-card">
                        <div className="hrms-stat-top">
                            <span className="hrms-stat-icon">✓</span>

                            <span className="hrms-status hrms-status-pending">
                                Placeholder
                            </span>
                        </div>

                        <div className="hrms-stat-label">
                            Today's tasks
                        </div>

                        <div className="hrms-stat-value">—</div>

                        <div className="hrms-stat-note">
                            No task integration yet
                        </div>
                    </div>

                    <div className="hrms-stat-card">
                        <div className="hrms-stat-top">
                            <span className="hrms-stat-icon">◷</span>

                            <span className="hrms-status hrms-status-pending">
                                Placeholder
                            </span>
                        </div>

                        <div className="hrms-stat-label">
                            Upcoming meetings
                        </div>

                        <div className="hrms-stat-value">—</div>

                        <div className="hrms-stat-note">
                            No meeting schedule connected yet
                        </div>
                    </div>

                </section>

                {/* Main dashboard content */}
                <section className="hrms-content-grid">

                    {/* Attendance panel */}
                    <div className="hrms-section-card">

                        <div className="hrms-section-header">
                            <div>
                                <h2>Daily attendance</h2>

                                <p>
                                    {formatDate(today)}
                                    {' · Employee ID '}
                                    {employee.emp_id}
                                </p>
                            </div>

                            <span className="hrms-status">
                                Time tracking
                            </span>
                        </div>

                        <div className="hrms-section-body">

                            <p className="hrms-muted">
                                Record your start and end times for today.
                            </p>

                            <div className="row g-3">

                                <div className="col-md-6">
                                    <div className="hrms-attendance-time">
                                        <span className="hrms-muted">
                                            Check-in time
                                        </span>

                                        <strong>
                                            {todayAttendance?.inTime
                                                ? formatTime(todayAttendance.inTime)
                                                : '--:--'}
                                        </strong>

                                        <span className="hrms-muted">
                                            {todayAttendance?.inTime
                                                ? formatDate(todayAttendance.attendancedate)
                                                : 'Not checked in'}
                                        </span>
                                    </div>
                                </div>

                                <div className="col-md-6">
                                    <div className="hrms-attendance-time">
                                        <span className="hrms-muted">
                                            Check-out time
                                        </span>

                                        <strong>
                                            {todayAttendance?.outTime
                                                ? formatTime(todayAttendance.outTime)
                                                : '--:--'}
                                        </strong>

                                        <span className="hrms-muted">
                                            {todayAttendance?.outTime
                                                ? formatDate(todayAttendance.attendancedate)
                                                : 'Not checked out'}
                                        </span>
                                    </div>
                                </div>

                            </div>

                            {!attendanceLoaded ? (
                                <button
                                    type="button"
                                    className="hrms-button hrms-button-light hrms-button-block"
                                    disabled
                                >
                                    Loading attendance...
                                </button>
                            ) : !todayAttendance ? (
                                <button
                                    type="button"
                                    className="hrms-button hrms-button-success hrms-button-block"
                                    onClick={markAttendance}
                                    disabled={loading}
                                >
                                    {loading
                                        ? 'Saving...'
                                        : '✓ Mark Attendance IN'}
                                </button>
                            ) : !todayAttendance.outTime ? (
                                <button
                                    type="button"
                                    className="hrms-button hrms-button-warning hrms-button-block"
                                    onClick={markMeOut}
                                    disabled={loading}
                                >
                                    {loading
                                        ? 'Saving...'
                                        : 'Mark Me OUT'}
                                </button>
                            ) : (
                                <button
                                    type="button"
                                    className="hrms-button hrms-button-light hrms-button-block"
                                    disabled
                                >
                                    ✓ Attendance completed for today
                                </button>
                            )}

                            {message && (
                                <div
                                    className="hrms-message"
                                    role="status"
                                >
                                    {message}
                                </div>
                            )}

                            <div className="d-flex justify-content-between align-items-center gap-3 flex-wrap mt-4">
                                <span className="hrms-muted">
                                    Review your previous attendance
                                </span>

                                <button
                                    type="button"
                                    className="hrms-button hrms-button-primary"
                                    onClick={goToDashboard}
                                >
                                    Open Dashboard →
                                </button>
                            </div>

                        </div>
                    </div>

                    {/* Tasks and meetings */}
                    <div>

                        <div className="hrms-section-card">
                            <div className="hrms-section-header">
                                <div>
                                    <h2>Today's tasks</h2>
                                    <p>Your daily priorities</p>
                                </div>
                            </div>

                            <div className="hrms-section-body">
                                <div className="hrms-empty-state">
                                    <div className="hrms-empty-icon">✓</div>

                                    <h3>No tasks assigned yet</h3>

                                    <p>
                                        Assigned tasks will appear here
                                        when task management is connected.
                                    </p>
                                </div>
                            </div>
                        </div>

                        <div className="hrms-section-card">
                            <div className="hrms-section-header">
                                <div>
                                    <h2>Upcoming meetings</h2>
                                    <p>Your schedule</p>
                                </div>
                            </div>

                            <div className="hrms-section-body">
                                <div className="hrms-empty-state">
                                    <div className="hrms-empty-icon">◷</div>

                                    <h3>No meetings scheduled</h3>

                                    <p>
                                        Meeting details will appear here
                                        when calendar integration is added.
                                    </p>
                                </div>
                            </div>
                        </div>

                    </div>

                </section>

                {/* Announcements */}
                <section className="hrms-section-card">

                    <div className="hrms-section-header">
                        <div>
                            <h2>Announcements & updates</h2>

                            <p>
                                Company notices and employee communications
                            </p>
                        </div>
                    </div>

                    <div className="hrms-section-body">
                        <div className="hrms-empty-state">
                            <div className="hrms-empty-icon">i</div>

                            <h3>No announcements available</h3>

                            <p>
                                Company announcements and HR updates
                                will appear here when this feature is connected.
                            </p>
                        </div>
                    </div>

                </section>

                {/* Employee profile summary */}
                <section className="hrms-section-card">

                    <div className="hrms-section-header">
                        <div>
                            <h2>My employee profile</h2>
                            <p>Your registered employee information</p>
                        </div>
                    </div>

                    <div className="hrms-section-body">
                        <div className="row g-4">

                            <div className="col-md-4">
                                <span className="hrms-muted">
                                    Employee name
                                </span>

                                <p className="fw-bold mt-2">
                                    {employee.emp_name}
                                </p>
                            </div>

                            <div className="col-md-4">
                                <span className="hrms-muted">
                                    Employee ID
                                </span>

                                <p className="fw-bold mt-2">
                                    {employee.emp_id}
                                </p>
                            </div>

                            <div className="col-md-4">
                                <span className="hrms-muted">
                                    Email address
                                </span>

                                <p className="fw-bold mt-2 text-break">
                                    {employee.email}
                                </p>
                            </div>

                            <div className="col-md-4">
                                <span className="hrms-muted">
                                    Designation
                                </span>

                                <p className="fw-bold mt-2">
                                    {employee.designation}
                                </p>
                            </div>

                            <div className="col-md-4">
                                <span className="hrms-muted">
                                    Technology stack
                                </span>

                                <p className="fw-bold mt-2">
                                    {employee.stack}
                                </p>
                            </div>

                        </div>
                    </div>

                </section>

            </main>

            <footer className="hrms-footer">
                HRMS Employee Workspace · Internal project demonstration
            </footer>

        </div>
    );
}

export default Home;
