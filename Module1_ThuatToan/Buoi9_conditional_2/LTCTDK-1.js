function Bai1 ()
{
    let a = +document.getElementById("a1").value;
    let b = +document.getElementById("b1").value;
    if (a % b === 0)
    {
        document.getElementById("ketqua1").innerHTML = "a chia hết cho b";
    }
    else
    {
        document.getElementById("ketqua1").innerHTML = "a không chia hết cho b";
    }
}

function Bai2 ()
{
    let tuoi = +document.getElementById("tuoi2").value;
    if (tuoi >= 6 && tuoi <= 200) // bao nhiêu tuổi học cũng được, học vượt cấp cũng được luôn
    {
        document.getElementById("ketqua2").innerHTML = "Đủ điều kiện học lớp 10";
    }
    else
    {
        document.getElementById("ketqua2").innerHTML = "Không đủ điều kiện học lớp 10";
    }
}

function Bai3 ()
{
    let so = +document.getElementById("so3").value;
    if (so < 0)
    {
        document.getElementById("ketqua3").innerHTML = "Số nhỏ hơn 0";
    }
    else if (so > 0)
    {
        document.getElementById("ketqua3").innerHTML = "Số lớn hơn 0";
    }
    else
    {
        document.getElementById("ketqua3").innerHTML = "Số bằng 0";
    }
}

function Bai4 ()
{
    let a = +document.getElementById("a4").value;
    let b = +document.getElementById("b4").value;
    let c = +document.getElementById("c4").value;
    let max = a;
    if (b > max)
    {
        max = b;
    }
    if (c > max)
    {
        max = c;
    }
    document.getElementById("ketqua4").innerHTML = "Số lớn nhất là: " + max;
}

function Bai5 ()
{
    let ktr1 = +document.getElementById("ktr1_5").value;
    let ktr2 = +document.getElementById("ktr2_5").value;
    let ktr3 = +document.getElementById("ktr3_5").value;
    let gk = +document.getElementById("gk_5").value;
    let ck = +document.getElementById("ck_5").value;
    let point = (ktr1 + ktr2 + ktr3 + gk*2 + ck*3)/8;
    if (ktr1 < 0 || ktr2 < 0 || ktr3 < 0 || gk < 0 || ck < 0)
    {
        document.getElementById("ketqua5").innerHTML = "Điểm thi phải lớn hơn hoặc bằng 0";
        return;
    }
    document.getElementById("ketqua5").innerHTML = "Điem trung bình của bạn: " + point.toFixed(2);
    if (point < 5)
    {
        document.getElementById("ketqua5").innerHTML += "<br>Học lực Kém";
    }
    else if (point < 6.5)
    {
        document.getElementById("ketqua5").innerHTML += "<br>Học lực Trung Bình";
    }
    else if (point < 8)
    {
        document.getElementById("ketqua5").innerHTML += "<br>Học lực Khá";
    }
    else
    {
        document.getElementById("ketqua5").innerHTML += "<br>Học lực Giỏi";
    }
}

function Bai6 ()
{
    let doanhso = +document.getElementById("doanhso6").value;
    let hoahong = 0;
    if (doanhso < 50)
    {
        hoahong = 0;
    }
    else if (doanhso < 100)
    {
        hoahong = doanhso * 0.02;
    }
    else if (doanhso < 500)
    {
        hoahong = 100 * 0.02 + (doanhso-100) * 0.025;
    }
    else if (doanhso < 1000)
    {
        hoahong = 100 * 0.02 + 400 * 0.025 + (doanhso-500) * 0.03;
    }
    else if (doanhso < 5000)
    {
        hoahong = 100 * 0.02 + 400 * 0.025 + 500 * 0.03 + (doanhso-1000) * 0.045;
    }
    else
    {
        hoahong = 100 * 0.02 + 400 * 0.025 + 500 * 0.03 + 4000 * 0.045 + (doanhso-5000) * 0.07;
    }
    document.getElementById("ketqua6").innerHTML = "Số hoa hồng nhận được là: " + hoahong.toFixed(3) + " triệu đồng";
}

function Bai7 ()
{
    let soPhut = +document.getElementById("phut7").value;
    let tien = 0;
    if (soPhut <= 60)
    {
        tien = soPhut * 1000;
    }
    else if (soPhut <= 120)
    {
        tien = 60 * 1000 + (soPhut - 60) * 1200;
    }
    else if (soPhut <= 180)
    {
        tien = 60 * 1000 + 60 * 1200 + (soPhut - 120) * 1500;
    }
    else
    {
        tien = 60 * 1000 + 60 * 1200 + 60 * 1500 + (soPhut - 180) * 2000;
    }
    const formatter = new Intl.NumberFormat('vi-VN', {
        style: 'currency',
        currency: 'VND'
    });
    document.getElementById("ketqua7").innerHTML = "Giá cước phải trả là: " + formatter.format(tien);
}