package seventhwheel.gameoflife;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Stack;

public class Field {

    public final int maxCol;
    public final int maxRow;
    public Life[][] cells;
    public Stack<Life> livings = new Stack<Life>();

    public Field(int maxCol, int maxRow) {
        this.maxCol = maxCol;
        this.maxRow = maxRow;
        cells = new Life[maxCol][maxRow];
    }

    public void init(ArrayList<Life> nextGenerations) {
        cells = new Life[maxCol][maxRow];
        for (int i = 0; i < nextGenerations.size(); i++) {
            Life life = nextGenerations.get(i);
            if (setLifeAt(life)) {
                livings.push(life);
            }
        }
    }

    public void next() {
        Lives newGenerations = new Lives();
        while(!livings.isEmpty()) {
            Life life = livings.pop();

            //add a surviver to next-gen
            newGenerations.add(regulatePopulationDensity(life));

            //add newborns to next-gen
            for (int i = 0; i < life.neighbors.length; i++) {
                newGenerations.add(reproduct(life.neighbors[i]));
            }
        }

        init(newGenerations);
    }

    Life reproduct(Point point) {
        if (getLifeAt(point) == null && countNeighbors(point.x, point.y) == 3) {
            return new Life(point.x, point.y);
        } else {
            return null;
        }
    }

    Life regulatePopulationDensity(Life life) {
        int neighbors = countNeighbors(life.x, life.y);
        if (neighbors != 2 && neighbors != 3) {
            life = null;
        }

        return life;
    }

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
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * This method return true if life was set to cell.
     * If specified cell is not empty, return false.
     */
    boolean setLifeAt(Life life) {
        if (life.x < 0 || maxCol < life.x || life.y < 0 || maxRow < life.y || null != cells[life.x][life.y]) {
            return false;
        }

        cells[life.x][life.y] = life;
        return true;
    }
    
    class Lives extends ArrayList<Life> {
        public boolean add(Life life) {
            if (null == life) {
                return true;
            }

            return super.add(life);
        }
    }
}
