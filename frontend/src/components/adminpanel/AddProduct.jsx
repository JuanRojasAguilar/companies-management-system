"use client";
import { Input, RadioGroup, Radio, Button, Textarea } from "@nextui-org/react";
import { useState } from "react";

const AddProduct = () => {
  const [name, setName] = useState("");
  const [isMaterialNeeded, setIsMaterialNeeded] = useState(false);

  const handleClean = () => {
    setName("");
    setIsMaterialNeeded(false);
  }

  return (
    <div>
      <h1>Agregar Servicio Nuevo</h1>
      <form>
        <Input label="Nombre del Servicio" value={name} onValueChange={setName} isRequired/>
        <RadioGroup label="Necesita materiales?" value={isMaterialNeeded} onValueChange={setIsMaterialNeeded}>
          <Radio value={true}>Si</Radio>
          <Radio value={false}>No</Radio>
        </RadioGroup>
        {isMaterialNeeded && <Textarea label="Ingresa los materiales"/>}
        <div>
          <Button>Registrar</Button>
          <Button onPress={handleClean}>Limpiar</Button>
        </div>
      </form>
    </div>
  );
}

export default AddProduct;
