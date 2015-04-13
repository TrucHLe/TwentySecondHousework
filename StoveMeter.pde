void StoveMeter() {

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

