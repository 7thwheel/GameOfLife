package seventhwheel.gameoflife;

import java.awt.Point;
import java.util.Stack;
import java.util.Vector;

public class Field {

    public final int maxCol;
    public final int maxRow;
    public Life[][] cells;
    public Stack livings;

    public Field(int maxCol, int maxRow) {
        this.maxCol = maxCol;
        this.maxRow = maxRow;
        cells = new Life[maxCol][maxRow];
        livings = new Stack();
    }

    public void init(Vector nextGenerations) {
        cells = new Life[maxCol][maxRow];
        for (int i = 0; i < nextGenerations.size(); i++) {
            Life life = (Life) nextGenerations.get(i);
            if (setLifeAt(life)) {
                livings.push(life);
            }
        }
    }

    public void next() {
        Lives newGenerations = new Lives();
        while(!livings.isEmpty()) {
            Life life = (Life) livings.pop();

            //dead or alive
            newGenerations.add(saveLife(life));

            //誕生
            for (int i = 0; i < life.neighbors.length; i++) {
                newGenerations.add(giveBirth(life.neighbors[i]));
            }
        }

        init(newGenerations);
    }

    Life giveBirth(Point point) {
        if (getLifeAt(point) == null && countNeighbors(point.x, point.y) == 3) {
            return new Life(point.x, point.y);
        } else {
            return null;
        }
    }

    Life saveLife(Life life) {
        int neighbors = countNeighbors(life.x, life.y);
        if (neighbors != 2 && neighbors != 3) {
            life = null;
        }

        return life;
    }

    /**
     * 指定されたセルに隣接するセルのLifeの数を返します。
     * @param col
     * @param row
     * @return
     */
    int countNeighbors(int col, int row) {
        Life life = new Life(col, row);
        Lives neighbors = new Lives();
        for (int i = 0; i < life.neighbors.length; i++) {
            neighbors.add(getLifeAt(life.neighbors[i]));
        }

        return neighbors.size();
    }

    public Life getLifeAt(Point point) {
        return getLifeAt(point.x, point.y);
    }

    public Life getLifeAt(int col, int row) {
        try {
            return cells[col][row];
        } catch (Exception e) {
            return null;
        }
    }

    boolean setLifeAt(Life life) {
        return setLifeAt(life.x, life.y, life);
    }

    boolean setLifeAt(int col, int row, Life life) {
        try {
            if (null != cells[col][row]) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

        cells[col][row] = life;

        return true;
    }

    class Lives extends Vector {
        public boolean add(Object o) {
            if (null == o) {
                return true;
            }

            return super.add(o);
        }
    }
}
