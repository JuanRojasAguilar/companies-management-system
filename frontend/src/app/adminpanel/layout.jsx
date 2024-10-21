"use client";
const { NextUIProvider } = require("@nextui-org/react");

const layout = ({ children }) => {
    return (
        <NextUIProvider>
            {children}
        </NextUIProvider>
    );
}

export default layout;