package example.materialdesign2;

/**
 * Created by frost on 25.11.2017.
 */

public class Phone {
    private String name;
    private String company;
    private int image;

    public Phone(String name, String company, int image){

        this.name=name;
        this.company = company;
        this.image = image;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getImage() {
        return this.image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
