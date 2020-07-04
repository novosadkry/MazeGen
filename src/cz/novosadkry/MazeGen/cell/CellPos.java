package cz.novosadkry.MazeGen.cell;

public class CellPos {
    int x, y;

    public CellPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public CellPos subtract(CellPos p) {
        return CellPos.subtract(this, p);
    }

    public static CellPos subtract(CellPos p1, CellPos p2) {
        return new CellPos(p1.getX() - p2.getX(), p1.getY() - p2.getY());
    }

    public CellPos add(CellPos p) {
        return CellPos.add(this, p);
    }

    public static CellPos add(CellPos p1, CellPos p2) {
        return new CellPos(p1.getX() + p2.getX(), p1.getY() + p2.getY());
    }
}
