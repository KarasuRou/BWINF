package bwinf.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Picture {

    //<editor-fold desc="Variables">
    private final int width;
    private final int height;
    private Pixel[][] pixels;
    //</editor-fold>

    //<editor-fold desc="Constructor">
    public Picture(int height, int width) {
        this.height = height;
        this.width = width;
        pixels = new Pixel[width][height];
        for (int i = 0; i < width; i++)
            for (int y = 0; y < height; y++)
                pixels[i][y] = new Pixel();
    }
    //</editor-fold>

    //<editor-fold desc="Setter and Getter">
    //<editor-fold desc="Width and Height">
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    //</editor-fold>

    //<editor-fold desc="Pixel">
    public void setPixel(int lane, int column, Pixel pixel) {
        this.pixels[lane][column] = pixel;
    }
    public void setPixelsInLane(int lane, Pixel[] pixels) {
        this.pixels[lane] = pixels;
    }

    public void setPixels(Pixel[][] pixels) {
        this.pixels = pixels;
    }

    public Pixel[][] getPixels() {
        return pixels;
    }

    public Pixel[] getPixelLane(int lane) {
        return pixels[lane];
    }

    public Pixel getPixel(int lane, int column) {
        return pixels[lane][column];
    }
    //</editor-fold>

    public void savePicture(String path) throws IOException {
        BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int width = 0; width < this.width; width++) {
            for (int height = 0; height < this.height; height++) {
                int red = getPixel(width, height).getRed();
                int green = getPixel(width, height).getGreen();
                int blue = getPixel(width, height).getBlue();
                int alpha = getPixel(width, height).getAlpha();

                Color color = new Color(red, green, blue, alpha);
                output.setRGB(width, height, color.getRGB());
            }
        }

        ImageIO.write(output, "jpeg", new File(path + ".jpeg"));
    }
    //</editor-fold>

}
