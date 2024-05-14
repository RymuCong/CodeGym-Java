let a = [0,12,4,5,6]

let c = {
    "lmao" : 1,
    "lmao2" : 2
}

let b = 10;

function changeA (a) {
    a[0] = 100;
}

function changB (b){
    b = 20;
}

function changeC (c){
    c.lmao = 100;
}

changeA(a);
changB(b);
changeC(c);
console.log(a);
console.log(b);
console.log(c)