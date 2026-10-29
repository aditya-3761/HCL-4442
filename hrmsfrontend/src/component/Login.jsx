
import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';

function Login() {
    const navigate = useNavigate();

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [message, setMessage] = useState('');
    const [loading, setLoading] = useState(false);

    async function handleLogin(event) {
        event.preventDefault();
        setLoading(true);
        setMessage('');

        try {
            const loginResponse = await fetch(
                'http://localhost:8080/v1/user/login?email=' +
                encodeURIComponent(email) +
                '&password=' +
                encodeURIComponent(password),
                {
                    method: 'POST'
                }
            );

            const loginResult = await loginResponse.text();

            if (
                !loginResponse.ok ||
                !loginResult.toLowerCase().includes('login successful')
            ) {
                setMessage(loginResult || 'Invalid email or password.');
                return;
            }

            const employeeResponse = await fetch(
                'http://localhost:8080/v1/user/byEmail?email=' +
                encodeURIComponent(email)
            );

            if (!employeeResponse.ok) {
                setMessage(
                    'Login succeeded, but employee details could not be loaded.'
                );
                return;
            }

            const employeeData = await employeeResponse.json();

            if (!employeeData || !employeeData.emp_id) {
                setMessage('Employee details were not found.');
                return;
            }

            // Never keep the password in browser storage.
            const { password: ignoredPassword, ...safeEmployee } = employeeData;

            sessionStorage.setItem(
                'hrmsEmployee',
                JSON.stringify(safeEmployee)
            );

            navigate('/home', {
                replace: true,
                state: { employee: safeEmployee }
            });
        } catch (error) {
            console.error('Login error:', error);
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

                    <div style={{ marginTop: 75 }}>
                        <span className="hrms-eyebrow">YOUR WORKSPACE</span>
                        <h1>One place for your workday.</h1>
                        <p>
                            Access employee information, manage your attendance,
                            and stay informed about tasks and workplace activities.
                        </p>
                    </div>
                </div>

                <div className="hrms-auth-aside-footer">
                    Employee Management System · Secure workspace
                </div>
            </aside>

            <main className="hrms-auth-main">
                <div className="hrms-auth-form">
                    <span className="hrms-eyebrow">WELCOME BACK</span>
                    <h2>Sign in to HRMS</h2>
                    <p className="hrms-auth-intro">
                        Enter your registered email address and password to continue.
                    </p>

                    <form onSubmit={handleLogin}>
                        <div className="hrms-form-group">
                            <label className="hrms-form-label" htmlFor="loginEmail">
                                Email address
                            </label>
                            <input
                                id="loginEmail"
                                className="hrms-input"
                                type="email"
                                value={email}
                                onChange={(event) => setEmail(event.target.value)}
                                placeholder="name@example.com"
                                autoComplete="username"
                                required
                            />
                        </div>

                        <div className="hrms-form-group">
                            <label className="hrms-form-label" htmlFor="loginPassword">
                                Password
                            </label>
                            <input
                                id="loginPassword"
                                className="hrms-input"
                                type="password"
                                value={password}
                                onChange={(event) => setPassword(event.target.value)}
                                placeholder="Enter your password"
                                autoComplete="current-password"
                                required
                            />
                        </div>

                        <button
                            type="submit"
                            className="hrms-button hrms-button-primary hrms-button-block"
                            disabled={loading}
                        >
                            {loading ? 'Signing in...' : 'Sign In'}
                        </button>
                    </form>

                    {message && (
                        <div className="hrms-message" role="alert">
                            {message}
                        </div>
                    )}

                    <div className="hrms-form-footer">
                        Don't have an account?{' '}
                        <Link to="/register">Register here</Link>
                    </div>
                </div>
            </main>
        </div>
    );
}

export default Login;
