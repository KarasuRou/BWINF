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
            for (int column = -grow_left; column <= grow_right; column++) {
                for (int row = -grow_down; row <= grow_top; row++) {
                    //<editor-fold desc="Circle">
                    int temp_width;
                    int temp_height;
                    if (column <= 0) {
                        if (row <= 0) {
                            // LEFT
                            temp_width = Math.min(width + column - row, width);
                            // TOP
                            temp_height = Math.min(height + row, height);
                        } else {
                            // LEFT
                            temp_width = Math.min(width + column + row, width);
                            // BOTTOM
                            temp_height = Math.max(height + row, height);
                        }
                    } else {
                        if (row <= 0) {
                            // RIGHT
                            temp_width = Math.max(width + column + row, width);
                            // TOP
                            temp_height = Math.min(height + row, height);
                        } else {
                            // RIGHT
                            temp_width = Math.max(width + column - row, width);
                            // BOTTOM
                            temp_height = Math.max(height + row, height);
                        }
                    }
                    //</editor-fold>

                    try {
                        if (pixels[temp_width][temp_height] == null) {
                            pixels[temp_width][temp_height] = getPixel(pixels, temp_width, temp_height);
                        }
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
