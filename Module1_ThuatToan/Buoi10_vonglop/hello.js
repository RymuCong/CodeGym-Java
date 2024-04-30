var cong = [{
    "ten": "Cong",
    "tuoi": 15
}, {
    "ten": "huy",
    "tuoi": 15
}];
for (x in cong) { // tung thuoc tinh trong obj
    console.log(x, cong[x]);
}
for (x of cong) { // object trong 1 mang
    console.log(x["ten"], x["tuoi"]);
}