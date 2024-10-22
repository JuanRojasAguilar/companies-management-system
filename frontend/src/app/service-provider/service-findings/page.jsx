'use client'

import FindingsServicesList from "@/components/service-provider/findingsServicesList"
import ServiceCard from "@/components/service-provider/editFinding"

const styles = {
    mainDiv : "w-screen h-screen grid grid-cols-2"
}

export default function ServiceProviderPage() {
    return (
        <main className={styles.mainDiv}>
            <section>
				<FindingsServicesList/>
            </section>
            <section>
                <ServiceCard/>
            </section>
        </main>
    )    
}
