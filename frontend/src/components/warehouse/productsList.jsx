"use client";

import {
  Table,
  TableHeader,
  TableBody,
  TableRow,
  TableCell,
  Button,
  TableColumn,
  Modal, ModalContent, ModalHeader, ModalBody, ModalFooter, useDisclosure, Text
} from "@nextui-org/react";
import { useState, useEffect } from "react";

export default function ProductsList() {
  const [products, setProducts] = useState([]); // Change to array
  const [productsAPI, setProductsAPI] = useState([]);
  const [productsDetailsAPI, setProductsDetailsAPI] = useState({});
  const [details, setDetails] = useState({});  
  const {isOpen, onOpen, onOpenChange} = useDisclosure();

  useEffect(() => {
    // Fetch products from API
    const fetchProducts = async () => {
      try {
        const response = await fetch("http://localhost:8080/api/products"); 
        const dataProd = await response.json();
        setProductsAPI(dataProd); 
        console.log(dataProd); 
      } catch (error) {
        console.error("Error fetching products:", error);
      }
    };

    fetchProducts();

    setProducts([
      {
        id: 1,
        name: "nombre",
        value: 69,
        currStock: 420,
      },
    ]);
    setDetails({});
  }, []); 
  const columns = ["name", "value", "current stock", "more info"];

  return (
    <div className="space-y-4 h-full p-4">
	  <h1 className="text-lg text-center">Products List</h1>
	  <Table aria-label="Products Table" className="grow [&>div]:grow [&>div]:mb-2">
        <TableHeader>
          {columns.map((column) => (
            <TableColumn className="text-center" key={column}>
              {column}
            </TableColumn>
          ))}
        </TableHeader>
        <TableBody items={products} emptyContent={"No products available"}>
          {(item) => (
            <TableRow key={item.id}>
              <TableCell className="text-center">{item.name}</TableCell>
              <TableCell className="text-center">{item.value}</TableCell>
              <TableCell className="text-center">{item.currStock}</TableCell>
              <TableCell className="text-center">
                <Button color="default" auto ghost rounded onPress={onOpen}>
                  Details
                </Button>
              </TableCell>
            </TableRow>
          )}
        </TableBody>
      </Table>
	  <Modal isOpen={isOpen} onOpenChange={onOpenChange}>
        <ModalContent>
          {(onClose) => (
            <>
              <ModalHeader className="flex flex-col gap-1">Product Details</ModalHeader>
              <ModalBody>
			    <Table aria-label="Product Details Table" className="grow [&>div]:grow [&>div]:mb-2">
			      <TableHeader>
			        <TableColumn className="text-center">Attribute</TableColumn>
			        <TableColumn className="text-center">Value</TableColumn>
			      </TableHeader>
			      <TableBody>
			        <TableRow>
			          <TableCell className="text-center">Product Name</TableCell>
			          <TableCell className="text-center">{products[0]?.name}</TableCell>
			        </TableRow>
			        <TableRow>
			          <TableCell className="text-center">Value</TableCell>
			          <TableCell className="text-center">{products[0]?.value}</TableCell>
			        </TableRow>
			        <TableRow>
			          <TableCell className="text-center">Current Stock</TableCell>
			          <TableCell className="text-center">{products[0]?.currStock}</TableCell>
			        </TableRow>
			      </TableBody>
			    </Table>
              </ModalBody>
              <ModalFooter>
                <Button color="danger" variant="light" onPress={onClose}>
                  Close
                </Button>
              </ModalFooter>
            </>
          )}
        </ModalContent>
      </Modal>
    </div>
  );
}
