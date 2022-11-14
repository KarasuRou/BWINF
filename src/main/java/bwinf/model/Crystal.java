package bwinf.model;

import org.json.JSONObject;

public class Crystal {

    private final int startPoint_width;
    private final int startPoint_height;
    private final int grow_top;
    private final int grow_right;
    private final int grow_down;
    private final int grow_left;
    private final int spawn_time;
    private boolean finished = false;

    public Crystal(JSONObject object, int width, int height) {
        startPoint_width = Math.min(object.getInt("startPoint_width"), width);
        startPoint_height = Math.min(object.getInt("startPoint_height"), height);
        grow_top = Math.max(object.getInt("grow_top"), 1);
        grow_right = Math.max(object.getInt("grow_right"), 1);
        grow_down = Math.max(object.getInt("grow_down"), 1);
        grow_left = Math.max(object.getInt("grow_left"), 1);
        spawn_time = object.getInt("spawn_time");
    }

    public Pixel[][] grow(Pixel[][] pixels) {
        return null;
    }

    public boolean finished() {
        return finished;
    }

}