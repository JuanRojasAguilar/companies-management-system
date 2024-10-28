import { useState, useEffect } from 'react';
import {
  Table, TableHeader, TableColumn, TableBody, TableRow, TableCell,
  Input, Button, Dropdown
} from '@nextui-org/react';
import axios from 'axios';

export default function OrderServiceManagement() {
  const [orders, setOrders] = useState([]);
  const [filter, setFilter] = useState({ status: '', branch: '', assignedTo: '' });

  useEffect(() => {
    const fetchOrders = async () => {
      try {
        const response = await axios.get('/api/orders');
        setOrders(response.data);
      } catch (error) {
        console.error('Error fetching orders:', error);
      }
    };
    fetchOrders();
  }, []);

  return (
    <div className="container">
      <h1>Order and Service Management</h1>

      {/* Orders Table */}
      <Table aria-label="Order Table" css={{ marginTop: '20px' }}>
        <TableHeader>
          <TableColumn>ID</TableColumn>
          <TableColumn>Status</TableColumn>
          <TableColumn>Branch</TableColumn>
          <TableColumn>Assigned To</TableColumn>
        </TableHeader>
        <TableBody>
          {orders.map((order) => (
            <TableRow key={order.id}>
              <TableCell>{order.id}</TableCell>
              <TableCell>{order.status}</TableCell>
              <TableCell>{order.branch}</TableCell>
              <TableCell>{order.assignedTo}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </div>
  );
}

