'use client'

import React, { useState, useEffect } from "react"
import { Input, Button, Select, SelectItem } from "@nextui-org/react"
import { z } from "zod"

const styles = {
  form: "w-9/12 p-4",
  formElement: "mb-4",
  formButton: "self-center mr-2"
}

const phoneSchema = z.object({
  type: z.string().min(1, "Debe seleccionar un tipo de teléfono"),
  number: z.string().min(1, "El número de teléfono no puede estar vacío")
})  

const formSchema = z.object({
  firstName: z.string().min(1, "El nombre no puede estar vacío"),
  lastName: z.string().min(1, "El apellido no puede estar vacío"),
  email: z.string().email("Email inválido").max(100, "El email es muy largo"),
  emailType: z.string().min(1, "Debe seleccionar un tipo de email"),
  password: z.string().min(8, "La contraseña es muy corta").max(20, "La contraseña es muy larga"),
  phoneNumbers: z.array(phoneSchema).min(1, "Debe agregar al menos un número de teléfono"),
  personType: z.string().min(1, "Debe seleccionar un tipo de persona"),
  country: z.string().min(1, "Debe seleccionar un país"),
  region: z.string().min(1, "Debe seleccionar una región"),
  city: z.string().min(1, "Debe seleccionar una ciudad")
})

export default function RegisterForm() {
  const [countries, setCountries] = useState([])
  const [regions, setRegions] = useState([])
  const [cities, setCities] = useState([])
  const [emailTypes] = useState([])
  const [phoneTypes] = useState([])
  const [personTypes] = useState([
    { key: "1", label: "Prestador de servicios" },
    { key: "2", label: "Cliente" }
  ])

  const [formData, setFormData] = useState({
    firstName: "",
    lastName: "",
    email: "",
    emailType: "",
    password: "",
    phoneNumbers: [],
    personType: "",
    country: "",
    region: "",
    city: ""
  })

  const [errors, setErrors] = useState({})
  const [touched, setTouched] = useState({})
  const [isSubmitted, setIsSubmitted] = useState(false)

  useEffect(() => {
    const fetchCountries = async () => {
      const response = await fetch('https://api.example.com/countries')
      const data = await response.json()
      setCountries(data)
    }
    fetchCountries()
  }, [])

  useEffect(() => {
    if (formData.country) {
      const fetchRegions = async () => {
        const response = await fetch(`https://api.example.com/regions?countryId=${formData.country}`)
        const data = await response.json()
        setRegions(data)
      }
      fetchRegions()
    } else {
      setRegions([])
      setFormData(prev => ({ ...prev, region: "", city: "" }))
    }
  }, [formData.country])

  useEffect(() => {
    if (formData.region) {
      const fetchCities = async () => {
        const response = await fetch(`https://api.example.com/cities?regionId=${formData.region}`)
        const data = await response.json()
        setCities(data)
      }
      fetchCities()
    } else {
      setCities([])
      setFormData(prev => ({ ...prev, city: "" }))
    }
  }, [formData.region])

  const handleAddPhone = () => {
    setFormData(prev => ({
      ...prev,
      phoneNumbers: [...prev.phoneNumbers, { type: "", number: "" }]
    }))
  }

  const handlePhoneChange = (index, field, value) => {
    const updatedPhones = [...formData.phoneNumbers]
    updatedPhones[index][field] = value
    setFormData(prev => ({ ...prev, phoneNumbers: updatedPhones }))
  }

  const handleInputChange = (field, value) => {
    setFormData(prev => ({ ...prev, [field]: value }))
  }

  const handleSubmit = (event) => {
    event.preventDefault()
    setIsSubmitted(true)

    try {
      formSchema.parse(formData)
      console.log("Form submitted", formData)
      // Here you would typically send the data to your backend
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

  const handleBlur = (field) => {
    setTouched(prev => ({ ...prev, [field]: true }))
  }

  return (
    <form onSubmit={handleSubmit} className={styles.form}>
      <Input
        value={formData.firstName}
        label="Nombre"
        variant="bordered"
        onValueChange={(value) => handleInputChange("firstName", value)}
        isInvalid={isSubmitted && !!errors.firstName}
        errorMessage={errors.firstName}
        onBlur={() => handleBlur("firstName")}
        className={styles.formElement}
      />
      <Input
        value={formData.lastName}
        label="Apellido"
        variant="bordered"
        onValueChange={(value) => handleInputChange("lastName", value)}
        isInvalid={isSubmitted && !!errors.lastName}
        errorMessage={errors.lastName}
        onBlur={() => handleBlur("lastName")}
        className={styles.formElement}
      />
      <div className="flex gap-2">
        <Input
          value={formData.email}
          type="email"
          label="Email"
          variant="bordered"
          onValueChange={(value) => handleInputChange("email", value)}
          isInvalid={isSubmitted && !!errors.email}
          errorMessage={errors.email}
          onBlur={() => handleBlur("email")}
          className={`${styles.formElement} flex-grow`}
        />
        <Select
          label="Tipo de Email"
          variant="bordered"
          placeholder="Seleccionar"
          selectedKeys={formData.emailType ? [formData.emailType] : []}
          onSelectionChange={(keys) => handleInputChange("emailType", Array.from(keys)[0])}
          isInvalid={isSubmitted && !!errors.emailType}
          errorMessage={errors.emailType}
          onBlur={() => handleBlur("emailType")}
          className={`${styles.formElement} w-1/3`}
        >
          {emailTypes.map((type) => (
            <SelectItem key={type.key} value={type.key}>
              {type.label}
            </SelectItem>
          ))}
        </Select>
      </div>
      <Input
        value={formData.password}
        type="password"
        label="Contraseña"
        variant="bordered"
        onValueChange={(value) => handleInputChange("password", value)}
        isInvalid={isSubmitted && !!errors.password}
        errorMessage={errors.password}
        onBlur={() => handleBlur("password")}
        className={styles.formElement}
      />
      {formData.phoneNumbers.map((phone, index) => (
        <div key={index} className="flex gap-2">
          <Input
            value={phone.number}
            label="Número de Teléfono"
            variant="bordered"
            onValueChange={(value) => handlePhoneChange(index, "number", value)}
            className={`${styles.formElement} flex-grow`}
          />
          <Select
            label="Tipo de Teléfono"
            variant="bordered"
            placeholder="Seleccionar"
            selectedKeys={phone.type ? [phone.type] : []}
            onSelectionChange={(keys) => handlePhoneChange(index, "type", Array.from(keys)[0])}
            className={`${styles.formElement} w-1/3`}
          >
            {phoneTypes.map((type) => (
              <SelectItem key={type.key} value={type.key}>
                {type.label}
              </SelectItem>
            ))}
          </Select>
        </div>
      ))}
      {isSubmitted && errors.phoneNumbers && (
        <p className="text-danger mb-2">{errors.phoneNumbers}</p>
      )}
      <Select
        label="Tipo de Persona"
        variant="bordered"
        placeholder="Seleccionar"
        selectedKeys={formData.personType ? [formData.personType] : []}
        onSelectionChange={(keys) => handleInputChange("personType", Array.from(keys)[0])}
        isInvalid={isSubmitted && !!errors.personType}
        errorMessage={errors.personType}
        onBlur={() => handleBlur("personType")}
        className={styles.formElement}
      >
        {personTypes.map((type) => (
          <SelectItem key={type.key} value={type.key}>
            {type.label}
          </SelectItem>
        ))}
      </Select>
      <Select
        label="País"
        variant="bordered"
        placeholder="Seleccionar país"
        selectedKeys={formData.country ? [formData.country] : []}
        onSelectionChange={(keys) => handleInputChange("country", Array.from(keys)[0])}
        isInvalid={isSubmitted && !!errors.country}
        errorMessage={errors.country}
        onBlur={() => handleBlur("country")}
        className={styles.formElement}
      >
        {countries.map((country) => (
          <SelectItem key={country.id} value={country.id}>
            {country.name}
          </SelectItem>
        ))}
      </Select>
      <Select
        label="Región"
        variant="bordered"
        placeholder="Seleccionar región"
        selectedKeys={formData.region ? [formData.region] : []}
        onSelectionChange={(keys) => handleInputChange("region", Array.from(keys)[0])}
        isDisabled={!formData.country}
        isInvalid={isSubmitted && !!errors.region}
        errorMessage={errors.region}
        onBlur={() => handleBlur("region")}
        className={styles.formElement}
      >
        {regions.map((region) => (
          <SelectItem key={region.id} value={region.id}>
            {region.name}
          </SelectItem>
        ))}
      </Select>
      <Select
        label="Ciudad"
        variant="bordered"
        placeholder="Seleccionar ciudad"
        selectedKeys={formData.city ? [formData.city] : []}
        onSelectionChange={(keys) => handleInputChange("city", Array.from(keys)[0])}
        isDisabled={!formData.region}
        isInvalid={isSubmitted && !!errors.city}
        errorMessage={errors.city}
        onBlur={() => handleBlur("city")}
        className={styles.formElement}
      >
        {cities.map((city) => (
          <SelectItem key={city.id} value={city.id}>
            {city.name}
          </SelectItem>
        ))}
      </Select>
      <Button type="submit" className={`${styles.formElement} ${styles.formButton}`}>
        <i className='bx bx-upload'/> Registrarse
      </Button>
      <Button onClick={handleAddPhone} className={styles.formElement}>
        <i className='bx bx-plus'/> Añadir Teléfono
      </Button>
    </form>
  )
}