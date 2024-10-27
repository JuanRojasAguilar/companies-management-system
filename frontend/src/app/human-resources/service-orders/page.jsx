'use client'

import orderService from "@/components/human-resources/orderService"

const styles = {
    mainDiv : "w-screen h-screen"
}

export default function CustomerPage() {
    return (
        <main className={styles.mainDiv}>
            <section>
                <orderService/>
            </section>
        </main>
    )    
}
