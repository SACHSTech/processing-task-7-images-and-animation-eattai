import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {
  PImage imgBackground;
  PImage imgAsteroid;
  PImage imgUFO;

  float fltAsteroidX = 0;
  float fltAsteroidY = 0;

  float fltUFOX = -100;
  float fltUFOY = 300;
  float velUFOX = 2; // Speed of UFO

  float velAsteroidX = 2; // Speed of asteroid
  float velAsteroidY = 2; // Speed of asteroid

  public void settings() {
    size(600, 600);
  }

  // Loading all the images
  public void setup() {
    imgBackground = loadImage("space.jpg");

    imgAsteroid = loadImage("asteroid.png");
    imgAsteroid.resize(imgAsteroid.width/4, imgAsteroid.height/4);

    imgUFO = loadImage("ufo.png");
    imgUFO.resize(imgUFO.width/4, imgUFO.height/4);
  }

  public void draw() {
    background(128);

    // Setting the position of the image so it shows more galaxy
    image(imgBackground, -1000, 0);

    // Move and show the asteroid
    image(imgAsteroid, fltAsteroidX, fltAsteroidY);
    fltAsteroidX += velAsteroidX;
    fltAsteroidY += velAsteroidY;

    // Collision detection for the asteroid
    if (fltAsteroidX + imgAsteroid.width >= width || fltAsteroidX <= 0) {
      velAsteroidX *= -1; // Reverse the horizontal direction if the asteroid hits the edges
    }
    if (fltAsteroidY + imgAsteroid.height >= height || fltAsteroidY <= 0) {
      velAsteroidY *= -1; 
    }

    // Move the UFO in a sine wave 
    float sinValue = sin((float) (frameCount * 0.03)); // Adjust the frequency of the wave by changing the multiplier
    float sinOffset = map(sinValue, -1, 1, -100, 100); // Adjust the amplitude of the wave

    fltUFOY = 300 + sinOffset; // Set the UFO's Y position based on the sine wave

    image(imgUFO, fltUFOX, fltUFOY);
    fltUFOX += velUFOX;

    // Collision detection with window boundaries for the UFO
    if (fltUFOX <= 0) {
      fltUFOX = 0; // Set UFO's X position to 0 if it reaches the left window edge
      velUFOX *= -1; // Reverse the horizontal direction
    } else if (fltUFOX + imgUFO.width >= width) {
      fltUFOX = width - imgUFO.width; // Set UFO's X position to the window width - UFO's width
      velUFOX *= -1; // Reverse the horizontal direction
    }
  }
}
