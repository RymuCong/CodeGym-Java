function bai1() {
    document.getElementById("bai1").innerHTML = "";
    let numbers1 = new Array(10);
    for (let i = 0; i < 10; i++) {
        numbers1[i] = +prompt("Nhập vào phần tử thứ " + (i + 1) + " : ");
    }
    let count = 0;
    for (let i = 0; i < 10; i++) {
        if (numbers1[i] >= 10) {
            count++;
            document.getElementById("bai1").innerHTML += (numbers1[i] + " ");
        }
    }
    document.getElementById("bai1").innerHTML += ("Số lượng phần tử lớn hơn 10 là: " + count);
}

function bai2() {
    let numbers2 = new Array(10);
    for (let i = 0; i < 10; i++) {
        numbers2[i] = +prompt("Nhập vào phần tử thứ " + (i + 1) + " : ");
        for (let j = 0; j < i; j++) {
            if (numbers2[i] === numbers2[j]) {
                i--;
                break;
            }
        }
    }
    let max = numbers2[0];
    let index = 0;
    for (let i = 0; i < 10; i++) {
        if (numbers2[i] > max) {
            max = numbers2[i];
            index = i;
        }
    }
    document.getElementById("bai2").innerHTML = "";
    document.getElementById("bai2").innerHTML += ("Phần tử có giá trị lớn nhất trong mảng là: " + numbers2[index]);
    document.getElementById("bai2").innerHTML += ("<br>Vị trí của phần tử đó là: " + index);
}

function bai3() {
    let numArr = +prompt("Nhập số lượng phần tử trong mảng: ");
    let numbers3 = new Array(numArr);
    for (let i = 0; i < numArr; i++) {
        numbers3[i] = +prompt("Nhập phần tử thứ " + (i + 1) + " : ");
    }
    let max = numbers3[0];
    let sum = 0;
    for (let i = 0; i < numArr; i++) {
        sum += numbers3[i];
        if (numbers3[i] > max) {
            max = numbers3[i];
        }
    }
    document.getElementById("bai3").innerHTML = "";
    document.getElementById("bai3").innerHTML += ("Giá trị lớn nhất trong mảng là: " + max);
    document.getElementById("bai3").innerHTML += ("<br>Giá trị trung bình của phần tử trong mảng là: " + sum / numArr);
}

function bai4() {
    let numArr = +prompt("Nhập số lượng phần tử trong mảng: ");
    let numbers4 = new Array(numArr);
    for (let i = 0; i < numArr; i++) {
        numbers4[i] = prompt("Nhập phần tử thứ " + (i + 1) + " : ");
    }
    document.getElementById("bai4").innerHTML = "";
    document.getElementById("bai4").innerHTML += "Đảo ngược phần tử trong mảng: <br>";
    for (let i = 0; i < numArr; i++) {
        for (let j = numbers4[i].length - 1; j >= 0; j--) {
            document.getElementById("bai4").innerHTML += (numbers4[i][j]);
        }
        document.getElementById("bai4").innerHTML += (" ");
    }
}

function bai5() {
    document.getElementById("bai5").innerHTML = "";
    let string5 = prompt("Nhập chuỗi: ");
    let arr5 = string5.split("");
    let count = 0;
    document.getElementById("bai5").innerHTML = "";
    for (let i = 0; i < arr5.length; i++) {
        if (arr5[i] === '-' && (arr5[i + 1] > '1' && arr5[i + 1] < '9')) {
            count++;
        }
    }
    document.getElementById("bai5").innerHTML += "Số nguyên âm trong chuỗi là: " + count;
}

function bai6() {
    document.getElementById("bai6").innerHTML = "";
    let numbers6 = new Array(10);
    for (let i = 0; i < 10; i++) {
        numbers6[i] = +prompt("Nhập phần tử thứ " + (i + 1) + " : ");
    }
    let v6 = +prompt("Nhập V: ");
    let check = false;
    numbers6.forEach(x => {
        if (x === v6) {
            check = true;
        }
    })
    if (check) {
        document.getElementById("bai6").innerHTML = "V is in the array";
    }
    else {
        document.getElementById("bai6").innerHTML = "V is not in the array";
    }
}

function bai7() {
    document.getElementById("bai7").innerHTML = "";
    let numbers7 = new Array(10);
    for (let i = 0; i < 10; i++) {
        numbers7[i] = +prompt("Nhập phần tử thứ " + (i + 1) + " : ");
    }
    // 1 2 3 4 5 6 7 8 9 10
    let v7 = +prompt("Nhập V: ");
    let check = true;
    let i;
    for (i = 9; i >= 0; i--) {
        if (numbers7[i] === v7) {
            check = false;
            break;
        }
        }

    // 3 4 5 6 7 8 9 10 // 4 5 6 7 8 9 10 0
    for (let j = i; j < 10; j++) {
        numbers7[j] = numbers7[j + 1];
    }
    numbers7[9] = 0;
    if (!check)
        document.getElementById("bai7").innerHTML = "Mảng sau khi xóa phần tử V: " + numbers7;
    else
        document.getElementById("bai7").innerHTML = "Mảng không có phần tử V";
}

function bai8() {
    document.getElementById("bai8").innerHTML = "";
    let numbers8 = new Array(10);
    for (let i = 0; i < 10; i++) {
        numbers8[i] = +prompt("Nhập phần tử thứ " + (i + 1) + " : ");
    }
    for (let i = 0; i < numbers8.length - 1; i++) {
        for (let j = i + 1; j < numbers8.length; j++) {
            if (numbers8[i] < numbers8[j]) {
                let temp = numbers8[i];
                numbers8[i] = numbers8[j];
                numbers8[j] = temp;
            }
        }
    }
    document.getElementById("bai8").innerHTML = "Mảng sau khi sắp xếp giảm  dần: " + numbers8;
}

function bai9_ver1() {
    document.getElementById("bai9").innerHTML = "Ver 1 <br>";
    let numbers9_a = new Array(10);
    for (let i = 0; i < 10; i++) {
        numbers9_a[i] = +prompt("Nhập phần tử mảng a thứ " + (i + 1) + " : ");
    }
    let numbers9_b = new Array(10);
    for (let i = 0; i < 10; i++) {
        numbers9_b[i] = +prompt("Nhập phần tử mảng b thứ " + (i + 1) + " : ");
    }
    let numbers9 = numbers9_a.concat(numbers9_b);
    document.getElementById("bai9").innerHTML = "Mảng sau khi nối hai mảng: " + numbers9;
}

function bai9_ver2() {
    document.getElementById("bai9").innerHTML = "Ver 2 <br>";
    let numbers9_a = new Array(10);
    for (let i = 0; i < 10; i++) {
        numbers9_a[i] = +prompt("Nhập phần tử mảng a thứ " + (i + 1) + " : ");
    }
    let numbers9_b = new Array(10);
    for (let i = 0; i < 10; i++) {
        numbers9_b[i] = +prompt("Nhập phần tử mảng b thứ " + (i + 1) + " : ");
    }
    let numbers9 = new Array(numbers9_a.length + numbers9_b.length);
    for (let i = 0; i < numbers9_a.length; i++) {
        numbers9[i] = numbers9_a[i];
    }
    for (let i = 0; i < numbers9_b.length; i++) {
        numbers9[i + numbers9_a.length] = numbers9_b[i];
    }
    document.getElementById("bai9").innerHTML = "Mảng sau khi nối hai mảng: " + numbers9;
}