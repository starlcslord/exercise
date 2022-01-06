package hnie.cs1881.lcs.bookmanage.pojo;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "book1")
public class book1 {
    @Id
    private Integer id;
    private String name;
    private String authon;
    private String tips;
    private Integer number;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthon() {
        return authon;
    }

    public void setAuthon(String authon) {
        this.authon = authon;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }
}
