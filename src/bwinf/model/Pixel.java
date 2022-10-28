package bwinf.model;

public class Pixel {

    //<editor-fold desc="Variables">
    private int red;
    private int green;
    private int blue;
    private int alpha; // Transparency
    //</editor-fold>

    //<editor-fold desc="Constructors">
    public Pixel() {
        red = 0;
        green = 0;
        blue = 0;
        alpha = 255;
    }

    public Pixel(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        alpha = 255;
    }

    public Pixel(int red, int green, int blue, int alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }
    //</editor-fold>

    //<editor-fold desc="Getter and Setter">
    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }
    //</editor-fold>

    @Override
    public String toString(){
        return String.format("RGBa: %d|%d|%d|%d", red, green, blue, alpha);
    }
}
