package reflection_hw_three;

public class SerializationExample {
    @Save
    private int id;
    @Save
    private String text;

    private boolean b;

    public SerializationExample(int id, String text, boolean b) {
        this.id = id;
        this.text = text;
        this.b = b;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isB() {
        return b;
    }

    public void setB(boolean b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "SerializationExample{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", b=" + b +
                '}';
    }

}
