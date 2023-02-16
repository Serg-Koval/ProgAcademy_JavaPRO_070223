package sample;

public class Customer {
    private String name;
    private String address;
    private int age;
    private int category;
    private String lastName;

    public Customer(String name, String address, int age, int category, String lastName) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.category = category;
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", category=" + category +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
