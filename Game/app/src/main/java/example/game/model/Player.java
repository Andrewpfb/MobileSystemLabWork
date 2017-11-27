package example.game.model;

/**
 * Created by frost on 26.11.2017.
 */

public class Player {
    private String name;
    private int count;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    public Player (String name,int count){
        this.name = name;
        this.count = count;
    }
    @Override
    public String toString(){
        return name + "  :  "+count;
    }
}
