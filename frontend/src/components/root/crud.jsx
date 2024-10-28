import { useState, useEffect } from 'react';
import axios from 'axios';
import { Modal, Input, Button, Table, Row, ModalBody, ModalFooter, ModalHeader, TableRow, TableBody, TableCell, TableColumn, TableHeader, SelectItem, ModalContent } from '@nextui-org/react';

export default function CRUDPage({ entity }) {
  const [data, setData] = useState([]);
  const [filteredData, setFilteredData] = useState([]);
  const [selectedItem, setSelectedItem] = useState(null);
  const [newItem, setNewItem] = useState(null);
  const [showEditModal, setShowEditModal] = useState(false);
  const [showDeleteModal, setShowDeleteModal] = useState(false);
  const [showNewModal, setShowNewModal] = useState(false);
  const [search, setSearch] = useState("");
  const [columns, setColumns] = useState([]);
  const [loading, setLoading] = useState(false);

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
    const match = differentEndpoints.find(([original, modified]) => modified === entity);
    return match ? match[0] : entity;
  };
 
  useEffect(() => {
    const fixedEndpoint = convertEndpoint();
	if (fixedEndpoint) {
	  setLoading(true);
	  axios.get(`http://localhost:8080/api/${fixedEndpoint}`)
		.then(response => {
		  setData(response.data);
		  setFilteredData(response.data);
		  setColumns(Object.keys(response.data[0]));
		  console.log(response.data);
		})
		.catch(error => console.error("Error fetching data:", error))
		.finally(() => {
		  setLoading(false);
		});
	}
    setFilteredData(
      data.filter(item =>
        item.name?.toLowerCase().includes(search.toLowerCase())
      )
    );
  }, [entity]);

  const handleEditSubmit = () => {
    if (!selectedItem) return;
    axios.put(`http://localhost:8080/api/${entity}/${selectedItem.id}`, selectedItem)
      .then(() => {
        setData(prevData =>
          prevData.map(item =>
            item.id === selectedItem.id ? selectedItem : item
          )
        );
        setShowEditModal(false);
      })
      .catch(error => console.error("Error updating item:", error));
  };

  const handleDeleteConfirm = () => {
    if (!selectedItem) return;
    axios.delete(`http://localhost:8080/api/${entity}/${selectedItem.id}`)
      .then(() => {
        setData(prevData => prevData.filter(item => item.id !== selectedItem.id));
        setShowDeleteModal(false);
      })
      .catch(error => console.error("Error deleting item:", error));
  };

  const handleNewSubmit = () => {
    if (!newItem) return;
    axios.post(`http://localhost:8080/api/${entity}`, newItem)
      .then(() => {
        setData(prevData =>
          prevData.map(item =>
            item.id === newItem.id ? newItem : item
          )
        );
        setShowNewModal(false);
      })
      .catch(error => console.error("Error updating item:", error));
  };

  const handleOpenEditModal = (item) => {
    setSelectedItem(item);
    setShowEditModal(true);
	console.log(item, showEditModal);
  };

  const handleOpenDeleteModal = (item) => {
    setSelectedItem(item);
    setShowDeleteModal(true);
  };

  const handleOpenNewModal = () => {
	setShowNewModal(true);
  };

  return (
    <div className="space-y-4 h-full w-full p-8 pr-16">
      <h1 className="text-lg text-center">{entity} CRUD Table</h1>
	  
	  <div className="flex flex row">
      <Input 
        type="text"
        clearable
        underlined
        placeholder={`Search by name in ${entity}...`} 
        value={search}
        onChange={e => setSearch(e.target.value)}
        css={{ marginBottom: '20px', width: '250px' }}
      />
	  <Button className="ml-4" variant="ghost" color='success' onClick={() => handleOpenNewModal()}>
		<i class='bx bx-message-square-add' ></i>
	  </Button>
	  </div>

      {loading ? (
        <h1>Loading...</h1>
      ) : (
      <Table aria-label={`${entity} Table`}>
        <TableHeader>
          {columns.map((col, index) => (
            <TableColumn key={col} css={{ width: index === columns.length - 1 ? 'auto' : '1fr' }}>
              {col.charAt(0).toUpperCase() + col.slice(1)}
            </TableColumn>
          ))}
          <TableColumn css={{ width: '100px' }}>Actions</TableColumn>
        </TableHeader>
        <TableBody>
          {filteredData.map(item => (
            <TableRow key={item.id}>
              {columns.map(col => (
                <TableCell key={`${item.id}-${col}`}>{item[col]}</TableCell>
              ))}
              <TableCell>
                <Button auto variant="ghost" color="primary" className="mx-2" onClick={() => handleOpenEditModal(item)}>
				  <i class='bx bxs-edit'></i>
				</Button>
                <Button auto variant="ghost" color="danger" className="mx-2" onClick={() => handleOpenDeleteModal(item)}>
				  <i class='bx bx-trash'></i>
				</Button>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
      )}

      {/* Edit Modal */}
      <Modal
        closeButton
        isOpen={showEditModal}
        onClose={() => setShowEditModal(false)}
      >
	  <ModalContent>
	  {(onClose) => (
		<>
        <ModalHeader>
          <h2>Edit {entity}</h2>
        </ModalHeader>
        <ModalBody>
          {columns.map((col) => (
            <Input
              key={col}
              label={col.charAt(0).toUpperCase() + col.slice(1)}
              clearable
              fullWidth
              value={selectedItem?.[col] || ''}
              onChange={e => setSelectedItem({ ...selectedItem, [col]: e.target.value })}
            />
          ))}
        </ModalBody>
        <ModalFooter>
          <Button auto onClick={handleEditSubmit}>
            Save Changes
          </Button>
          <Button auto flat color="error" onClick={() => setShowEditModal(false)}>
            Cancel
          </Button>
        </ModalFooter>
		</>
	  )}
	  </ModalContent>
      </Modal>

      {/* Delete Confirmation Modal */}
      <Modal
        closeButton
        isOpen={showDeleteModal}
        onClose={() => setShowDeleteModal(false)}
      >
	  <ModalContent>
	  {(onClose) => (
		<>
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
		</>
	  )}
	  </ModalContent>
      </Modal>
      
	  {/* New Modal */}
      <Modal
        closeButton
        isOpen={showNewModal}
        onClose={() => setShowNewModal(false)}
      >
	  <ModalContent>
	  {(onClose) => (
		<>
        <ModalHeader>
          <h2>New {entity}</h2>
        </ModalHeader>
        <ModalBody>
          {columns.map((col) => (
            <Input
              key={col}
              label={col.charAt(0).toUpperCase() + col.slice(1)}
              clearable
              fullWidth
              onChange={e => setNewItem({ [col]: e.target.value })}
            />
          ))}
        </ModalBody>
        <ModalFooter>
          <Button auto onClick={handleNewSubmit}>
            Save Changes
          </Button>
          <Button auto flat color="error" onClick={() => setShowNewModal(false)}>
            Cancel
          </Button>
        </ModalFooter>
		</>
	  )}
	  </ModalContent>
      </Modal>
    </div>
  );
}
