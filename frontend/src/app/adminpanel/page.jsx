"use client";

import { Card, CardBody, CardFooter, Divider } from "@nextui-org/react";
import EmployeeList from "../../components/adminpanel/EmployeeList";
import { Fragment, useState } from "react";
import AddProduct from "../../components/adminpanel/AddProduct";

const pageComponents = [
    {
        name: "Gestionar empleados",
        component: <EmployeeList />
    },
    {
        name: "Gestionar Servicios",
        component: <AddProduct />
    }
]

const Styles = {
    container: `
        flex
        flex-col
        px-8
        pb-8
        h-full
        bg-black/50
    `,
    aside: `
        w-1/5
        border-2
        rounded-l-lg
    `,
}

const AdminPanel = () => {
    const [pageLoaded, setPageLoaded] = useState(<Fragment></Fragment>);
    const handleClick = (pageElement) => {
        setPageLoaded(pageElement);
    }
    return (
        <div className={Styles.container}>
            <div className="mb-4">
                <h1 className="text-6xl">Empresa Seria S.A.S</h1>
            </div>
            <div className="w-full h-full flex">
                <div className={Styles.aside}>
                    <h2 className="text-2xl mb-2">Menu Administrativo</h2>
                    <Divider />
                    <div className="mt-4 text-xl flex flex-col gap-4">
                    {pageComponents.map(({name, component}) => (
                        <Card key={name}>
                            <CardBody>
                                <p>{name}</p>
                                <button className="text-sm w-fit" onClick={() => handleClick(component)}>Click Aqui</button>
                            </CardBody>
                        </Card>
                    ))}
                    </div>
                </div>
                <div className="w-4/5 border-2 rounded-r-lg">
                    {pageLoaded}
                </div>
            </div>
        </div>
    );
}

export default AdminPanel;
