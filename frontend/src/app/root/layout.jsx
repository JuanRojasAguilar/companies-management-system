export default function RootLayout() {
    return (
        <NextUIProvider>
            <div className={styles.mainDiv}>
                <main className={styles.currentPage}>{children}</main>
                <NavigationBar pages={customerPages}/>
            </div>
        </NextUIProvider>
    )
}