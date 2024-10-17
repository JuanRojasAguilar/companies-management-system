import { Input, Button } from "@nextui-org/react";
import { useState } from "react";

const styles = {
    form: "bg-gray-50 bg-opacity-5 shadow-gray-700 shadow-lg transition shadow p-6 rounded-xl w-9/12",
    formElement: "mb-2",
    formButton: "self-center mr-2"
}

const LoginForm = () => {
    const [valueEmail, setValueEmail] = useState("");
    const [errorEmail, setErrorEmail] = useState("");
    const [valuePassword, setValuePassword] = useState("");
    const [errorPassword, setErrorPassword] = useState("");
    const [isSubmitted, setIsSubmitted] = useState(false);

    const handleSubmit = (event) => {
        event.preventDefault();
        setIsSubmitted(true);

        if (valueEmail.trim() === "") {
            setErrorEmail("El campo está vacío");
        } else if (valueEmail.length > 100) {
            setErrorEmail("El email es muy largo");
        } else {
            setErrorEmail("");
        }

        if (valuePassword.trim() === "") {
            setErrorPassword("La contraseña no puede estar vacía");
        } else if (valuePassword.length < 8) {
            setErrorPassword("La contraseña es muy corta");
        } else if (valuePassword.length > 20) {
            setErrorPassword("La contraseña es muy larga");
        } else {
            setErrorPassword("");
        }

        if (!errorEmail && !errorPassword) {
            console.log("Formulario enviado correctamente");

            setIsSubmitted(false);
        }
    };

    return (
        <form onSubmit={handleSubmit} className={styles.form}>
            <Input
                value={valueEmail}
                type="email"
                label="Email"
                variant="bordered"
                isInvalid={isSubmitted && errorEmail}
                color={errorEmail ? "danger" : ""}
                errorMessage={errorEmail}
                onValueChange={setValueEmail}
                className={styles.formElement}
            />
            <Input
                value={valuePassword}
                type="password"
                label="Contraseña"
                variant="bordered"
                isInvalid={isSubmitted && errorPassword}
                color={errorPassword ? "danger" : ""}
                errorMessage={errorPassword}
                onValueChange={setValuePassword}
                className={styles.formElement}
            />
            <Button type="submit" className={`${styles.formElement} ${styles.formButton}`}>
                <i class='bx bx-upload'/> Ingresar
            </Button>
            <Button className={`${styles.formElement} ${styles.formButton}`}>
                <i class='bx bx-user'/> Registrarse
            </Button>
        </form>
    )
}

export default LoginForm;