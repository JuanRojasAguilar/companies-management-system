"use client";

import EmployeeCard from "./EmployeeCard";

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
    return (
        <div className="px-8 flex flex-col items-center">
            <h1 className="mb-12 text-6xl">Empleados</h1>
            <div className="w-1/2 flex flex-wrap gap-4">
                {employees.map(({ name, position }) => (
                    <EmployeeCard key={name} name={name} position={position} />
                ))}
            </div>
        </div>
    );
}

export default EmployeeList;