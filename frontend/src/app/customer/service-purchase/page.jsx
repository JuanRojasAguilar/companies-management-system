'use client'

import AvailableServicesList from "@/components/customer/availableServicesList"
import ServiceCart from "@/components/customer/serviceCart"

const styles = {
    mainDiv : "w-screen h-screen grid grid-cols-2"
}

export default function CustomerPage() {
    return (
        <main className={styles.mainDiv}>
            <section>
                <AvailableServicesList/>
            </section>
            <section>
                <ServiceCart/>
            </section>
        </main>
    )    
}