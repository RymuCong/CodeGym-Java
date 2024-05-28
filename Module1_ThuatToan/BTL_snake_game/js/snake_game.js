// Khai báo có thông tin lấy từ file index.html
let
    canvas         = document.getElementById('canvas'),
    ctx                          = canvas.getContext('2d'),
    scoreBlock     = document.getElementById('score'),
    scoreCount          = 0,
    bestScoreBlock = document.getElementById('best-score'),
    dir                   = '', // snake direction
    diff                  = 'Easy', // độ khó
    diffBlock          = document.getElementById('difficulty'),
    btnChange          = document.getElementById('changeMode');

canvas.width = 720;
canvas.height = 480;

// Cấu hình game
const config = {
    sizeCell: 24, //  Kích thước của một ô trong trò chơi.
    sizeFood: 24, // Kích thước của thức ăn.
    step: 0, // Số bước di chuyển
    stepMax: 10
}

class Snake {
    constructor(config) {
        this.x = config.sizeCell;
        this.y = config.sizeCell;
        this.dirX = 0; // direction X
        this.dirY = 0; // direction Y
        this.body = [];
        this.maxBodySize = 1;
        this.snakeSkins = "./images/snake/head.svg";
        this.snakeImages = [new Image()];
        this.imageLoaded = false;
    }

    loadSnakeSkins() {
        this.snakeImages[0].src = this.snakeSkins;
        this.snakeImages[0].onload = () => { this.imageLoaded = true; } // Add this line
    }

    snakeStyles(x, y, index) {
        if (this.imageLoaded) { // Check if the image is loaded before drawing
            if ( index === 0 ) { // for the first snake element(head)
                ctx.drawImage(this.snakeImages[0], x, y, config.sizeCell, config.sizeCell);
            }
            else { // other elements
                ctx.fillStyle = '#093D14';
                ctx.strokeStyle = '#071510';
                ctx.lineWidth = 1;
                ctx.fillRect(x, y, config.sizeCell, config.sizeCell);
                ctx.strokeRect(x, y, config.sizeCell - 1, config.sizeCell - 1);
            }
        }
    }
}

let snake = new Snake(config);
snake.loadSnakeSkins();

class Food {
    constructor(config, images) {
        this.x = randomInt(0, canvas.width / config.sizeCell) * config.sizeCell;
        this.y = randomInt(0, canvas.height / config.sizeCell) * config.sizeCell;
        this.images = images;
        this.img = new Image();
        this.img.src = this.images[0]; // the default will be the first image
    }
}

const imagesFood = [
    './images/food/apple.svg',
    './images/food/carrot.svg',
    './images/food/eggplant.svg',
    './images/food/banana.svg',
];

function drawFood() {
    ctx.drawImage(img, food.x, food.y, config.sizeFood, config.sizeFood);
}

function randomPosFood() { // random food position
    ctx.drawImage(randomImg(), food.x, food.y, config.sizeFood, config.sizeFood);
    food.x = randomInt(0, canvas.width / config.sizeCell) * config.sizeCell;
    food.y = randomInt(0, canvas.height / config.sizeCell) * config.sizeCell;
    drawFood();
}

let img = new Image();
img.src = imagesFood[0];

let food = new Food(config, imagesFood);


class Bomb {
    constructor(config) {
        this.x = -config.sizeCell; // default bomb will be hidden
        this.y = -config.sizeCell;
        this.bombImg = new Image();
        this.bombImg.src = './images/food/bomb.svg';
    }
}

let bomb = new Bomb(config);

function randomPosBomb() { // random bomb
    let chance = randomInt(1, 5);
    if ( chance === 3 ) { // 20% hien bom
        bomb.x = randomInt(0, canvas.width / config.sizeCell) * config.sizeCell;
        bomb.y = randomInt(0, canvas.height / config.sizeCell) * config.sizeCell;
        drawBomb();
    }
    else { // xoa bom
        bomb.x = -config.sizeCell;
        bomb.y = -config.sizeCell;
        drawBomb();
    }
}

function drawBomb() { // ve bomb
    ctx.drawImage(bomb.bombImg, bomb.x, bomb.y, config.sizeFood, config.sizeFood);
}

function drawScore() { // ve diem so
    scoreBlock.innerHTML = scoreCount;
}

function bestScore() {

    // xoa best score
    // localStorage.removeItem('best score easy');
    // localStorage.removeItem('best score hard');

    // Initialize best scores if they don't exist
    if (!localStorage.getItem('best score easy')) {
        localStorage.setItem('best score easy', 0);
    }
    if (!localStorage.getItem('best score hard')) {
        localStorage.setItem('best score hard', 0);
    }

    // Update the best score for the current mode
    if (diff === 'Easy' && scoreCount > localStorage.getItem('best score easy')) {
        localStorage.setItem('best score easy', scoreCount);
    } else if (diff === 'Hard' && scoreCount > localStorage.getItem('best score hard')) {
        localStorage.setItem('best score hard', scoreCount);
    }

    // Display the best score for the current mode
    if (diff === 'Easy') {
        bestScoreBlock.innerHTML = localStorage.getItem('best score easy');
    } else {
        bestScoreBlock.innerHTML = localStorage.getItem('best score hard');
    }
}
function score() {
    scoreCount++;
    bestScore();
    if ( scoreCount > 15 )
        config.stepMax = 6;
    else if ( scoreCount <= 15 )
        config.stepMax = 7;
    drawScore();
}

// Cập nhật score
window.addEventListener('load', bestScore());


// điều khiển rắn
function turnUp() {
    if ( dir !== 'down' ) {
        dir = 'up';
        snake.dirY = -config.sizeCell;
        snake.dirX = 0;
    }
}
function turnLeft() {
    if ( dir !== 'right' ) {
        dir = 'left';
        snake.dirX = -config.sizeCell;
        snake.dirY = 0;
    }
}
function turnDown() {
    if ( dir !== 'up' ) {
        dir = 'down';
        snake.dirY = config.sizeCell;
        snake.dirX = 0;
    }
}
function turnRight() {
    if ( dir !== 'left' ) {
        dir = 'right';
        snake.dirX = config.sizeCell;
        snake.dirY = 0;
    }
}

// nhận dữ liệu từ bàn phím
document.addEventListener('keydown', (e) => {
    if ( e.key === 'w' || e.key === 'ArrowUp' ) {
        turnUp();
    }
    else if ( e.key === 'a' || e.key === 'ArrowLeft' ) {
        turnLeft();
    }
    else if ( e.key === 's' || e.key === 'ArrowDown' ) {
        turnDown();
    }
    else if ( e.key === 'd' || e.key === 'ArrowRight' ) {
        turnRight();
    }
});

// draw snake
function drawSnake() {
    snake.x += snake.dirX;
    snake.y += snake.dirY;

    if (diff === 'Easy')
        checkBorder();
    if (diff === 'Hard')
        withoutBorder();

    // work with snake length
    snake.body.unshift({x: snake.x, y: snake.y});
    if ( snake.body.length > snake.maxBodySize ) {
        snake.body.pop();
    }   ``

    snake.body.forEach((e, index) => {

        snake.snakeStyles(e.x, e.y, index); // snake styles

        if ( e.x === food.x && e.y === food.y ) { // If snake ate food
            score();
            randomPosFood();
            snake.maxBodySize++;

            if ( diff === 'Hard' ) {
                randomPosBomb();
            }
        }
        // cham vao bom giam chieu dai
        if ( diff === 'Hard' ) {
            if ( e.x === bomb.x && e.y === bomb.y ) {
                if ( scoreCount >= 2 ) {
                    scoreCount = Math.ceil(scoreCount / 2);
                    snake.maxBodySize = scoreCount + 1;
                    for ( let i = 0; i < snake.maxBodySize; i++ ) {
                        snake.body.pop();
                    }
                    drawScore();
                    randomPosFood();
                    randomPosBomb();
                } else {
                    restart()
                }
            }
        }

        // Checking if the snake has touched the tail
        for ( let i = index + 1; i < snake.body.length; i++ ) {
            if ( e.x === snake.body[i].x && e.y === snake.body[i].y ) {
                endGame();
            }
        }
    });
}

// game
function gameLoop() {

    requestAnimationFrame(gameLoop);

    if ( ++config.step < config.stepMax )
        return;
    config.step = 0;

    ctx.clearRect(0, 0, canvas.width, canvas.height);
    drawFood();
    drawSnake();
    if (diff === 'Hard') {
        ctx.strokeStyle = '#f00';
        ctx.lineWidth = 5;
        ctx.strokeRect(0, 0, canvas.width, canvas.height);
        drawBomb();
    }
}

gameLoop();

// difficulty, restart
function checkBorder() { // A function that checks for going beyond the border
    // x
    if ( snake.x < 0 ) {
        snake.x = canvas.width - config.sizeCell;
    } else if ( snake.x >= canvas.width ) {
        snake.x = 0;
    }
    // y
    if ( snake.y < 0 ) {
        snake.y = canvas.height - config.sizeCell;
    } else if ( snake.y >= canvas.height ) {
        snake.y = 0;
    }
}

function withoutBorder() { // A function that checks if the snake is touched border
    if ( snake.x < 0 ) {
        endGame();
        restart();
    } else if ( snake.x >= canvas.width ) {
        endGame();
        restart();
    }
    if ( snake.y < 0 ) {
        endGame();
        restart();
    } else if ( snake.y >= canvas.height ) {
        endGame();
        restart();
    }
}

function endGame() {
    bestScore();
    swal("Game Over", "Điểm của bạn là: " + scoreCount)
        .then(() => {
            restart();
        });
}

function restart() { // A function restart game
    config.stepMax = 7;
    scoreCount = 0;
    drawScore();

    snake.x = config.sizeCell;
    snake.y = config.sizeCell;
    snake.body = [];
    snake.maxBodySize = 1;
    snake.dirX = 0;
    snake.dirY = 0;
    dir = '';

    randomPosFood();
    if ( diff === 'Hard' ) randomPosBomb();
}

// change difficulty
btnChange.addEventListener('click', (e) => {
    if ( diff === 'Easy' ) {
        diff = 'Hard';
        diffBlock.innerHTML = 'Khó';
        restart();
    } else {
        diff = 'Easy';
        diffBlock.innerHTML = 'Dễ';
        restart();
    }
});

function randomInt(min, max) {
    return Math.floor(Math.random() * (max - min) + min);
}

function randomImg() { // random img ( for food )
    let imgCount = randomInt(0, imagesFood.length);
    img.src = imagesFood[imgCount];
    return img;
}