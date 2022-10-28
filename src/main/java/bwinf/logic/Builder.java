package bwinf.logic;

import bwinf.model.Picture;
import bwinf.model.Pixel;

import java.io.IOException;

public class Builder {

    public static void main(String[] args) {
//        Picture picture = new Picture(1080, 1920); // Full HD
        Picture picture = new Picture(2160, 3840); // 4K
        Pixel[][] pixels = picture.getPixels();

        for (int width = 0; width < picture.getWidth(); width++) {
            for (int height = 0; height < picture.getHeight(); height++) {
                pixels[width][height] = new Pixel(100, 100, 100);
            }
        }
        picture.setPixels(pixels);
        try {
            picture.savePicture("testPicture");
        } catch (IOException e) {
            e.printStackTrace();
        }
//        readConfig();
//
//        buildPicture(config);
//
//        savePicture(picture);
    }
}