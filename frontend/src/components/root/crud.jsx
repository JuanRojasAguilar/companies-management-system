import { useState, useEffect } from 'react';
import axios from 'axios';
import { Modal, Input, Button, Table, Row, ModalBody, ModalFooter, ModalHeader, TableRow, TableBody, TableCell, TableColumn, TableHeader } from '@nextui-org/react';

export default function CRUDPage({ entity }) {
  const [data, setData] = useState([]);
  const [filteredData, setFilteredData] = useState([]);
  const [selectedItem, setSelectedItem] = useState(null);
  const [showEditModal, setShowEditModal] = useState(false);
  const [showDeleteModal, setShowDeleteModal] = useState(false);
  const [search, setSearch] = useState("");

  const differentEndpoints = [
    ["order/details", "order-details"],
    ["work/orders/details", "work-orders-details"],
    ["emails/types", "emails-types"],
    ["users/emails", "users-emails"],
    ["orders/services", "orders-services"],
    ["orders/states", "orders-states"],
    ["works/orders", "works-orders"],
    ["services/approvals", "services-approvals"],
    ["service-reagents", "service-reagents"],
    ["telephones/types", "telephones-types"],
    ["user-reagents", "user-reagents"],
    ["users/types", "users-types"],
    ["users/telephones", "users-telephones"]
  ];

  const convertEndpoint = () => {
      const match = differentEndpoints.find(([original, modified]) => modified == entity);
      return( match ? match[0] : entity);
  };

  // Fetch data on mount
  useEffect(() => {
	const fixedEndpoint = convertEndpoint();
    if (fixedEndpoint) {
      axios.get(`/api/${fixedEndpoint}`)
        .then(response => {
          console.log(response.data); // Log response for debugging
          setData(response.data);
          setFilteredData(response.data);
        })
        .catch(error => console.error("Error fetching data:", error));
    }
  }, [entity]);

  // Filter data by search
  useEffect(() => {
    setFilteredData(
      data.filter(item =>
        item.name?.toLowerCase().includes(search.toLowerCase()) // Use optional chaining
      )
    );
  }, [search, data]);

  // Handle selecting an item
  const handleSelectItem = (item) => {
    setSelectedItem(item);
    setShowEditModal(true);
  };

  // Handle edit submission
  const handleEditSubmit = () => {
    if (!selectedItem) return; // Check if selectedItem is defined
    axios.put(`/api/${entity}/${selectedItem.id}`, selectedItem)
      .then(response => {
        setData(prevData =>
          prevData.map(item =>
            item.id === selectedItem.id ? selectedItem : item
          )
        );
        setShowEditModal(false);
      })
      .catch(error => console.error("Error updating item:", error));
  };

  // Handle delete confirmation
  const handleDeleteConfirm = () => {
    if (!selectedItem) return; // Check if selectedItem is defined
    axios.delete(`/api/${entity}/${selectedItem.id}`)
      .then(response => {
        setData(prevData => prevData.filter(item => item.id !== selectedItem.id));
        setShowDeleteModal(false);
      })
      .catch(error => console.error("Error deleting item:", error));
  };

  return (
    <div className="container">
      <h1>{entity} CRUD Table</h1>
      
      <Input 
        type="text"
        clearable
        underlined
        placeholder={`Search by name in ${entity}...`} 
        value={search}
        onChange={e => setSearch(e.target.value)}
        css={{ marginBottom: '20px', width: '250px' }}
      />

      <Table aria-label={`${entity} Table`}>
        <TableHeader>
          <TableColumn>Name</TableColumn>
          <TableColumn>Description</TableColumn>
          <TableColumn>Action</TableColumn>
        </TableHeader>
        <TableBody>
          {filteredData.map(item => (
            <TableRow key={item.id}>
              <TableCell>{item.name}</TableCell>
              <TableCell>{item.description}</TableCell>
              <TableCell>
                <Button auto onClick={() => handleSelectItem(item)}>Select</Button>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>

      {/* Edit Modal */}
      <Modal
        closeButton
        isOpen={showEditModal}
        onClose={() => setShowEditModal(false)}
      >
        <ModalHeader>
          <h2>Edit {entity}</h2>
        </ModalHeader>
        <ModalBody>
          <Input
            label="Name"
            clearable
            fullWidth
            value={selectedItem?.name || ''}
            onChange={e => setSelectedItem({ ...selectedItem, name: e.target.value })}
          />
          <Input
            label="Description"
            clearable
            fullWidth
            value={selectedItem?.description || ''}
            onChange={e => setSelectedItem({ ...selectedItem, description: e.target.value })}
          />
        </ModalBody>
        <ModalFooter>
          <Button auto onClick={handleEditSubmit}>
            Save Changes
          </Button>
          <Button auto flat color="error" onClick={() => setShowEditModal(false)}>
            Cancel
          </Button>
        </ModalFooter>
      </Modal>

      {/* Delete Confirmation Modal */}
      <Modal
        closeButton
        isOpen={showDeleteModal}
        onClose={() => setShowDeleteModal(false)}
      >
        <ModalHeader>
          <h2>Confirm Deletion</h2>
        </ModalHeader>
        <ModalBody>
          <p>Are you sure you want to delete this {entity}?</p>
        </ModalBody>
        <ModalFooter>
          <Button auto flat color="error" onClick={handleDeleteConfirm}>
            Confirm
          </Button>
          <Button auto flat onClick={() => setShowDeleteModal(false)}>
            Cancel
          </Button>
        </ModalFooter>
      </Modal>

      {/* If an item is selected, show Edit/Delete buttons */}
      {selectedItem && (
        <Row justify="center" css={{ marginTop: '20px' }}>
          <Button auto onClick={() => setShowEditModal(true)}>Edit</Button>
          <Button auto flat color="error" onClick={() => setShowDeleteModal(true)}>Delete</Button>
        </Row>
      )}
    </div>
  );
}

