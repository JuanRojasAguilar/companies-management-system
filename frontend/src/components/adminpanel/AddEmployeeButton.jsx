import { Modal, Input, ModalHeader, ModalBody, ModalFooter, RadioGroup, Radio, Button, useDisclosure, ModalContent } from "@nextui-org/react";
import { useState } from "react";

const AddEmployeeButton = ({ addEmployee }) => {
    const {isOpen, onOpen, onOpenChange} = useDisclosure();
    const [name, setName] = useState("");
    const [position, setPosition] = useState("");
    const handleClick = () => {
        addEmployee({name: name, position: position});
        setName("");
    }

    return (
        <div className="h-[92px] flex items-center">
            <Button className="w-min" onPress={onOpen}>Agregar Empleado</Button>
            <Modal
                isOpen={isOpen}
                onOpenChange={onOpenChange}
            >
                <ModalContent>
                    {(onClose) => (
                        <>
                            <ModalHeader className="flex flex-col gap-1">Agregar empleado</ModalHeader>
                            <ModalBody>
                                <Input
                                    autoFocus
                                    label="Nombre"
                                    placeholder="Ingrese el nombre"
                                    variant="bordered"
                                    value={name}
                                    onValueChange={setName}
                                />
                                <RadioGroup label="Selecciona el puesto" value={position} onValueChange={setPosition}>
                                    <Radio value="Backend Developer">Desarrollador Backend</Radio>
                                    <Radio value="Frontend Developer">Desarrollador Frontend</Radio>
                                    <Radio value="Fullstack Developer">Desarrollador Fullstack</Radio>
                                    <Radio value="Project Manager">Manager de proyecto</Radio>
                                </RadioGroup>
                            </ModalBody>
                            <ModalFooter>
                                <Button color="danger" variant="flat" onPress={onClose}>
                                    Close
                                </Button>
                                <Button color="primary" onPress={() => {handleClick(); onClose(); }} >
                                    Sign in
                                </Button>
                            </ModalFooter>
                        </>
                    )}
                </ModalContent>
            </Modal>
        </div>
    );
}

export default AddEmployeeButton;