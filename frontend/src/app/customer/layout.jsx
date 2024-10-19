import { NextUIProvider } from "@nextui-org/react";
import NavigationBar from "@/components/navBar";
import { customerPages } from "@/lib/pagesArrays/customerPages";

const styles = {
    mainDiv: "flex h-screen w-screen",
    currentPage: "w-full h-full",
}

export default function customerLayout({ children }) {
    return (
        <NextUIProvider>
            <div className={styles.mainDiv}>
                <main className={styles.currentPage}>{children}</main>
                <NavigationBar pages={customerPages}/>
            </div>
        </NextUIProvider>
    )
}