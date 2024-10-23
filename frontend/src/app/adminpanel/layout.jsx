"use client";
const { NextUIProvider } = require("@nextui-org/react");

const layout = ({ children }) => {
    return (
        <NextUIProvider>
            <div className="h-screen pt-20">
                {children}
            </div>
        </NextUIProvider>
    );
}

export default layout;