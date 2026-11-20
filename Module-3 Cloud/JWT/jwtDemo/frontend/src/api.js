import axios from "axios";

const apiURL= import.meta.env.VITE_API_URL || "http://localhost:8080/api/v1";
const api = axios.create({baseURL: apiURL,})

api.interceptors.request.use((config) => {
    const token = localStorage.getItem("token");
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
});

export default api;