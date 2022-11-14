package bwinf.model;

import org.json.JSONObject;

public class Crystal {

    private int id = -1;
    private final int startPoint_width;
    private final int startPoint_height;
    private final int grow_top;
    private final int grow_right;
    private final int grow_down;
    private final int grow_left;
    private final int color;
    private final int spawn_time;
    private boolean finished = false;

    public Crystal(JSONObject object, int width, int height) {
        startPoint_width = Math.min(object.getInt("startPoint_width"), width - 1);
        startPoint_height = Math.min(object.getInt("startPoint_height"), height - 1);
        grow_top = Math.max(object.getInt("grow_top"), 1);
        grow_right = Math.max(object.getInt("grow_right"), 1);
        grow_down = Math.max(object.getInt("grow_down"), 1);
        grow_left = Math.max(object.getInt("grow_left"), 1);
        spawn_time = object.getInt("spawn_time");
        color = object.getInt("color");
    }

    public Pixel[][] grow(Pixel[][] pixels, int passthroughs) {
        if (id != -1 && !finished) {
            if (passthroughs == spawn_time) {
                if (pixels[startPoint_width][startPoint_height] == null) {
                    pixels[startPoint_width][startPoint_height] = getPixel(pixels, startPoint_width, startPoint_height);
                } else {
                    finished = true;
                }
            } else if (passthroughs == 60) {
                finished = true;
            } else if (passthroughs >= spawn_time) {
                if (pixels[startPoint_width][startPoint_height] != null && pixels[startPoint_width][startPoint_height].getId() == id) {
                    pixels = growFromPoint(pixels, startPoint_width, startPoint_height);
                } else {
                    finished = true;
                }
            }
        }
//        System.out.printf("S:%d; P:%d, F:%b\n", spawn_time, passthroughs, finished);
        return pixels;
    }

    private Pixel[][] growFromPoint(Pixel[][] pixels, int width, int height) {
        if (width < 0 || height < 0 || pixels.length <= width || pixels[0].length <= height)
            return pixels;

        if (pixels[width][height] == null) {
            pixels[width][height] = getPixel(pixels, width, height);
        } else if (pixels[width][height] != null && pixels[width][height].getId() == id) {
            // Top
            for (int grow = 1; grow <= grow_top; grow++) {
                try {
                    pixels[width][height - grow] = getPixel(pixels, width, height - grow);
                } catch (Exception ignore) {}
            }
            // Bottom
            for (int grow = 1; grow <= grow_down; grow++) {
                try {
                    pixels[width][height + grow] = getPixel(pixels, width, height + grow);
                } catch (Exception ignore) {}
            }
            // Right
            for (int grow = 1; grow <= grow_right; grow++) {
                try {
                    pixels[width + grow][height] = getPixel(pixels, width + grow, height);
                } catch (Exception ignore) {}
            }
            // Left
            for (int grow = 1; grow <= grow_left; grow++) {
                try {
                    pixels[width - grow][height] = getPixel(pixels, width - grow, height);
                } catch (Exception ignore) {}
            }
            // Top-Left
            for (int grow_top = 0; grow_top < this.grow_top; grow_top++) {
                for (int grow_left = 1; grow_left <= this.grow_left; grow_left++) {
                    int min = Math.min(width - grow_left + grow_top, width);
                    try {
                        pixels[min][height - grow_top] = getPixel(pixels, min, height - grow_top);
                    } catch (Exception ignore) {}
                }
            }
            // Top-Right
            for (int grow_top = 0; grow_top < this.grow_top; grow_top++) {
                for (int grow_right = 1; grow_right <= this.grow_right; grow_right++) {
                    int max = Math.max(width + grow_right - grow_top,width);
                    try {
                        pixels[max][height - grow_top] = getPixel(pixels, max, height - grow_top);
                    } catch (Exception ignore) {}
                }
            }
            // Bottom-Right
            for (int grow_down = 0; grow_down < this.grow_down; grow_down++) {
                for (int grow_right = 1; grow_right <= this.grow_right; grow_right++) {
                    int max = Math.max(width + grow_right - grow_down, width);
                    try {
                        pixels[max][height + grow_down] = getPixel(pixels, max, height + grow_down);
                    } catch (Exception ignore) {}
                }
            }
            // Bottom-Left
            for (int grow_down = 0; grow_down < this.grow_down; grow_down++) {
                for (int grow_left = 1; grow_left <= this.grow_left; grow_left++) {
                    int min = Math.min(width - grow_left + grow_down, width);
                    try {
                        pixels[min][height + grow_down] = getPixel(pixels, min, height + grow_down);
                    } catch (Exception ignore) {}
                }
            }
        }
        return pixels;
    }

    private Pixel getPixel(Pixel[][] pixels, int width, int height) {
        if (width < 0 || height < 0 || pixels.length <= width || pixels[0].length <= height)
            throw new IndexOutOfBoundsException("Not Placeable");

        if (pixels[width][height] == null) {
            return new Pixel(color, color, color, 255, id);
        }
        throw new IndexOutOfBoundsException("Not Placeable");
    }

    public boolean finished() {
        return finished;
    }

    //<editor-fold desc="Setter">
    public void setId(int id) {
        if (this.id != -1)
            return;

        this.id = id;
    }
    //</editor-fold>
}
