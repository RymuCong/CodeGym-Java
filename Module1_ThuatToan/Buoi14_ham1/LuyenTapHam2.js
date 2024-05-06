function bai4 ()
{
    let isNum = prompt("Nhập vào 1 ký tự: ");
    if (isNaN(parseInt(isNum)))
    {
        return document.getElementById("bai4").innerHTML = "Không phải là ký tự số";
    }
    else
    {
        return document.getElementById("bai4").innerHTML = "Là ký tự số";
    }
}

function bai5 ()
{
    let num1 = +prompt("Nhập số thứ nhất: ");
    let num2 = +prompt("Nhập số thứ hai: ");
    let num3 = +prompt("Nhập số thứ ba: ");
    let min = Math.min(num1, num2, num3);
    return document.getElementById("bai5").innerHTML = "Số nhỏ nhất là: " + min.toString();
}

function bai6 () {
    let num = +prompt("Nhập số: ");
    if (num > 0 && Number.isInteger(num)) {
        return document.getElementById("bai6").innerHTML = "Là số nguyên dương";
        // return true
    } else {
        return document.getElementById("bai6").innerHTML = "Không phải là số nguyên dương";
    }
}

function bai9 () {
    let string9 = prompt("Nhập vào 1 chuỗi: ");
    let char9 = prompt("Nhập vào 1 ký tự: ");
    let count = 0;
    for (let char of string9) {
        if (char === char9) {
            count++;
        }
    }
    if (count === 0)
        document.getElementById("bai9").innerHTML = "-1";
    else
        document.getElementById("bai9").innerHTML = "Số lần xuất hiện: " + count.toString();
}

