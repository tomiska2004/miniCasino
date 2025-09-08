// src/App.jsx
import { useState } from "react";
import SlotMachine from "./components/SlotMachine";
import Balance from "./components/Balance";

function App() {
    const [balance, setBalance] = useState(100);
    const [symbols, setSymbols] = useState(["🍒", "🍋", "🍊"]);
    const [winAmount, setWinAmount] = useState(0);

    const spin = async (bet) => {
        console.log("🎰 Spin function called with bet:", bet);
        console.log("💰 Current balance before spin:", balance);

        if (bet > balance) {
            console.warn("⛔ Not enough balance to spin!");
            alert("Not enough balance!");
            return;
        }

        try {
            console.log("📡 Sending POST request to: http://localhost:8080/slot/1/spin?bet=" + bet);

            const response = await fetch(`http://localhost:8080/slot/1/spin?bet=${bet}`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
            });

            console.log("📡 Response received. Status:", response.status);

            if (!response.ok) {
                const errorText = await response.text();
                console.error("❌ Server responded with error:", errorText);
                throw new Error(`Spin failed: ${response.status} ${response.statusText}`);
            }

            const result = await response.json();
            console.log("✅ Spin result received:", result);

            // Update state
            console.log("🔁 Updating state with new symbols, winAmount, and balance...");
            setSymbols(result.symbols);
            setWinAmount(result.winAmount);
            setBalance(result.newBalance);

            console.log("🎉 Spin completed successfully!");
        } catch (err) {
            console.error("💥 Error during spin:", err);
            alert("Error: " + err.message);
        }
    };

    return (
        <div className="flex flex-col items-center justify-center min-h-screen bg-gray-900 text-white">
            <h1 className="text-3xl font-bold mb-6">🎰 Mini Casino</h1>
            <Balance balance={balance} />
            <SlotMachine symbols={symbols} onSpin={spin} />
            <p className="mt-4">Last win: ${winAmount}</p>
        </div>
    );
}

export default App;