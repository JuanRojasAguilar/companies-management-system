'use client'

import ProductsList from "@/components/warehouse/productsList"

const styles = {
    mainDiv : "w-screen h-screen"
}

export default function CustomerPage() {
    return (
        <main className={styles.mainDiv}>
            <section>
                <ProductsList/>
            </section>
        </main>
    )    
}
