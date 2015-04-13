import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import ddf.minim.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class TwentySecondHousework extends PApplet {

// 20 Second Game Template
// re-design start.gif (start screen) and end.gif (end screen) to give appropriate instructions



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

public void setup() {
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

public void draw() {
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

public void eventGame() {
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

public void mousePressed() 
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

public void timer() 
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
public void beginScreen() {
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
public void endScreen() {
  image(Background, width/2, height/2, width, height);
  textFont(beginFont);
  textSize(50);
  fill(0);
  text("You ran out of time!", width/2, height/2);
}

// Display when win the game
public void winScreen() {
  image(Background, width/2, height/2, width, height);
  textFont(beginFont);
  pushStyle();
  textSize(60);
  text("You Won ! ! !", width/2, height/2);
  popStyle();
}

public void burntScreen() {
  image(Background, width/2, height/2, width, height);
  textFont(beginFont);
  text("All food is burnt", width/2, 250);
  text("because you left the stove", width/2, 300);
  text("for too long :-(", width/2, 350);
}

public void Baby() {
  pushStyle();
  image(baby, xBaby, yBaby, 60, 60);
  xBaby=xBaby+xBabySpeed;
  yBaby=yBaby+yBabySpeed;

  if (xBaby>width-30||xBaby<130) {
    xBabySpeed=xBabySpeed*-1;
  }
  if (yBaby>height-40||yBaby<80) {
    yBabySpeed=yBabySpeed*-1;
  }
  popStyle();
}

public void BabyMeter() {
  pushStyle();
  stroke(242, 199, 208);
  strokeWeight(12);
  line(10, 24, width-10, 24);
  popStyle();

  pushStyle();
  stroke(246, 238, 34);
  strokeWeight(7);
  line(10, 24, BabyMeterBar, 24);
  popStyle();

  if (xHouseWife>=xBaby-50 && xHouseWife<=xBaby+50 && yHouseWife>=yBaby-50 && yHouseWife<=yBaby+50) {
    BabyMeterBar+=15;
  }
  else {
    BabyMeterBar+=0;
  }

  if (BabyMeterBar<10) {
    BabyMeterBar=10;
  }
  if (BabyMeterBar>width-10) {
    BabyMeterBar=width-10;
  }
}

public void BananaMeter() {
  image(banana, xBanana, yBanana, 70, 70);

  pushStyle();
  stroke(186, 161, 154);
  strokeWeight(12);
  line(10, 38, width-10, 38);
  popStyle();

  pushStyle();
  stroke(246, 238, 34);
  strokeWeight(7);
  line(10, 38, BananaMeterBar, 38);
  popStyle();

  if (xHouseWife>=xBanana-30 && xHouseWife<=xBanana+30 && yHouseWife>=yBanana-30 && yHouseWife<=yBanana+30) {
    xBanana=random(100, width-100);
    yBanana=random(150, height-100);
    BananaMeterBar+=100;
  }

  else {
    BananaMeterBar+=0;
  }

  if (BananaMeterBar<10) {
    BananaMeterBar=10;
  }
  if (BananaMeterBar>width-10) {
    BananaMeterBar=width-10;
  }
}

public void HouseWife() {
  image(housewife, xHouseWife, yHouseWife, 120, 120);
}

public void keyPressed() {

  if (xHouseWife>width-100) {
    xHouseWife=width-100;
  }
  if (xHouseWife<100) {
    xHouseWife=100;
  }

  if (yHouseWife>height-100) {
    yHouseWife=height-100;
  }
  if (yHouseWife<150) {
    yHouseWife=150;
  }

  if (key==CODED) {
    if (keyCode==LEFT) {
      xHouseWife=xHouseWife-xHouseWifeSpeed;
    }
    if (keyCode==RIGHT) {
      xHouseWife=xHouseWife+xHouseWifeSpeed;
    }
    if (keyCode==UP) {
      yHouseWife=yHouseWife-yHouseWifeSpeed;
    }
    if (keyCode==DOWN) {
      yHouseWife=yHouseWife+yHouseWifeSpeed;
    }
  }
}

public void StoveMeter() {

  //Pink border of Stove
  pushStyle();
  strokeWeight(5);
  stroke(255, 20, 147);
  noFill();
  rect(0, 310, 95, 95);
  popStyle();

  pushStyle();
  stroke(204, 139, 133);
  strokeWeight(12);
  line(10, 10, width-10, 10);
  popStyle();

  pushStyle();
  stroke(246, 238, 34);
  strokeWeight(7);
  line(10, 10, StoveMeterBar, 10);
  popStyle();

  if (xHouseWife>=0 && xHouseWife<=95 && yHouseWife>=280 && yHouseWife<=405) {
    StoveMeterBar+=20;
  }
  else {
    StoveMeterBar-=1;
  }

  if (StoveMeterBar<10) {
    StoveMeterBar=10;
  }
  if (StoveMeterBar>width-10) {
    StoveMeterBar=width-10;
  }
}

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--full-screen", "--bgcolor=#666666", "--stop-color=#cccccc", "TwentySecondHousework" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
