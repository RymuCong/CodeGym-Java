// Hàm kiểm tra số nguyên tố
function checkSNT(n) {
    if (n <= 1) {
        return false;
    }
    for (let i = 2; i * i <= n; i++) {
        if (n % i === 0) {
            return false;
        }
    }
    return true;
}

// Nhập số phần tử mảng a
do {
    var num = +prompt("Nhập số phần tử mảng a: ");
} while (isNaN(num) || num <= 0 || num >= 50);

// Khai báo mảng a
let numA = [];

// Nhập mảng a
for (let i = 0; i < num; i++) {
    do {
        numA[i] = +prompt("Nhập phần tử thứ " + (i + 1) + " của mảng a: ");
    } while (isNaN(numA[i]));
}


// Tạo mảng b chứa các số nguyên tố của a
let numB = numA.filter(checkSNT);

// In ra mảng a và b
document.write("<br>" + "Mảng a: ", numA + "<br>");
document.write( "<br>" + "Mảng b: ", numB + "<br>");