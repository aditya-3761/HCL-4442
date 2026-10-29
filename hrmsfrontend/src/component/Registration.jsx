
import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';

function Registration() {
    const navigate = useNavigate();

    const [employee, setEmployee] = useState({
        emp_name: '',
        email: '',
        designation: '',
        stack: '',
        password: ''
    });

    const [message, setMessage] = useState('');
    const [loading, setLoading] = useState(false);

    function handleChange(event) {
        setEmployee({
            ...employee,
            [event.target.name]: event.target.value
        });
    }

    async function sendData(event) {
        event.preventDefault();
        setLoading(true);
        setMessage('');

        try {
            const response = await fetch(
                'http://localhost:8080/v1/user/addemp',
                {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(employee)
                }
            );

            const result = await response.text();

            if (!response.ok) {
                setMessage(result || 'Registration failed.');
                return;
            }

            if (result.toLowerCase().includes('success')) {
                setMessage('Registration successful. Redirecting to login...');

                setTimeout(() => {
                    navigate('/login', { replace: true });
                }, 900);
            } else {
                setMessage(result || 'Please check your details.');
            }
        } catch (error) {
            console.error('Registration error:', error);
            setMessage('Unable to connect to the server. Please try again.');
        } finally {
            setLoading(false);
        }
    }

    return (
        <div className="hrms-auth-page">
            <aside className="hrms-auth-aside">
                <div>
                    <div className="hrms-brand">
                        <div className="hrms-brand-mark">HR</div>
                        <div>
                            <h2 className="hrms-brand-name" style={{ color: 'white' }}>
                                HRMS Portal
                            </h2>
                            <span className="hrms-brand-caption" style={{ color: '#d2e0f8' }}>
                                Employee workspace
                            </span>
                        </div>
                    </div>

                    <div style={{ marginTop: 65 }}>
                        <span className="hrms-eyebrow">GET STARTED</span>
                        <h1>Build your employee profile.</h1>
                        <p>
                            Register to access your employee information,
                            daily attendance, tasks, and workplace updates
                            from one central workspace.
                        </p>
                    </div>
                </div>

                <div className="hrms-auth-aside-footer">
                    Employee Management System · Registration
                </div>
            </aside>

            <main className="hrms-auth-main">
                <div className="hrms-auth-form">
                    <span className="hrms-eyebrow">NEW EMPLOYEE</span>
                    <h2>Create your account</h2>
                    <p className="hrms-auth-intro">
                        Enter your details below to register with the HRMS portal.
                    </p>

                    <form onSubmit={sendData}>
                        <div className="hrms-form-group">
                            <label className="hrms-form-label" htmlFor="emp_name">
                                Full name
                            </label>
                            <input
                                id="emp_name"
                                className="hrms-input"
                                name="emp_name"
                                value={employee.emp_name}
                                onChange={handleChange}
                                placeholder="Enter your full name"
                                autoComplete="name"
                                required
                            />
                        </div>

                        <div className="hrms-form-group">
                            <label className="hrms-form-label" htmlFor="email">
                                Work email
                            </label>
                            <input
                                id="email"
                                className="hrms-input"
                                type="email"
                                name="email"
                                value={employee.email}
                                onChange={handleChange}
                                placeholder="name@example.com"
                                autoComplete="email"
                                required
                            />
                        </div>

                        <div className="hrms-form-group">
                            <label className="hrms-form-label" htmlFor="designation">
                                Designation
                            </label>
                            <input
                                id="designation"
                                className="hrms-input"
                                name="designation"
                                value={employee.designation}
                                onChange={handleChange}
                                placeholder="e.g. Software Developer"
                                required
                            />
                        </div>

                        <div className="hrms-form-group">
                            <label className="hrms-form-label" htmlFor="stack">
                                Technology stack
                            </label>
                            <input
                                id="stack"
                                className="hrms-input"
                                name="stack"
                                value={employee.stack}
                                onChange={handleChange}
                                placeholder="e.g. Java, Spring Boot, React"
                                required
                            />
                        </div>

                        <div className="hrms-form-group">
                            <label className="hrms-form-label" htmlFor="password">
                                Password
                            </label>
                            <input
                                id="password"
                                className="hrms-input"
                                type="password"
                                name="password"
                                value={employee.password}
                                onChange={handleChange}
                                placeholder="Create a password"
                                autoComplete="new-password"
                                required
                            />
                        </div>

                        <button
                            type="submit"
                            className="hrms-button hrms-button-primary hrms-button-block"
                            disabled={loading}
                        >
                            {loading ? 'Creating account...' : 'Create Account'}
                        </button>
                    </form>

                    {message && (
                        <div className="hrms-message" role="status">
                            {message}
                        </div>
                    )}

                    <div className="hrms-form-footer">
                        Already have an account?{' '}
                        <Link to="/login">Sign in</Link>
                    </div>
                </div>
            </main>
        </div>
    );
}

export default Registration;
