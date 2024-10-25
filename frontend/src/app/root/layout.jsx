import { NextUIProvider } from "@nextui-org/react";
import NavigationBar from "@/components/navBar";
import { rootPages } from "@/lib/pagesArrays/rootPages";

const styles = {
    mainDiv: "flex h-screen w-screen",
    currentPage: "w-full h-full",
}

export default function providerLayout({ children }) {
    return (
        <NextUIProvider>
            <div className={styles.mainDiv}>
                <main className={styles.currentPage}>{children}</main>
                <NavigationBar pages={rootPages}/>
            </div>
        </NextUIProvider>
    )
}
