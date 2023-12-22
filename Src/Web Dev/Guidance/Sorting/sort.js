
let grades = [100,50,90,80,70,60];

grades = grades.sort(descending);

grades.forEach(print);

grades = grades.sort(ascending);

grades.forEach(print);

function descending(x,y){
    return y -  x;
}

function ascending(x,y){
    return x - y;
}

function print(element){
    console.log(element);
}