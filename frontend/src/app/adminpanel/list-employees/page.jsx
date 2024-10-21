"use client";

import { getEmployees } from "@/lib/hooks/useUser";

const AdminPage = () => {
    const employees = getEmployees();
    console.log(employees);
    return (
        <div>    
            hola mondo
        </div>
    );
}

export default AdminPage;