"use client";
import { Card, CardBody, Divider } from "@nextui-org/react"
import { useState } from "react";

const EmployeeCard = ({name, position}) => {
    const [hidden, setHidden] = useState(false);
    const handleClick = () => {
        setHidden(true);
    };

    return (
        <Card className={`h-[92px] w-[220px] ${hidden ? "hidden" : ""} hover:transition hover:ease-in-out hover:duration-150 hover:shadow-2xl hover:shadow-indigo-500/50`}>
            <CardBody className="flex flex-col">
                <p className="text-xl text-white">{name}</p>
                <Divider />
                <p className="text-lg text-white pt-2 flex justify-between">{position}<button onClick={handleClick} className="hover:text-red-500">x</button></p>
            </CardBody>
        </Card>
    );
}

export default EmployeeCard;