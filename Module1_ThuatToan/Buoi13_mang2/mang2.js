// Bài 2

function bai2()
{
    let arr = [];
    let numArr = +prompt("Nhập số lượng phần tử của mảng: ");
    for(let i = 0; i < numArr; i++) {
        arr[i] = prompt("Nhập phần tử thứ "+(i+1)+" trong mảng: ");
    }
    document.getElementById("bai2").innerHTML =(reverseArray(arr));
}
function reverseArray(arr) {
    return arr.reverse().join('');
}

function bai3()
{
    let arr2 = [];
    let numArr2 = +prompt("Nhập số lượng phần tử của mảng: ");
    for(let i = 0; i < numArr2; i++) {
        arr2[i] = prompt("Nhập phần tử thứ "+(i+1)+" trong mảng: ");
    }
    document.getElementById("bai3").innerHTML =(countDigits(arr2));
}

// Bài 3
function countDigits(arr) {
    let count = 0;
    for(let i = 0; i < arr.length; i++) {
        if(!isNaN(arr[i])) {
            count++;
        }
    }
    return count;
}

function bai4()
{
    let str4 = prompt("Nhập 1 chuỗi: ");
    document.getElementById("bai4").innerHTML =(countWords(str4));
}

// Bài 4
function countWords(str) {
    return str.split(' ').length;
}
