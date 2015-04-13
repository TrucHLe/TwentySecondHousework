void Baby() {
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

void BabyMeter() {
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

