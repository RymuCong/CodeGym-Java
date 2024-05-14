class Apple {
    constructor(weight) {
        this.weight = weight;
    }

    getWeight() {
        return this.weight;
    }

    setWeight(weight) {
        this.weight = weight;
    }

    isEmpty() {
        return this.weight <= 0;
    }

    decrease() {
        if (this.isEmpty()) {
            alert("Đã ăn hết táo rồi");
        } else
            this.weight--;
    }
}

class Human {
    constructor(name, gender, weight) {
        this.name = name;
        this.gender = gender;
        this.weight = weight;
    }

    getName() {
        return this.name;
    }

    setName(name) {
        this.name = name;
    }

    getWeight() {
        return this.weight;
    }

    setWeight(weight) {
        this.weight = weight;
    }

    getGender() {
        if (this.isMale()) {
            return "Male";
        } else {
            return "Female";
        }
    }

    setGender(gender) {
        this.gender = gender;
    }

    isMale() {
        return this.gender === 1;
    }

    say(string) {
        document.write(string);
    }

    checkApple(apple) {
        return apple.isEmpty();
    }

    eat(apple) {
        if (apple.getWeight() > 0) {
            apple.decrease();
            this.weight++;
        } else {
            alert("Táo đã hết");
        }
    }

    getInfo () {
        document.write("Name: " + this.name + "<br>");
        document.write("Gender: " + (this.gender ? "Nam" : "Nữ") + "<br>");
        document.write("Weight: " + this.weight + "<br>");
    }
}


let adam = new Human("adam", 1, 65);
let eva = new Human("eva", 0, 55);
let apple_1 = new Apple(10);

adam.say("Tôi là Adam" + "<br>");
eva.say("Tôi là Eva" + "<br>");


while (apple_1.isEmpty() !== true) {
    document.write("<br>")
    document.write("Adam ăn táo" + "<br>");
    adam.eat(apple_1);

    document.write("Quả táo còn: " + apple_1.getWeight() + " đơn vị" + "<br>");
    adam.getInfo();

    document.write("<br>")

    document.write("Eva ăn táo" + "<br>");
    eva.eat(apple_1);

    document.write("Quả táo còn: " + apple_1.getWeight() + " đơn vị" + "<br>");
    eva.getInfo();

    if (apple_1.isEmpty() === true) {
        document.write("<br><b>" +"Táo đã hết" + "</b><br>");
    }
}