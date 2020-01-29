package com.example.demo_tebbed;

public class Iteam {
    int id;
    String name;
    String pgflag;

    public Iteam(int id, String name, String pgflag) {
        this.id = id;
        this.name = name;
        this.pgflag = pgflag;
    }

    public Iteam(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getPgflag() {
        return pgflag;
    }

    public void setPgflag(String pgflag) {
        this.pgflag = pgflag;
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
}
