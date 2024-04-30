let mangTV = ["Chó", "Mèo", "Thỏ", "Táo", "Chuối", "Hoa", "Mặt trời", "Chim", "Cây", "Trường"];
let mangTA = ["Dog", "Cat", "Rabbit", "Apple", "Banana", "Flower", "Sun", "Bird", "Tree", "School"];

function chuyenDoi() {
    let dich = document.getElementById("text");
    let check = false;
    for (let i = 0; i < mangTA.length; i++) {
        if (mangTA[i].toUpperCase() === dich.value.toUpperCase()) {
            document.write("Từ sau khi dịch: " +mangTV[i]);
            check = true;
        }
        else if (mangTV[i].toUpperCase() === dich.value.toUpperCase()) {
            document.write("Từ sau khi dịch: " +mangTA[i]);
            check = true;
        }
    }
    if (!check) {
        document.write("Không tìm được!");
    }
}