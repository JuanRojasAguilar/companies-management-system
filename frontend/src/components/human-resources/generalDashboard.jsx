import { useEffect, useState } from 'react';
import {
  Card, Table, TableHeader, TableColumn, TableBody, TableRow, TableCell,
  Row, Col, Text, Statistic
} from '@nextui-org/react';
import axios from 'axios';
import { withOptions } from 'tailwindcss/plugin';

export default function Dashboard() {
  const [orders, setOrders] = useState([]);
  const [unassignedServices, setUnassignedServices] = useState([]);
  const [branchStats, setBranchStats] = useState([]);
  const [employeeStats, setEmployeeStats] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const ordersData = await axios.get('/api/orders');
        const servicesData = await axios.get('/api/services/unassigned');
        const branchData = await axios.get('/api/stats/branches');
        const employeeData = await axios.get('/api/stats/employees');

        setOrders(ordersData.data);
        setUnassignedServices(servicesData.data);
        setBranchStats(branchData.data);
        setEmployeeStats(employeeData.data);
      } catch (error) {
        console.error('Error fetching dashboard data:', error);
      }
    };
    fetchData();
  }, []);

  return (
    <div className="container">
      <h1>General Dashboard</h1>
      <Row>
        <Col span={6}>
          <Card>
            <h1>Order Summary</h1>
            <Statistic value={orders.length} />
            <Statistic value={orders.filter(o => o.status === 'Pending').length} />
            <Statistic value={orders.filter(o => o.status === 'Completed').length} />
          </Card>
        </Col>
        <Col span={6}>
          <Card>
            <h1>Unassigned Services</h1>
            <Table aria-label="Unassigned Services" css={{ marginTop: '20px' }}>
              <TableHeader>
                <TableColumn>Service</TableColumn>
              </TableHeader>
              <TableBody>
                {unassignedServices.map((service) => (
                  <TableRow key={service.id}>
                    <TableCell>{service.name}</TableCell>
                  </TableRow>
                ))}
              </TableBody>
            </Table>
          </Card>
        </Col>
      </Row>
    </div>
  );
}
