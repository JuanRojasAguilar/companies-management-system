import { useState, useEffect } from 'react';
import { Modal, Input, Button, Table, TableRow, TableBody, TableCell, TableColumn, TableHeader, Dropdown, DropdownItem, DropdownMenu, DropdownTrigger } from '@nextui-org/react';
import axios from 'axios';

export default function CreateProduct() {
  const [productId, setProductId] = useState('');
  const [productName, setProductName] = useState('');
  const [servicesAPI, setServicesAPI] = useState([]);
  const [serviceRows, setServiceRows] = useState([]);
  const [rowCounter, setRowCounter] = useState(0); // Counter to generate unique IDs

  useEffect(() => {
    axios.get('/api/services') // Replace with your actual API endpoint
      .then(response => setServicesAPI(response.data))
      .catch(error => console.error('Error fetching services:', error));
  }, []);

  const addServiceRow = () => {
    const newRow = {
      id: rowCounter, // Assign unique ID based on the counter
      serviceId: '',
      value: '',
      currStock: '',
      minStock: '',
      maxStock: ''
    };
    setServiceRows([...serviceRows, newRow]);
    setRowCounter(rowCounter + 1); // Increment counter for next row
  };

  const deleteService = (id) => {
    const updatedRows = serviceRows.filter(row => row.id !== id);
    setServiceRows(updatedRows);
  };

  const handleRowChange = (id, field, value) => {
    const updatedRows = serviceRows.map((row) =>
      row.id === id ? { ...row, [field]: value } : row
    );
    setServiceRows(updatedRows);
  };

  const handleSubmit = async () => {
    const productData = {
      id: productId,
      name: productName,
      services: serviceRows,
    };

    try {
      const response = await axios.post('/api/products', productData);
      console.log('Product created successfully:', response.data);
      setProductId('');
      setProductName('');
      setServiceRows([]);
      setRowCounter(0); // Reset counter
    } catch (error) {
      console.error('Error creating product:', error);
    }
  };

  return (
    <div className="space-y-4 h-full w-full p-8 pr-16">
		  <h1 className="text-lg text-center">Product Creation</h1>
      <h1>Create Product</h1>
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

      <h3>Services</h3>
      <Button auto onClick={addServiceRow}>
        Add Service
      </Button>

      <Table aria-label="Service Table" css={{ marginTop: '20px' }}>
        <TableHeader>
          <TableColumn>Service</TableColumn>
          <TableColumn>Value</TableColumn>
          <TableColumn>Current Stock</TableColumn>
          <TableColumn>Min Stock</TableColumn>
          <TableColumn>Max Stock</TableColumn>
          <TableColumn>Delete</TableColumn>
        </TableHeader>
        <TableBody>
          {serviceRows.map((row) => (
            <TableRow key={row.id}>
              <TableCell>
                <Dropdown>
                  <DropdownTrigger flat>{row.serviceId || 'Select Service'}</DropdownTrigger>
                  <DropdownMenu
                    aria-label="Services"
                    selectionMode="single"
                    selectedKeys={row.serviceId}
                    onSelectionChange={(value) => handleRowChange(row.id, 'serviceId', value)}
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
                  onChange={(e) => handleRowChange(row.id, 'value', e.target.value)}
                  placeholder="Enter Value"
                />
              </TableCell>
              <TableCell>
                <Input
                  type="number"
                  value={row.currStock}
                  onChange={(e) => handleRowChange(row.id, 'currStock', e.target.value)}
                  placeholder="Current Stock"
                />
              </TableCell>
              <TableCell>
                <Input
                  type="number"
                  value={row.minStock}
                  onChange={(e) => handleRowChange(row.id, 'minStock', e.target.value)}
                  placeholder="Min Stock"
                />
              </TableCell>
              <TableCell>
                <Input
                  type="number"
                  value={row.maxStock}
                  onChange={(e) => handleRowChange(row.id, 'maxStock', e.target.value)}
                  placeholder="Max Stock"
                />
              </TableCell>
              <TableCell>
                <Button color="danger" variant="bordered" onClick={() => deleteService(row.id)}>
				  <i class='bx bxs-trash'></i> 
                </Button>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>

      <Button auto onClick={handleSubmit} css={{ marginTop: '20px' }}>
        Submit
      </Button>
    </div>
  );
}
