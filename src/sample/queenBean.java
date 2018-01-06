package sample;

public class queenBean {
    private int x;
    private int y;
    private int queenSize;
    private int witchqueen = 0;
    private queenBean queenUp;
    private queenBean queendown;
    private queenBean queenRight;
    private queenBean queenLeft;
    private int count = 1000;
    private queenBean nextbean;

    public queenBean(int x, int y, int queenSize) {
        this.x = x;
        this.y = y;
        this.queenSize = queenSize;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void move(){
        if(x != 1){
            queenLeft = new queenBean(x-1,y,queenSize);
        }else {
            queenLeft = null;
        }
        if (x != queenSize){
            queenRight = new queenBean(x+1,y,queenSize);
        }else {
            queenRight = null;
        }
        if(y != 1){
            queenUp = new queenBean(x,y-1,queenSize);
        }else {
            queenUp = null;
        }
        if (y != queenSize){
            queendown = new queenBean(x,y+1,queenSize);
        }else {
            queendown = null;
        }
    }
    public queenBean getNowQueen(int witchqueen){
        queenBean nowQueen = null;
        this.witchqueen = witchqueen;
        switch (witchqueen){
            case 0:
                nowQueen = this;
            break;
            case 1:
                nowQueen = queenUp;
                break;
            case 2:
                nowQueen = queendown;
            break;
            case 3:
                nowQueen = queenRight;
            break;
            case 4:
                nowQueen = queenLeft;
            break;
        }
        return nowQueen;
    }
    public queenBean getNowQueen(){
        queenBean nowQueen = null;
        switch (witchqueen){
            case 0:
                nowQueen = this;
                break;
            case 1:
                nowQueen = queenUp;
                break;
            case 2:
                nowQueen = queendown;
                break;
            case 3:
                nowQueen = queenRight;
                break;
            case 4:
                nowQueen = queenLeft;
                break;
        }
        return nowQueen;
    }


    public int getCount() {
        return count;
    }

    public void setCount(int newcount) {
        if(this.count > newcount){
            this.count = newcount;
            nextbean = getNowQueen(witchqueen);
        }
        witchqueen = 0;
    }

    public void changeNext(){
        this.setX(nextbean.getX());
        this.setY(nextbean.getY());
    }

    public void setWitchqueen(int witchqueen) {
        this.witchqueen = witchqueen;
    }

    @Override
    public String toString() {
        return "queenBean{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public void reternInit(){
        count = 1000;
        witchqueen = 0;
    }
}
