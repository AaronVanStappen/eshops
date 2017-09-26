package be.vdab.entiteiten;

public class Customer extends User {
    private int id;
    private String name;
    private String first_name;
    private String email;
    private String delivAd;

    public Customer(String username, String pasword, int id, String name, String first_name, String email, String delivAd) {
        super(username, pasword);
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

    public void setName(String name) {
        this.name = name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDelivAd() {
        return delivAd;
    }

    public void setDelivAd(String delivAd) {
        this.delivAd = delivAd;
    }
}
