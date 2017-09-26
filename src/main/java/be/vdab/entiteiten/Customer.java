package be.vdab.entiteiten;

public class Customer extends User {
    private int id;
    private String name;
    private String first_name;
    private String email;
    private String delivAd;

    public Customer(int id, String username, String pasword, String name, String first_name, String email, String delivAd) {
        super(id, username, pasword);
        this.setId(id);
        this.setName(name);
        this.setFirst_name(first_name);
        this.setEmail(email);
        this.setDelivAd(delivAd);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getFirst_name() {
        return first_name;
    }

    private void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getEmail() {
        return email;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    public String getDelivAd() {
        return delivAd;
    }

    private void setDelivAd(String delivAd) {
        this.delivAd = delivAd;
    }

    @Override
    public String toString() {
        return  super.toString() + ", " + name + ", " + first_name + ", " + email + ", " + delivAd;
    }
}
