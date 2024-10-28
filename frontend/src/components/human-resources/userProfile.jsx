'use client'
import { useEffect, useState } from 'react'
import {
  Card,
  CardHeader,
  CardBody,
  Table,
  TableHeader,
  TableColumn,
  TableBody,
  TableRow,
  TableCell,
  Button,
  Divider
} from '@nextui-org/react'
import axios from 'axios'

export default function UserProfile() {
  const [userData, setUserData] = useState({
    name: '',
    email: '',
    role: ''
  })
  const [userHistory, setUserHistory] = useState([])

  useEffect(() => {
    const fetchUserData = async () => {
      try {
        const [userResponse, historyResponse] = await Promise.all([
          axios.get('/api/user/profile'),
          axios.get('/api/user/history')
        ])
        
        setUserData(userResponse.data)
        setUserHistory(historyResponse.data)
      } catch (error) {
        console.error('Error fetching user profile data:', error)
      }
    }
    fetchUserData()
  }, [])

  return (
    <div className="max-w-7xl mx-auto p-6">
      <h1 className="text-2xl font-bold mb-6">User Profile</h1>
      
      <Card className="mb-6">
        <CardHeader className="flex-col items-start gap-2">
          <h3 className="text-xl font-bold">{userData.name}</h3>
        </CardHeader>
        <Divider/>
        <CardBody className="gap-2">
          <div className="flex flex-col gap-2">
            <p className="text-default-600">
              <span className="font-semibold">Email:</span> {userData.email}
            </p>
            <p className="text-default-600">
              <span className="font-semibold">Role:</span> {userData.role}
            </p>
            <div className="flex justify-end mt-4">
              <Button color="primary">
                Edit Profile
              </Button>
            </div>
          </div>
        </CardBody>
      </Card>

      <div className="mb-4">
        <h3 className="text-xl font-bold mb-4">Order & Service History</h3>
        <Card>
          <CardBody>
            <Table aria-label="User History Table">
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
          </CardBody>
        </Card>
      </div>
    </div>
  )
}