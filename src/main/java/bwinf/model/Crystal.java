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
                    pixels[startPoint_width][startPoint_height] = new Pixel(color, color, color, 255, id);
                } else {
                    finished = true;
                }
            } else if (passthroughs >= spawn_time) {
                if (pixels[startPoint_width][startPoint_height] != null && pixels[startPoint_width][startPoint_height].getId() == id) {
                    growFromPoint(pixels, startPoint_width, startPoint_height);
                } else {
                    finished = true;
                }
            }
        }
//        System.out.printf("S:%d; P:%d, F:%b\n", spawn_time, passthroughs, finished);
        return pixels;
    }

    private void growFromPoint(Pixel[][] pixels, int width, int height) {
        if (pixels[width][height] == null) {
            pixels[width][height] = new Pixel(color, color, color, 255, id);
        } else if (pixels[width][height] != null && pixels[width][height].getId() == id) {
            // Top
            // Top-Right
            // Right
            // Bottom-Right
            // Bottom
            // Bottom-Left
            // Top-Left
        }
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
