'use client'

import ApproveClientCart from "@/components/warehouse/approveClientCart"

const styles = {
    mainDiv : "w-screen h-screen"
}

export default function ClientCartPage() {
    return (
        <main className={styles.mainDiv}>
            <section>
                <ApproveClientCart/>
            </section>
        </main>
    )    
}
