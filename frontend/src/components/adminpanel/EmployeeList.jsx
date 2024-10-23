"use client";

import EmployeeCard from "./EmployeeCard";
import AddEmployeeButton from "./AddEmployeeButton";
import { useState } from "react";

const employees = [
    {
        name: "Jhon Roa",
        position: "Backend Developer"
    },
    {
        name: "Juan Rojas",
        position: "Project Manager"
    },
    {
        name: "Alejandro Jimenez",
        position: "Frontend Developer"
    },
    {
        name: "Jholver Pardo",
        position: "CEO"
    }
];

const EmployeeList = () => {
    const [employeeList, setEmployeelist] = useState(employees);
    const addEmployee = (newEmployee) => {
        setEmployeelist((prevState) => [...prevState, newEmployee]);
    }
    return (
        <div className="px-8 flex flex-col items-center">
            <h1 className="mb-12 text-6xl">Empleados</h1>
            <div className="w-1/2 flex flex-wrap gap-4">
                {employeeList.map(({ name, position }) => (
                    <EmployeeCard key={name} name={name} position={position} />
                ))}
                <AddEmployeeButton addEmployee={addEmployee}/>
            </div>
        </div>
    );
}

export default EmployeeList;