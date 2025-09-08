import { useState } from "react";

function SlotMachine({ symbols, onSpin }) {
    const [bet, setBet] = useState(10);

    return (
        <div className="text-center">
            <div className="grid grid-cols-3 gap-2 text-5xl mb-6">
                {symbols.map((s, i) => (
                    <div key={i} className="bg-gray-800 p-4 rounded-lg shadow">
                        {s}
                    </div>
                ))}
            </div>
            <div className="flex items-center gap-4 justify-center">
                <input
                    type="number"
                    min="1"
                    value={bet}
                    onChange={(e) => setBet(e.target.value)}
                    className="px-2 py-1 rounded text-black"
                />
                <button
                    onClick={() => onSpin(bet)}
                    className="bg-green-500 px-4 py-2 rounded-lg hover:bg-green-600"
                >
                    Spin
                </button>
            </div>
        </div>
    );
}

export default SlotMachine;
