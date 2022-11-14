package bwinf.logic;

import bwinf.model.Crystal;
import bwinf.model.Picture;
import bwinf.model.Pixel;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.IOException;

public class Builder {

    public static void main(String[] args) {
        for (int x = 0; x <= 0; x++) {
            try {
                JSONObject[] configs = readConfig(x);

                Picture picture = buildPicture(configs);

                picture.savePicture("out/Picture"+x);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static JSONObject[] readConfig(int x) throws IOException {
        FileReader reader = new FileReader("res/crystals" + x + ".json");
        String test = "";
        while (reader.ready()) {
            test += (char) reader.read();
        }

        JSONArray objects = new JSONArray(test);
        JSONObject[] configs = new JSONObject[objects.length()];
        for (int i = 0; i < objects.length(); i++) {
            configs[i] = objects.getJSONObject(i);
        }
        return configs;
    }

    private static Picture buildPicture(JSONObject[] configs) {
//        final int width = 100, height = 100; // Test
        final int width = 1024, height = 768; // HD
//        final int width = 1920, height = 1080; // Full HD
//        final int width = 2160, height = 3840; // 4K
        Crystal[] crystals = new Crystal[configs.length];

        for (int i = 0; i < configs.length; i++) {
            crystals[i] = new Crystal(configs[i], width, height);
            crystals[i].setId(i);
        }

        int passthroughs = 0;
        Pixel[][] pixels = new Pixel[width][height];
        boolean finished = false;

        while (!finished) {
            finished = true;
            for (Crystal crystal : crystals) {
                pixels = crystal.grow(pixels, passthroughs);
                finished = crystal.finished() && finished;
            }
            passthroughs++;
        }

        Picture picture = new Picture(height, width);
        picture.setPixels(pixels);
        return picture;
//        Picture picture = new Picture(1080, 1920);
//        Picture picture = new Picture(2160, 3840);
//        Pixel[][] pixels = picture.getPixels();
//
//        for (int width = 0; width < picture.getWidth(); width++) {
//            for (int height = 0; height < picture.getHeight(); height++) {
//                pixels[width][height] = new Pixel(100, 100, 100);
//            }
//        }
//        picture.setPixels(pixels);
//        try {
//            picture.savePicture("testPicture");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}