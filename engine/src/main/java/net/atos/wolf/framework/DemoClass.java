package net.atos.wolf.framework;

@Service
public class DemoClass {

    private String text;

    public DemoClass() {

    }

    public DemoClass(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    @Override
    public String toString() {
        return "DemoClass{" +
                "text='" + text + '\'' +
                '}';
    }
}
