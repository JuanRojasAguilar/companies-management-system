This is a [Next.js](https://nextjs.org/) project bootstrapped with [`create-next-app`](https://github.com/vercel/next.js/tree/canary/packages/create-next-app).

## Getting Started


```bash
npm run dev
```


# Reglas del trabajo

- Se usará el idioma inglés. (Exceptuando textos para el usuario)
- Se usará, en el backend, vertical slicing
- Cada uno tendra su rama "jhon-dev" "alejo-dev" "juan-dev"
- Para trabajar vamos a usar \[nombre\]\_\[responsabilidad\] "juan\_product-order"
- No se alimentará a Alejandro después de las 8 pm.
- No se alimentará a Juan antes de las 12 pm.
- Jhon no se alimenta.
- **Por ahora** Se va a usar la rama main **SOLO** para documentacion.

## Backend
### Domains

- Los id se escribiran de tal manera: *product_id* dejando el "id" al final.
- Usar @EqualsAndHashCode.Include en los id para evitar problemas con Lombok
- En las relaciones OneToMany, **siempre** usar la anotación *@JsonIgnore* 
- En relaciones ManyToOne, usar FetchType.Lazy

### Services

- Usar JpaRepository. (Preferiblemente)
