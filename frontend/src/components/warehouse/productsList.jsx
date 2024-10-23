"use client";

import { Table, TableHeader, TableBody, TableFooter, TableRow, TableCell, Button, TableColumn } from "@nextui-org/react";
import { useState, useEffect } from "react";

export default function productsList() {
	const [products, setProducts] = useState({});
	const [productsAPI, setProductsAPI] = useState({});
	const [productsDetailsAPI, setProductsDetailsAPI] = useState({});
	const [details, setDetails] = useState({});

  useEffect(() => {
    // Fetch services from API (replace with your API endpoint)
    const fetchServices = async () => {
      try {
        const response = await fetch("localhost:8080/api/products");
        const dataProd = await response.json();
        setProductsAPI(dataProd);
			  console.og(productsAPI)
      } catch (error) {
        console.error("Error fetching products:", error);
      }
	}
  }, []);

  setProducts({
	id: 1,
	name: "nombre",
	value: 69,
	currStock: 420
  });
  setDetails({});

  const columns = ["name", "value", "current stock", "details"];

  return (
  <div className="space-y-4 h-full">
	<Table
	  aria-label="Products Table"
	  className="grow [&>div]:grow [&>div]:mb-2"
	>
      <TableHeader>
        {columns.map((column) => (
          <TableColumn className="text-center" key={column}>
            {column}
          </TableColumn>
        ))}
      </TableHeader>
	  <TableBody items={products} emptyContent={"No services available"}>
		{(item) => (
		  <TableRow key={item.id}>
			<TableCell>{item.name}</TableCell>
			<TableCell>{item.value}</TableCell>
			<TableCell>{item.currStock}</TableCell>
			<TableCell>
			  <Button color="default" auto ghost rounded>Details</Button>
			</TableCell>
		  </TableRow>
		)}
	  </TableBody>
	</Table>
  </div>
  ); 
}
