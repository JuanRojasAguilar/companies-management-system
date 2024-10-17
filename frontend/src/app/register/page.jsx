'use client'

import RegisterForm from "@/components/formPageComponents/registerForm"
import React from "react"

const styles = {
    mainDiv: "flex justify-center items-center w-screen min-h-screen",
}

const RegisterPage = () => {
    return (
        <main className={styles.mainDiv}>
            <RegisterForm/>
        </main>
    );
};

export default RegisterPage;
