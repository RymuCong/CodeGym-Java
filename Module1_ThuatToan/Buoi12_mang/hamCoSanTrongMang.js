function bai1() {
    document.getElementById("bai1").innerHTML = "";
    let numArr = +prompt("Nhập số lượng phần tử trong mảng: ");
    let arr1 = new Array(numArr);
    for (let i = 0; i < numArr; i++) {
        arr1[i] = prompt("Nhập phần tử thứ " + (i + 1) + " : ");
    }
    document.getElementById("bai1").innerHTML += ("Mảng sau khi nối các phần tử là: " + arr1.toString());
}

function bai2() {
    document.getElementById("bai2").innerHTML = "";
    let num2 = prompt("Nhập 1 số bất kỳ: ");
    let result = [num2[0]];
    for (let i = 1; i < num2.length; i++) {
        if (num2[i-1] % 2 === 0 && num2[i] % 2 === 0) {
            result.push('-',num2[i]);
        }
        else
            result.push(num2[i]);
    }
    document.getElementById("bai2").innerHTML += "Dãy số sau khi chèn dấu (-) vào giữa 2 số chẵn là: " + result.join('');
}

function bai3() {
    document.getElementById("bai3").innerHTML = "";
    let str = prompt("Nhập 1 chuỗi: ");
    let result = "";
    for (let i = 0; i < str.length; i++) {
        if (str[i] === str[i].toUpperCase()) {
            result += str[i].toLowerCase();
        }
        else
            result += str[i].toUpperCase();
    }
    document.getElementById("bai3").innerHTML += "Chuỗi sau khi chuyển đổi : " + result;
}