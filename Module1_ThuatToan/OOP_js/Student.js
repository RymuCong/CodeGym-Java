class Student {

    constructor(name, email, age, GPA) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.GPA = GPA;
    }

    getInformation() {
        document.write("Student Information: <br>");
        document.write("Name: " + this.name + "<br>");
        document.write("Email: " + this.email + "<br>");
        document.write("Age: " + this.age + "<br>");
        document.write("GPA: " + this.GPA + "<br>");
    }

    getRank() {
        if (this.GPA > 8) {
            document.write("Học sinh Giỏi <br>");
        } else if (this.GPA >= 6.5) {
            document.write("Học sinh Khá <br>");
        } else {
            document.write("Học sinh Yếu <br>");
        }
    }

    setName(name) {
        this.name = name;
    }
}


// let myStudent1 = new Student("Nguyễn Văn A", "XGZ7H@example.com", 19, 9.5);
// let myStudent2 = new Student("Nguyễn Văn B", "XGZ7H@example.com", 20, 6.5);
// let myStudent3 = new Student("Nguyễn Văn C", "XGZ7H@example.com", 21, 4.5);
//
//
// myStudent1.getInformation();
//
// myStudent1.getRank();
//
// myStudent2.getInformation();
//
// myStudent2.getRank();
//
// myStudent3.getInformation();
//
// myStudent3.getRank();