import { useEffect, useState } from 'react';
import {
  Card, Table, TableHeader, TableColumn, TableBody, TableRow, TableCell,
  Row, Col, Text, Button
} from '@nextui-org/react';
import axios from 'axios';

export default function UserProfile() {
  const [userData, setUserData] = useState({});
  const [userHistory, setUserHistory] = useState([]);

  useEffect(() => {
    const fetchUserData = async () => {
      try {
        const userResponse = await axios.get('/api/user/profile');
        const historyResponse = await axios.get('/api/user/history');

        setUserData(userResponse.data);
        setUserHistory(historyResponse.data);
      } catch (error) {
        console.error('Error fetching user profile data:', error);
      }
    };
    fetchUserData();
  }, []);

  return (
    <div className="container">
      <h1>User Profile</h1>

      <Card css={{ marginBottom: '20px' }}>
        <Text h3>{userData.name}</Text>
        <Text>Email: {userData.email}</Text>
        <Text>Role: {userData.role}</Text>
        <Button>Edit Profile</Button>
      </Card>

      <h3>Order & Service History</h3>
      <Table aria-label="User History Table" css={{ marginTop: '20px' }}>
        <TableHeader>
          <TableColumn>Order ID</TableColumn>
          <TableColumn>Status</TableColumn>
          <TableColumn>Assigned Date</TableColumn>
        </TableHeader>
        <TableBody>
          {userHistory.map((history) => (
            <TableRow key={history.orderId}>
              <TableCell>{history.orderId}</TableCell>
              <TableCell>{history.status}</TableCell>
              <TableCell>{history.assignedDate}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </div>
  );
}

