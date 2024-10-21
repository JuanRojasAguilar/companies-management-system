'use client'

import ApprovalServicesList from "@/components/service-provider/approvalServicesList"

const styles = {
    mainDiv : "w-screen h-screen flex flex-col p-3"
}

export default function CustomerPage() {
    return (
        <main className={styles.mainDiv}>
            <section>
                <ApprovalServicesList/>
            </section>
        </main>
    )    
}
