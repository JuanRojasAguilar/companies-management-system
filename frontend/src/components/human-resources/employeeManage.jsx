import { useEffect, useState } from 'react';
import {
  Table, TableHeader, TableColumn, TableBody, TableRow, TableCell,
  Input, Button, Dropdown
} from '@nextui-org/react';
import axios from 'axios';

export default function EmployeeManagement() {
  const [employees, setEmployees] = useState([]);
  const [branches, setBranches] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const employeesData = await axios.get('/api/employees');
        const branchesData = await axios.get('/api/branches');

        setEmployees(employeesData.data);
        setBranches(branchesData.data);
      } catch (error) {
        console.error('Error fetching employees data:', error);
      }
    };
    fetchData();
  }, []);

  return (
    <div className="container">
      <h1>Employee Management</h1>
      <Button>Add New Employee</Button>

      <Table aria-label="Employee Table" css={{ marginTop: '20px' }}>
        <TableHeader>
          <TableColumn>ID</TableColumn>
          <TableColumn>Name</TableColumn>
          <TableColumn>Branch</TableColumn>
          <TableColumn>Role</TableColumn>
        </TableHeader>
        <TableBody>
          {employees.map((employee) => (
            <TableRow key={employee.id}>
              <TableCell>{employee.id}</TableCell>
              <TableCell>{employee.name}</TableCell>
              <TableCell>{employee.branch}</TableCell>
              <TableCell>{employee.role}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </div>
  );
}
