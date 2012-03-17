package seventhwheel.gameoflife;

import java.util.Vector;

public class Console {

    private int generation;
    private long interval;

    public Console(int generation, long interval) {
        this.generation = generation;
        this.interval =interval;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Vector newGenerations = new Vector();
        newGenerations.add(new Life(6, 4));
        newGenerations.add(new Life(6, 5));
        newGenerations.add(new Life(6, 6));
        newGenerations.add(new Life(5, 5));
        newGenerations.add(new Life(11, 4));
        newGenerations.add(new Life(11, 5));
        newGenerations.add(new Life(11, 6));
        newGenerations.add(new Life(12, 5));
//        newGenerations.add(new Life(5, 5));
//        newGenerations.add(new Life(6, 5));
//        newGenerations.add(new Life(7, 5));
//        newGenerations.add(new Life(5, 6));
//        newGenerations.add(new Life(6, 7));

        Field field = new Field(30, 30);
        field.init(newGenerations);

        Console console = new Console(90, 100);
        console.print(field);

        for (int i = 0; i < console.generation; i++) {
            field.next();
            console.print(field);
        }
    }

    public void print(Field field) {
        for (int i = 0; i < field.maxCol; i++) {
            for (int j = 0; j < field.maxRow; j++) {
                Life life = field.getLifeAt(j, i);
                System.out.print("|");
                if (null == life) {
                    System.out.print("　");
                } else {
                    System.out.print("■");
                }
            }
            System.out.println("|");
        }
        System.out.println();
        try {
            Thread.sleep(interval);
        } catch (InterruptedException e) {
        }
    }

}