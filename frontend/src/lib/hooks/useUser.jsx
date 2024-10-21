"use client";

import { useEffect } from "react";

const getEmployees = () => {
    const info = fetch("http://localhost:8080/api/users/employees")
        .then(data => data.json());
    return info;
};



export {getEmployees};