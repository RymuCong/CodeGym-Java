function footToMeter(foot)
{
    document.getElementById("footToMeter").innerHTML = foot + " foot = ";
    return document.getElementById("footToMeter").innerHTML += (foot * 0.305).toString() + " meter";
}

function meterToFoot(meter)
{
    document.getElementById("meterToFoot").innerHTML = meter + " meter = ";
    return document.getElementById("meterToFoot").innerHTML += (meter * 3.279).toString() + " foot";
}

let foot = +prompt("Nhập feet: ");
footToMeter(foot);

let meter = +prompt("Nhập meter: ");
meterToFoot(meter);