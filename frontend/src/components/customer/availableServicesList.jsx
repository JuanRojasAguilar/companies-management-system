"use client";

import useLocalStorage from "@/lib/hooks/useLocalStorage";
import { useState, useEffect } from "react";
import {
  Table,
  TableHeader,
  TableColumn,
  TableBody,
  TableRow,
  TableCell,
  getKeyValue,
  Input,
  Button,
} from "@nextui-org/react";

export default function AvailableServicesList() {
  const [services, setServices] = useState([]);
  const [filteredServices, setFilteredServices] = useState([]);
  const [searchQuery, setSearchQuery] = useState("");
  const [savedServices, setSavedServices, clearServices] = useLocalStorage("savedServices", [])

  useEffect(() => {}, []);

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

  const addToLocalStorage = (service) => {
    setSavedServices((prevSavedServices) => [...prevSavedServices, service]);
  };

  const columns = ["servicio", "precio", "añadir"];

  return (
    <div className="space-y-4 h-full flex flex-col">
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
        <TableHeader >
          {columns.map((column) => (
            <TableColumn className="text-center" key={column}>{column}</TableColumn>
          ))}
        </TableHeader>
        <TableBody
          items={filteredServices}
          emptyContent={"no hay servicios disponibles"}
        >
          {(item) => (
            <TableRow key={item.id}>
              {(columnKey) => (
                <TableCell>{getKeyValue(item, columnKey)}</TableCell>
              )}
              <TableCell>
                <Button
                  isIconOnly
                  color="primary"
                  aria-label="Añadir servicio"
                  onClick={() => addToLocalStorage(item)}
                >
                  <i className="bx bx-cart-add"></i>
                </Button>
              </TableCell>
            </TableRow>
          )}
        </TableBody>
      </Table>
    </div>
  );
}
