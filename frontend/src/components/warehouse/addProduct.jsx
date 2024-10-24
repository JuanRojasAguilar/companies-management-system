import { useState, useEffect } from 'react';
import { Modal, Input, Button, Table, TableRow, TableBody, TableCell, TableColumn, TableHeader, Row, Col, Dropdown, DropdownItem, DropdownMenu, DropdownButton, DropdownTrigger } from '@nextui-org/react';
import axios from 'axios';

export default function CreateProduct() {
  const [productId, setProductId] = useState('');
  const [productName, setProductName] = useState('');
  const [services, setServices] = useState([]);
  const [serviceRows, setServiceRows] = useState([]);
  const [servicesAPI, setServicesAPI] = useState([]);

  useEffect(() => {
    // Fetch available services from the API
    axios.get('/api/services') // Replace with your actual API endpoint
      .then(response => setServicesAPI(response.data))
      .catch(error => console.error('Error fetching services:', error));
  }, []);

  // Add a new service row
  const addServiceRow = () => {
    setServiceRows([{ serviceId: '', value: '', currStock: '', minStock: '', maxStock: '' }]);
  };

  // Handle change for service row fields
  const handleRowChange = (index, field, value) => {
    const updatedRows = serviceRows.map((row, i) =>
      i === index ? { ...row, [field]: value } : row
    );
    setServiceRows(updatedRows);
  };

  // Handle form submission
  const handleSubmit = async () => {
    const productData = {
      id: productId,
      name: productName,
      services: serviceRows,
    };

    try {
      const response = await axios.post('/api/products', productData); // Adjust endpoint
      console.log('Product created successfully:', response.data);
      // Optionally reset form
      setProductId('');
      setProductName('');
      setServiceRows([]);
    } catch (error) {
      console.error('Error creating product:', error);
    }
  };

  return (
    <div className="container">
      <h1>Create Product</h1>

      {/* Product ID and Name Inputs */}
      <Input
        label="Product ID"
        clearable
        fullWidth
        placeholder="Enter Product ID"
        value={productId}
        onChange={(e) => setProductId(e.target.value)}
        css={{ marginBottom: '20px' }}
      />
      <Input
        label="Product Name"
        clearable
        fullWidth
        placeholder="Enter Product Name"
        value={productName}
        onChange={(e) => setProductName(e.target.value)}
        css={{ marginBottom: '20px' }}
      />

      {/* Services Section */}
      <h3>Services</h3>
      <Button auto onClick={addServiceRow}>
        Add Service
      </Button>

      {/* Table for dynamically added service rows */}
      <Table aria-label="Service Table" css={{ marginTop: '20px' }}>
        <TableHeader>
          <TableColumn>Service</TableColumn>
          <TableColumn>Value</TableColumn>
          <TableColumn>Current Stock</TableColumn>
          <TableColumn>Min Stock</TableColumn>
          <TableColumn>Max Stock</TableColumn>
        </TableHeader>
        <TableBody>
          {serviceRows.map((row, index) => (
            <TableRow key={index}>
              <TableCell>
                <Dropdown>
                  <DropdownTrigger flat>{row.serviceId || 'Select Service'}</DropdownTrigger>
                  <DropdownMenu
                    aria-label="Services"
                    selectionMode="single"
                    selectedKeys={row.serviceId}
                    onSelectionChange={(value) => handleRowChange(index, 'serviceId', value)}
                  >
                    {servicesAPI.map((service) => (
                      <DropdownItem key={service.id} value={service.id}>
                        {service.name}
                      </DropdownItem>
                    ))}
                  </DropdownMenu>
                </Dropdown>
              </TableCell>
              <TableCell>
                <Input
                  type="number"
                  value={row.value}
                  onChange={(e) => handleRowChange(index, 'value', e.target.value)}
                  placeholder="Enter Value"
                />
              </TableCell>
              <TableCell>
                <Input
                  type="number"
                  value={row.currStock}
                  onChange={(e) => handleRowChange(index, 'currStock', e.target.value)}
                  placeholder="Current Stock"
                />
              </TableCell>
              <TableCell>
                <Input
                  type="number"
                  value={row.minStock}
                  onChange={(e) => handleRowChange(index, 'minStock', e.target.value)}
                  placeholder="Min Stock"
                />
              </TableCell>
              <TableCell>
                <Input
                  type="number"
                  value={row.maxStock}
                  onChange={(e) => handleRowChange(index, 'maxStock', e.target.value)}
                  placeholder="Max Stock"
                />
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>

      {/* Submit Button */}
      <Button auto onClick={handleSubmit} css={{ marginTop: '20px' }}>
        Submit
      </Button>
    </div>
  );
}
