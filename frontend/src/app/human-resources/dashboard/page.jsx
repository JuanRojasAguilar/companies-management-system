'use client'

import generalDashboard from "@/components/human-resources/generalDashboard"

const styles = {
    mainDiv : "w-screen h-screen"
}

export default function CustomerPage() {
    return (
        <main className={styles.mainDiv}>
            <section>
                <generalDashboard/>
            </section>
        </main>
    )    
}
