'use client'

import AddProduct from "@/components/warehouse/addProduct"

const styles = {
    mainDiv : "w-screen h-screen"
}

export default function CustomerPage() {
    return (
        <main className={styles.mainDiv}>
            <section>
                <AddProduct/>
            </section>
        </main>
    )    
}
