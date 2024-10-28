'use client'

import userProfi from "@/components/human-resources/userProfile"

const styles = {
    mainDiv : "w-screen h-screen"
}

export default function CustomerPage() {
    return (
        <main className={styles.mainDiv}>
            <section>
                <userProfi/>
            </section>
        </main>
    )    
}
