void HouseWife() {
  image(housewife, xHouseWife, yHouseWife, 120, 120);
}

void keyPressed() {

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

