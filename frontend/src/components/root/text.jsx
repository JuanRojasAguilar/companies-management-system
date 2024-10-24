import { useState, useEffect } from 'react';
import axios from 'axios';
import { Modal, Input, Button, Table, Row, Col } from '@nextui-org/react';

export default function CRUDPage() {
  const [data, setData] = useState([]);
  const [filteredData, setFilteredData] = useState([]);
  const [selectedItem, setSelectedItem] = useState(null);
  const [showEditModal, setShowEditModal] = useState(false);
  const [showDeleteModal, setShowDeleteModal] = useState(false);
  const [search, setSearch] = useState("");

  // Fetch data on mount
  useEffect(() => {
    axios.get('/api/items') // Replace with your actual API endpoint
      .then(response => {
        setData(response.data);
        setFilteredData(response.data);
      })
      .catch(error => console.error("Error fetching data:", error));
  }, []);

  // Filter data by search
  useEffect(() => {
    setFilteredData(
      data.filter(item => 
        item.name.toLowerCase().includes(search.toLowerCase())
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
    axios.put(`/api/items/${selectedItem.id}`, selectedItem)
      .then(response => {
        // Update the data state with the edited item
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
    axios.delete(`/api/items/${selectedItem.id}`)
      .then(response => {
        // Remove the item from the data state
        setData(prevData => prevData.filter(item => item.id !== selectedItem.id));
        setShowDeleteModal(false);
      })
      .catch(error => console.error("Error deleting item:", error));
  };

  return (
    <div className="container">
      <h1>CRUD Table</h1>
      
      <Input 
        type="text"
        clearable
        underlined
        placeholder="Search by name..." 
        value={search}
        onChange={e => setSearch(e.target.value)}
        css={{ marginBottom: '20px', width: '250px' }}
      />

      <Table aria-label="Example CRUD Table">
        <Table.Header>
          <Table.Column>Name</Table.Column>
          <Table.Column>Description</Table.Column>
          <Table.Column>Action</Table.Column>
        </Table.Header>
        <Table.Body>
          {filteredData.map(item => (
            <Table.Row key={item.id}>
              <Table.Cell>{item.name}</Table.Cell>
              <Table.Cell>{item.description}</Table.Cell>
              <Table.Cell>
                <Button auto onClick={() => handleSelectItem(item)}>Select</Button>
              </Table.Cell>
            </Table.Row>
          ))}
        </Table.Body>
      </Table>

      {/* Edit Modal */}
      <Modal
        closeButton
        isOpen={showEditModal}
        onClose={() => setShowEditModal(false)}
      >
        <Modal.Header>
          <h2>Edit Item</h2>
        </Modal.Header>
        <Modal.Body>
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
        </Modal.Body>
        <Modal.Footer>
          <Button auto onClick={handleEditSubmit}>
            Save Changes
          </Button>
          <Button auto flat color="error" onClick={() => setShowEditModal(false)}>
            Cancel
          </Button>
        </Modal.Footer>
      </Modal>

      {/* Delete Confirmation Modal */}
      <Modal
        closeButton
        isOpen={showDeleteModal}
        onClose={() => setShowDeleteModal(false)}
      >
        <Modal.Header>
          <h2>Confirm Deletion</h2>
        </Modal.Header>
        <Modal.Body>
          <p>Are you sure you want to delete this item?</p>
        </Modal.Body>
        <Modal.Footer>
          <Button auto flat color="error" onClick={handleDeleteConfirm}>
            Confirm
          </Button>
          <Button auto flat onClick={() => setShowDeleteModal(false)}>
            Cancel
          </Button>
        </Modal.Footer>
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
