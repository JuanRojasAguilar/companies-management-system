'use client'

import FindingsServicesList from "@/components/service-provider/findingsServicesList"
import EditFinding from "@/components/service-provider/editFinding"

const styles = {
    mainDiv : "w-screen h-screen grid grid-cols-2"
}

export default function CustomerPage() {
    return (
        <main className={styles.mainDiv}>
            <section>
					<FindingsServicesList/>
            </section>
            <section>
                <EditFinding/>
            </section>
        </main>
    )    
}
