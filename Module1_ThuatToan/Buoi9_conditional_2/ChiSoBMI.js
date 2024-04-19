function BMI() {
    let cannang = +document.getElementById("cannang").value;
    let chieucao = +document.getElementById("chieucao").value;
    if (cannang <= 0 || chieucao <= 0) {
        document.getElementById("ketqua").innerHTML = "Cân nặng và chiều cao phải lớn hơn 0";
        return;
    }
    let BMI = cannang / Math.pow((chieucao / 100), 2);
    document.getElementById("ketqua").innerHTML = "Chi số BMI của bạn là:" + BMI.toFixed(2);
    if (BMI < 18.5) {
        document.getElementById("ketqua").innerHTML += "<br>Bạn thiếu cân nặng";
    } else if (BMI < 24.9) {
        document.getElementById("ketqua").innerHTML += "<br>Bạn bình thuong";
    } else if (BMI < 30) {
        document.getElementById("ketqua").innerHTML += "<br>Bạn thừa cân nặng";
    } else
        document.getElementById("ketqua").innerHTML += "<br>Bạn bị béo phì";
}
