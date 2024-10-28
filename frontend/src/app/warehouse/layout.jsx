import { NextUIProvider } from "@nextui-org/react";
import NavigationBar from "@/components/navBar";
import { warehousePages } from "@/lib/pagesArrays/warehousePages";

const styles = {
    mainDiv: "flex h-screen w-screen",
    currentPage: "w-full h-full",
}

export default function warehouseLayout({ children }) {
    return (
        <NextUIProvider>
            <div className={styles.mainDiv}>
                <main className={styles.currentPage}>{children}</main>
                <NavigationBar pages={warehousePages}/>
            </div>
        </NextUIProvider>
    )
}
