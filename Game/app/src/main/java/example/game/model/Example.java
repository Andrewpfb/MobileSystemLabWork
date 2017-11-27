package example.game.model;

/**
 * Created by frost on 26.11.2017.
 */

public class Example {
    private String example;
    private int result;

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
    public Example(String example,int result){
        this.example = example;
        this.result = result;
    }
    @Override
    public String toString(){
        return example;// + "=" + result;
    }
}
