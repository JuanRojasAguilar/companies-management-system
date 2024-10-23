import { Card, CardBody, Divider } from "@nextui-org/react";
import EmployeeList from "../../components/adminpanel/EmployeeList";

const pageComponents = [
    {
        name: "Gestionar empleados",
        component: <EmployeeList />
    }
]

const Styles = {
    container: `
        flex
        flex-col
        px-8
    `,
    aside: `
        w-1/5
        border-2
        rounded-lg
        h-full
    `,
}

const AdminPanel = () => {
    return (
        <div className={Styles.container}>
            <div>
                <h1 className="text-6xl">Empresa Seria S.A.S</h1>
            </div>
            <div className="w-full h-full flex">
                <div className={Styles.aside}>
                    <h2>Servicios</h2>
                    <Divider />
                    <div></div>
                    <Card>
                        <CardBody>
                            <p>Diosanto</p>
                        </CardBody>
                    </Card>
                </div>
                <div>

                </div>
            </div>

        </div>
    );
}

export default AdminPanel;