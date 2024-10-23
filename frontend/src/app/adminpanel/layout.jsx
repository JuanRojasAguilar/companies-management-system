"use client";
const { NextUIProvider } = require("@nextui-org/react");

const layout = ({ children }) => {
    return (
        <div className="h-screen pt-20">
            <NextUIProvider>
                {children}
            </NextUIProvider>
        </div>
    );
}

export default layout;