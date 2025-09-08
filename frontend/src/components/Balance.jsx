function Balance({ balance }) {
    return (
        <div className="mb-4 text-xl">
            Balance: <span className="font-bold">${balance}</span>
        </div>
    );
}

export default Balance;
