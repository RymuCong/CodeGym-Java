class Switch {
    constructor() {
        this.status = false;
    }

    switchOn() {
        this.status = true;
    }

    switchOff() {
        this.status = false;
    }

    connectToLamp(lamp) {
        this.lamp = lamp;
        document.write("Công tắc đã được kết nối với đèn <br>")
    }
}

class Lamp {
    constructor() {
        this.status = false;
    }

    turnOn() {
        this.status = true;
    }

    turnOff() {
        this.status = false;
    }
}

let switchButton = new Switch();
let lamp = new Lamp();

switchButton.connectToLamp(lamp);

let count = 1;

while (count <= 10)
{
    if (switchButton.status === false)
    {
        switchButton.switchOn();
        lamp.turnOn();
        document.write(`<br>Lần ${count}: Đèn đang sáng & Công tắc đang mở <br>`);
    }
    else
    {
        switchButton.switchOff();
        lamp.turnOff();
        document.write(`<br>Lần ${count}: Đèn đã tắt & Công tắc đã đóng <br>`);
    }
    count++;
}