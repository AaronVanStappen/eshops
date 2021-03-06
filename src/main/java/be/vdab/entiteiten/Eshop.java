package be.vdab.entiteiten;

public class Eshop {
    private int id;
    private String info;
    private String address;

    public Eshop(int id, String info, String address) {
        this.setId(id);
        this.setInfo(info);
        this.setAddress(address);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    private void setInfo(String info) {
        this.info = info;
    }

    public String getAddress() {
        return address;
    }

    private void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return id + ", " + info + ", " + address;
    }
}
