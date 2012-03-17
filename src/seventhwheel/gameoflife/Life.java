package seventhwheel.gameoflife;

import java.awt.Point;

public class Life {

    public int x = 0;
    public int y = 0;
    public Point point;
    public Point tl;
    public Point tc;
    public Point tr;
    public Point l;
    public Point r;
    public Point bl;
    public Point bc;
    public Point br;
    public Point[] neighbors;

    public Life(int x, int y) {
        this.x = x;
        this.y = y;
        point = new Point(x, y);
        tl = new Point(x - 1, y - 1);
        tc = new Point(x, y - 1);
        tr = new Point(x + 1, y - 1);
        l = new Point(x - 1, y);
        r = new Point(x + 1, y);
        bl = new Point(x - 1, y + 1);
        bc = new Point(x, y + 1);
        br = new Point(x + 1, y + 1);
        neighbors = new Point[]{tl, tc, tr, l, r, bl, bc, br};
    }
}

