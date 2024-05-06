function bai2(number1) {
    number1++;
    document.getElementById("bai2").innerHTML = number1.toString();
}

function bai3() {
    let number1 = +prompt("Nhập số thứ nhất");
    let number2 = +prompt("Nhập số thứ hai");
    if (number1 > number2) {
        alert("Số thứ 1 lớn hơn số thứ 2");
    }
    else {
        let sum = number1 + number2;
        document.getElementById("bai3").innerHTML = "Tổng 2 số là: " + sum.toString();
    }
}

function bai5() {
    let array_chom_sao = ["Polaris", "Aldebaran", "Deneb", "Vega", "Altair", "Dubhe", "Regulus"];
    let array_ngoi_sao = ["Ursa Minor", "Tarurus", "Cygnus", "Lyra", "Aquila", "Ursa Major", "Leo"];
    let name = prompt("Nhập tên ngôi sao");
    let chom_sao = name.toLowerCase();
    chom_sao = chom_sao[0].toUpperCase() + chom_sao.slice(1);
    let index = array_chom_sao.indexOf(chom_sao);
    if (index >= 0) {
        document.getElementById("bai5").innerHTML = "Chòm sao tương ứng là: " + array_ngoi_sao[index];
    }
    else {
        document.getElementById("bai5").innerHTML = "Không có chòm sao tương ứng với ngôi sao: " + chom_sao;
    }
}