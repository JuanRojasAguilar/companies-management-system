'use client'

import React from "react"
import { Input, Button } from "@nextui-org/react"
import { z } from "zod"

const styles = {
  form: "bg-gray-50 bg-opacity-5 shadow-gray-700 shadow-lg transition shadow p-6 rounded-xl w-9/12",
  formElement: "mb-2",
  formButton: "self-center mr-2"
}

const loginSchema = z.object({
  email: z.string().email("Email inválido").max(100, "El email es muy largo"),
  password: z.string().min(8, "La contraseña es muy corta").max(20, "La contraseña es muy larga")
})

export default function LoginForm() {
  const [formData, setFormData] = React.useState({ email: "", password: "" })
  const [errors, setErrors] = React.useState({ email: "", password: "" })
  const [isSubmitted, setIsSubmitted] = React.useState(false)

  const handleInputChange = (field, value) => {
    setFormData(prev => ({ ...prev, [field]: value }))
    if (isSubmitted) {
      validateField(field, value)
    }
  }

  const validateField = (field, value) => {
    try {
      loginSchema.pick({ [field]: true }).parse({ [field]: value })
      setErrors(prev => ({ ...prev, [field]: "" }))
    } catch (error) {
      if (error instanceof z.ZodError) {
        setErrors(prev => ({ ...prev, [field]: error.errors[0].message }))
      }
    }
  }

  const handleSubmit = (event) => {
    event.preventDefault()
    setIsSubmitted(true)

    try {
      loginSchema.parse(formData)
      console.log(formData)
      
      // Here you would typically send the data to your backend
      
      setIsSubmitted(false)
      setFormData({ email: "", password: "" })
    } catch (error) {
      if (error instanceof z.ZodError) {
        const newErrors = {}
        error.errors.forEach(err => {
          newErrors[err.path[0]] = err.message
        })
        setErrors(newErrors)
      }
    }
  }

  return (
    <form onSubmit={handleSubmit} className={styles.form}>
      <Input
        value={formData.email}
        type="email"
        label="Email"
        variant="bordered"
        isInvalid={isSubmitted && !!errors.email}
        color={errors.email ? "danger" : ""}
        errorMessage={errors.email}
        onValueChange={(value) => handleInputChange("email", value)}
        className={styles.formElement}
      />
      <Input
        value={formData.password}
        type="password"
        label="Contraseña"
        variant="bordered"
        isInvalid={isSubmitted && !!errors.password}
        color={errors.password ? "danger" : ""}
        errorMessage={errors.password}
        onValueChange={(value) => handleInputChange("password", value)}
        className={styles.formElement}
      />
      <Button type="submit" className={`${styles.formElement} ${styles.formButton}`}>
        <i className='bx bx-upload'/> Ingresar
      </Button>
      <a href="/register"><Button className={`${styles.formElement} ${styles.formButton}`}>
        <i className='bx bx-user'/> Registrarse
      </Button></a>
    </form>
  )
}