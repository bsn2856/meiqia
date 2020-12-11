package com.meiqia.user.vo;

public class ByRidFindDockVO {
    private int id;
    private String name;
    private Integer price;
    private String rName;
    private String lName;
    private String adept;

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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getAdept() {
        return adept;
    }

    public void setAdept(String adept) {
        this.adept = adept;
    }

    @Override
    public String toString() {
        return "ByRidFindDockVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", rName='" + rName + '\'' +
                ", lName='" + lName + '\'' +
                ", adept='" + adept + '\'' +
                '}';
    }

    public ByRidFindDockVO(int id, String name, Integer price, String rName, String lName, String adept) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rName = rName;
        this.lName = lName;
        this.adept = adept;
    }
}
