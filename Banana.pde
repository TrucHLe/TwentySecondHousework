void BananaMeter() {
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

