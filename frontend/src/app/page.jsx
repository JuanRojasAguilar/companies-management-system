'use client'

import LoginForm from "@/components/formPageComponents/loginForm";
import React from "react";

const styles = {
  mainDiv: "grid grid-cols-2 w-screen h-screen",
  formSection: "flex justify-center items-center",  
  titleSection: "flex flex-col justify-center items-center",
  title: "text-5xl mb-12",
  icon: "bx bx-buildings text-9xl hover:-translate-y-2 transition "
}

const LoginPage = () => {
  

  return (
    <main className={styles.mainDiv}>
      <section className={styles.formSection}>
        <LoginForm/>
      </section>
      <section className={styles.titleSection}>
        <h1 className={styles.title}>Service Market</h1>
        <i className={styles.icon} ></i>
      </section>
    </main>
  );
};

export default LoginPage;
