package example.game.utils;

import example.game.model.Example;

/**
 * Created by frost on 26.11.2017.
 */

public class ExampleGenerator {
    private static int a;
    private static int b;
    private static int c;
    private static int selector;
    private static String example;
    private static int result;
    public static Example GetExample(){
        a = (int)(Math.random()*11);
        b = (int)(Math.random()*11);
        selector = (int)(Math.random()*3);
        switch (selector){
            case 0:
                result = a + b;
                example = a+"+"+b;
                break;
            case 1:
                result = a-b;
                example = a+"-"+b;
                break;
            case 2:
                result = a*b;
                example = a+"*"+b;
                break;
        }
        return new Example(example,result);
    }
}
