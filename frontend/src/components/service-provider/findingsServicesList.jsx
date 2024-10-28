"use client";

import { useState, useEffect } from "react";
import {
  Table,
  TableHeader,
  TableColumn,
  TableBody,
  TableRow,
  TableCell,
  Input,
  Button,
  Checkbox,
} from "@nextui-org/react";
import useLocalStorage from "@/lib/hooks/useLocalStorage";

export default function FindingsServiceList() {
  const [services, setServices] = useState([]);
  const [selectedServices, setSelectedServices] = useState([]);
  const [localStorageService, setLocalStorageService] = useLocalStorage(
    "serviceFinding",
    {}
  );

  useEffect(() => {
    // Fetch services from API (replace with your API endpoint)
    const fetchServices = async () => {
      try {
        const response = await fetch("/api/services");
        const data = await response.json();
        setServices(data);
      } catch (error) {
        console.error("Error fetching services:", error);
      }
    };

    fetchServices();
  }, []);

  const handleEdit = (service) => {
    // Save the finding value of the selected service to local storage
    setLocalStorageService((prev) => ({ ...prev, [service.id]: service.finding }));
  };

  const handleSelection = (serviceId) => {
    setSelectedServices((prev) =>
      prev.includes(serviceId)
        ? prev.filter((id) => id !== serviceId)
        : [...prev, serviceId]
    );
  };

  const uploadServices = async () => {
    try {
      const servicesToUpload = services.filter((service) =>
        selectedServices.includes(service.id)
      );

      await fetch("/api/services/upload", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(servicesToUpload),
      });

      // Handle success
      console.log("Selected services uploaded successfully!");
    } catch (error) {
      console.error("Error uploading services:", error);
    }
  };

  const columns = ["name", "finding", "edit", "select"];

  return (
   <div className="space-y-4 h-full flex flex-col p-4">
	  <h1 className="text-lg text-center">Service Findings</h1>
      <Table
        aria-label="Service Findings Table"
        className="grow [&>div]:grow [&>div]:mb-2"
      >
        <TableHeader>
          {columns.map((column) => (
            <TableColumn className="text-center" key={column}>
              {column}
            </TableColumn>
          ))}
        </TableHeader>
        <TableBody items={services} emptyContent={"No services available"}>
          {(item) => (
            <TableRow key={item.id}>
              <TableCell>{item.nombre}</TableCell>
              <TableCell>
                <Input
                  value={item.finding}
                  onChange={(e) =>
                    setServices((prev) =>
                      prev.map((service) =>
                        service.id === item.id
                          ? { ...service, finding: e.target.value }
                          : service
                      )
                    )
                  }
                />
              </TableCell>
              <TableCell>
                <Button onClick={() => handleEdit(item)}>Edit</Button>
              </TableCell>
              <TableCell>
                <Checkbox
                  isSelected={selectedServices.includes(item.id)}
                  onChange={() => handleSelection(item.id)}
                />
              </TableCell>
            </TableRow>
          )}
        </TableBody>
      </Table>
      <Button color="default" variant="ghost" onClick={uploadServices}>
        Upload Selected Services
      </Button>
    </div>
  );
}

