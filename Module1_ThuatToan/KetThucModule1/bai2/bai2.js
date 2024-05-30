function soSanhPhanSo(tu1, mau1, tu2, mau2)
{
    if (tu1 * mau2 === tu2 * mau1)
    {
        return "Hai phân số bằng nhau";
    }
    return tu1 * mau2 > tu2 * mau1;
}

let num;
// Nhập số testcase
do {
    num = +prompt("Nhập số testcase: ");
}
while (isNaN(num) || num <= 0);

// Khai báo biến tử, mẫu của 2 phân số
let tu1, mau1, tu2, mau2;

for (let i = 0; i < num; i++)
{
    // Nhập 2 phân số từng testcase
    do {
        tu1 = +prompt("Nhập tử số phân số thứ nhất: ");
        mau1 = +prompt("Nhập mẫu số phân số thứ nhất: ");
        tu2 = +prompt("Nhập tử số phân số thứ hai: ");
        mau2 = +prompt("Nhập mẫu số phân số thứ hai: ");

        document.write("Testcase " + (i + 1) + ": ");
        document.write(tu1 + "/" + mau1 + " , " + tu2 + "/" + mau2 + "<br>")
        document.write("<br>Kết quả: " + soSanhPhanSo(tu1, mau1, tu2, mau2));
        document.write("<br>---------------------------<br>");
    } while (isNaN(tu1) || isNaN(mau1) || isNaN(tu2) || isNaN(mau2) || mau1 === 0 || mau2 === 0);
}



