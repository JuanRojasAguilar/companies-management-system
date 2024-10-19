import { NextUIProvider } from "@nextui-org/react";
import CustomerNavigation from "@/components/customer/customerNavBar";
import { comicSans } from "../layout";

const styles = {
    mainDiv: "flex h-screen w-screen",
    currentPage: "w-full h-full",
}

export default function customerLayout({ children }) {
    return (
                <NextUIProvider>
                    <div className={styles.mainDiv}>
                        <main className={styles.currentPage}>{children}</main>
                        <CustomerNavigation/>
                    </div>
                </NextUIProvider>
    )
}