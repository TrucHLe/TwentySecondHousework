// 20 Second Game Template
// re-design start.gif (start screen) and end.gif (end screen) to give appropriate instructions

import ddf.minim.*;

Minim minim;
AudioPlayer player;

PFont beginFont;
PFont meterFont;

PImage Background;
PImage simplekitchen;
PImage baby;
PImage housewife;
PImage banana;

int begin;               // Time the event/game begins
boolean active;          // Flag for event/game status
boolean lose;            // Flag for "game over"
boolean win;             // Flag for "you win!"
boolean burnt;

float eventGame;

float StoveMeter;
float StoveMeterBar;

float HouseWife;
float xHouseWife;
float yHouseWife;
float xHouseWifeSpeed;
float yHouseWifeSpeed;

float Baby;
float BabyMeter;
float BabyMeterBar;
float xBaby;
float yBaby;
float xBabySpeed;
float yBabySpeed;

float BananaMeter;
float BananaMeterBar;
float xBanana;
float yBanana;

void setup() {
  size(600, 600);

  smooth();

  StoveMeterBar = width/2;

  xHouseWife=width/2;
  yHouseWife=height/2;
  xHouseWifeSpeed=50;
  yHouseWifeSpeed=50;

  xBaby=width/2;
  yBaby=height/2;
  xBabySpeed=5;
  yBabySpeed=5;

  active = false;        // Don't begin with action
  lose = false;          // The event/game has not finished
  win = false;

  beginFont=loadFont("Opificio-48.vlw");
  meterFont=loadFont("Opificio-Bold-48.vlw");
  textAlign(CENTER);

  imageMode(CENTER);
  simplekitchen=loadImage("simplekitchen.jpg");
  baby=loadImage("Baby.png");
  housewife=loadImage("HouseWife.png");
  banana=loadImage("banana.png");
  Background=loadImage("Background.jpg");

  xBanana=random(100, width-100);
  yBanana=random(150, height-100);
  
  minim = new Minim(this);
  player = minim.loadFile("Kitchen.mp3");
  player.loop();
}

void draw() {
  if (active == true) {
    eventGame();
    timer();
  } 
  else {
    if (lose == true && win == false && burnt == false) {
      endScreen();      
    } 
    if (lose == false && win == true && burnt == false) {
      winScreen();
    }
    if (lose == false && win == false && burnt == true) {
      burntScreen();
    }
    if (lose == false && win == false && burnt == false) {
      beginScreen();   
    }
  }
}

void eventGame() {
  image(simplekitchen, width/2, height/2, width, height);

  Baby();
  BabyMeter();
  StoveMeter();
  BananaMeter();

  pushStyle();
  textFont(meterFont);
  textSize(13);
  fill(0);
  text("STOVE", width/2, 16);
  text("BABY", width/2, 32);
  text("CLEANING", width/2, 45);
  popStyle();

  HouseWife();
}

void mousePressed() 
{
  // Begin the event/game when the mouse is clicked
  // and the event/game is not already happening
  if (active == false) {
    active = true;
    begin = millis();
    BabyMeterBar=10;
    BananaMeterBar=10;
    StoveMeterBar=width/2;
  }
}

void timer() 
{
  int curTime = millis();
  if (curTime > begin + 20000 && StoveMeterBar<width-10) {
    active = false;
    lose = true;
  }

  if (curTime > begin + 20000 && BabyMeterBar<width-10) {
    active = false;
    lose = true;
  }

  if (curTime > begin + 20000 && BananaMeterBar<width-10) {
    active = false;
    lose = true;
  }

  if (curTime > begin + 20000 && StoveMeterBar==width-10 && BabyMeterBar==width-10 && BananaMeterBar==width-10) {
    active = false;
    win = true;
  }

  if (StoveMeterBar==10) {
    active = false;
    burnt = true;
  }

  noStroke();
  fill(255);
  rect(0, height-5, width, 5);
  fill(0);
  rect(0, height-5, (curTime-begin)/33, 5);
}

// Display when the game/event begins
void beginScreen() {
  image(Background, width/2, height/2, width, height);
  textFont(beginFont);
  fill(0);
  pushStyle();
  textSize(50);
  text("Click to begin!", width/2, 400);
  popStyle();

  pushStyle();
  textSize(30);
  text("Use arrow keys to:", width/2, 150);
  text("You have 20 seconds", width/2, 360);
  pushStyle();

  pushStyle();
  textSize(25);
  textAlign(LEFT);
  text("- Cook", 180, 200);
  text("- Feed the baby", 180, 230);
  text("- Pick up banana peels", 180, 260);
  popStyle();
}

// Displays when the 20 seconds are over
void endScreen() {
  image(Background, width/2, height/2, width, height);
  textFont(beginFont);
  textSize(50);
  fill(0);
  text("You ran out of time!", width/2, height/2);
}

// Display when win the game
void winScreen() {
  image(Background, width/2, height/2, width, height);
  textFont(beginFont);
  pushStyle();
  textSize(60);
  text("You Won ! ! !", width/2, height/2);
  popStyle();
}

void burntScreen() {
  image(Background, width/2, height/2, width, height);
  textFont(beginFont);
  text("All food is burnt", width/2, 250);
  text("because you left the stove", width/2, 300);
  text("for too long :-(", width/2, 350);
}

