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
} from "@nextui-org/react";

export default function ApprovalServicesList() {
  const [services, setServices] = useState([]);
  const [filteredServices, setFilteredServices] = useState([]);
  const [searchQuery, setSearchQuery] = useState("");

  useEffect(() => {
    // Fetch services from API (replace URL with your API endpoint)
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

  useEffect(() => {
    const filtered = services.filter((service) =>
      Object.values(service).some((value) =>
        value.toString().toLowerCase().includes(searchQuery.toLowerCase())
      )
    );
    setFilteredServices(filtered);
  }, [searchQuery, services]);

  const handleSearch = (e) => {
    setSearchQuery(e.target.value);
  };

  const handleApproval = async (serviceId, isApproved) => {
	  //TODO fetch with security and so one
    try {
      const response = await fetch(`/api/services/${serviceId}`, {
        method: "PATCH",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ approved: isApproved }),
      });

      if (response.ok) {
        // Update the service state after successful approval/rejection
        setServices((prevServices) =>
          prevServices.map((service) =>
            service.id === serviceId
              ? { ...service, estado: isApproved ? "Aprobado" : "Rechazado" }
              : service
          )
        );
      }
    } catch (error) {
      console.error("Error updating service approval status:", error);
    }
  };

  const columns = [
    "nombre",
    "tiempo de ejecucion",
    "fecha",
    "estado de aprovacion",
    "aprovar",
  ];

  return (
    <div className="space-y-4 h-full flex flex-col p-4 pr-16">
	  <h1 className="text-lg text-center">Service Approval</h1>
      <Input
        classNames={{
          base: "max-w-full sm:max-w-[20rem] h-10",
          mainWrapper: "h-full",
          input: "text-small",
          inputWrapper:
            "h-full font-normal text-default-500 bg-default-400/20 dark:bg-default-500/20",
        }}
        placeholder="Buscar un servicio..."
        size="sm"
        startContent={<i className="bx bx-search"></i>}
        type="search"
        value={searchQuery}
        onChange={handleSearch}
      />
      <Table aria-label="Tabla de servicios disponibles" className="grow [&>div]:grow [&>div]:mb-2">
        <TableHeader>
          {columns.map((column) => (
            <TableColumn className="text-center" key={column}>
              {column}
            </TableColumn>
          ))}
        </TableHeader>
        <TableBody
          items={filteredServices}
          emptyContent={"no hay servicios disponibles"}
        >
          {(item) => (
            <TableRow key={item.id}>
              <TableCell>{item.nombre}</TableCell>
              <TableCell>{item.tiempoEjecucion}</TableCell>
              <TableCell>{item.fecha}</TableCell>
              <TableCell>{item.estado}</TableCell>
              <TableCell>
                <div className="flex justify-center space-x-2">
                  <Button
                    isIconOnly
                    color="success"
                    aria-label="Aprovar servicio"
                    onClick={() => handleApproval(item.id, true)}
                  >
                    <i className="bx bx-check"></i>
                  </Button>
                  <Button
                    isIconOnly
                    color="error"
                    aria-label="Rechazar servicio"
                    onClick={() => handleApproval(item.id, false)}
                  >
                    <i className="bx bx-x"></i>
                  </Button>
                </div>
              </TableCell>
            </TableRow>
          )}
        </TableBody>
      </Table>
    </div>
  );
}
