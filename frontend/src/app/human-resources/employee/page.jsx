'use client'

import EmployeeManagement from "@/components/human-resources/employeeManage"

const styles = {
    mainDiv : "w-screen h-screen"
}

export default function CustomerPage() {
    return (
        <main className={styles.mainDiv}>
            <section>
                <EmployeeManagement/>
            </section>
        </main>
    )    
}
