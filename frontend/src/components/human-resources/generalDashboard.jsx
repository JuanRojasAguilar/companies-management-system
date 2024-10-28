'use client'
import { useEffect, useState } from 'react'
import { 
  Card, 
  CardBody,
  CardHeader,
  Table,
  TableHeader,
  TableColumn,
  TableBody,
  TableRow,
  TableCell,
  Divider 
} from '@nextui-org/react'

export default function Dashboard() {
  const [orders, setOrders] = useState([])
  const [unassignedServices, setUnassignedServices] = useState([])
  const [branchStats, setBranchStats] = useState([])
  const [employeeStats, setEmployeeStats] = useState([])

  useEffect(() => {
    const fetchData = async () => {
      try {
        const [ordersData, servicesData, branchData, employeeData] = await Promise.all([
          fetch('/api/orders').then(res => res.json()),
          fetch('/api/services/unassigned').then(res => res.json()),
          fetch('/api/stats/branches').then(res => res.json()),
          fetch('/api/stats/employees').then(res => res.json())
        ])

        setOrders(ordersData)
        setUnassignedServices(servicesData)
        setBranchStats(branchData)
        setEmployeeStats(employeeData)
      } catch (error) {
        console.error('Error fetching dashboard data:', error)
      }
    }

    fetchData()
  }, [])

  return (
    <div className="p-6 gap-6">
      <h1 className="text-2xl font-bold mb-6">General Dashboard</h1>
      
      <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
        <Card>
          <CardHeader className="flex gap-3">
            <div className="flex flex-col">
              <p className="text-md">Order Summary</p>
            </div>
          </CardHeader>
          <Divider/>
          <CardBody>
            <div className="space-y-2">
              <div className="flex justify-between">
                <span>Total Orders:</span>
                <span>{orders.length}</span>
              </div>
              <div className="flex justify-between">
                <span>Pending:</span>
                <span>{orders.filter(o => o.status === 'Pending').length}</span>
              </div>
              <div className="flex justify-between">
                <span>Completed:</span>
                <span>{orders.filter(o => o.status === 'Completed').length}</span>
              </div>
            </div>
          </CardBody>
        </Card>

        <Card>
          <CardHeader className="flex gap-3">
            <div className="flex flex-col">
              <p className="text-md">Unassigned Services</p>
            </div>
          </CardHeader>
          <Divider/>
          <CardBody>
            <Table aria-label="Unassigned Services">
              <TableHeader>
                <TableColumn>Service</TableColumn>
                <TableColumn>Status</TableColumn>
              </TableHeader>
              <TableBody>
                {unassignedServices.map((service) => (
                  <TableRow key={service.id}>
                    <TableCell>{service.name}</TableCell>
                    <TableCell>Unassigned</TableCell>
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