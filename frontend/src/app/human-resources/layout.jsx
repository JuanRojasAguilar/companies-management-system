import { NextUIProvider } from "@nextui-org/react";
import NavigationBar from "@/components/navBar";
import { hhrrPages } from "@/lib/pagesArrays/hhrrPages";

const styles = {
    mainDiv: "flex h-screen w-screen",
    currentPage: "w-full h-full",
}

export default function hhrrLayout({ children }) {
    return (
        <NextUIProvider>
            <div className={styles.mainDiv}>
                <main className={styles.currentPage}>{children}</main>
                <NavigationBar pages={hhrrPages}/>
            </div>
        </NextUIProvider>
    )
}
