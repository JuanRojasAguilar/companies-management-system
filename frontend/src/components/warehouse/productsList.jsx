"use client";

import {
  Table,
  TableHeader,
  TableBody,
  TableRow,
  TableCell,
  Button,
  TableColumn,
  Modal, ModalContent, ModalHeader, ModalBody, ModalFooter, useDisclosure
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
              <ModalHeader className="flex flex-col gap-1">Modal Title</ModalHeader>
              <ModalBody>
			    <p> 
                  Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                  Nullam pulvinar risus non risus hendrerit venenatis.
                  Pellentesque sit amet hendrerit risus, sed porttitor quam.
                </p>
                <p>
                  Lorem ipsum dolor sit amet, consectetur adipiscing elit.
                  Nullam pulvinar risus non risus hendrerit venenatis.
                  Pellentesque sit amet hendrerit risus, sed porttitor quam.
                </p>
                <p>
                  Magna exercitation reprehenderit magna aute tempor cupidatat consequat elit
                  dolor adipisicing. Mollit dolor eiusmod sunt ex incididunt cillum quis. 
                  Velit duis sit officia eiusmod Lorem aliqua enim laboris do dolor eiusmod. 
                  Et mollit incididunt nisi consectetur esse laborum eiusmod pariatur 
                  proident Lorem eiusmod et. Culpa deserunt nostrud ad veniam.
                </p>
              </ModalBody>
              <ModalFooter>
                <Button color="danger" variant="light" onPress={onClose}>
                  Close
                </Button>
                <Button color="primary" onPress={onClose}>
                  Action
                </Button>
              </ModalFooter>
            </>
          )}
        </ModalContent>
      </Modal>
    </div>
  );
}
