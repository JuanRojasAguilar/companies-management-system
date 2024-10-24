"use client";

import {
  Table,
  TableHeader,
  TableBody,
  TableRow,
  TableCell,
  Button,
  TableColumn,
} from "@nextui-org/react";
import { useState, useEffect } from "react";

export default function ProductsList() {
  const [products, setProducts] = useState([]); // Change to array
  const [productsAPI, setProductsAPI] = useState([]);
  const [productsDetailsAPI, setProductsDetailsAPI] = useState({});
  const [details, setDetails] = useState({});

  useEffect(() => {
    // Fetch products from API
    const fetchProducts = async () => {
      try {
        const response = await fetch("http://localhost:8080/api/products"); // Add http://
        const dataProd = await response.json();
        setProductsAPI(dataProd); // Store the fetched products in the state
        console.log(dataProd); // Fixed logging typo
      } catch (error) {
        console.error("Error fetching products:", error);
      }
    };

    fetchProducts();

    // Set initial product details (can be hardcoded or fetched)
    setProducts([
      {
        id: 1,
        name: "nombre",
        value: 69,
        currStock: 420,
      },
    ]);
    setDetails({});
  }, []); // Only run once when the component mounts

  const columns = ["name", "value", "current stock", "details"];

  return (
    <div className="space-y-4 h-full">
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
                <Button color="default" auto ghost rounded>
                  Details
                </Button>
              </TableCell>
            </TableRow>
          )}
        </TableBody>
      </Table>
    </div>
  );
}
