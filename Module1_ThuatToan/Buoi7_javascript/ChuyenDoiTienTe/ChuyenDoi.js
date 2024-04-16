function convertCurrency() {
    const exchangeRate = 25000;
    let amount = document.getElementById('amount').value;
    let fromCurrency = document.getElementById('fromCurrency').value;
    let toCurrency = document.getElementById('toCurrency').value;
    let result = 0;

    if (fromCurrency === 'VND' && toCurrency === 'USD') {
        result = amount / exchangeRate;
    } else if (fromCurrency === 'USD' && toCurrency === 'VND') {
        result = amount * exchangeRate;
    }

    document.getElementById('result').innerText = result.toFixed(2);
}