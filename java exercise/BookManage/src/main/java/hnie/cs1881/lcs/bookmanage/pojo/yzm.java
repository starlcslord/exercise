package hnie.cs1881.lcs.bookmanage.pojo;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Table(name = "yzmlist")
public class yzm {
    @Id
    private  Integer id;
    @NotEmpty(message = "验证码不能为空")
    @Length(min = 5 ,max = 5,message = "请输入5位验证码")
    private String yanzhengma;
    private String mytoken;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getYanzhengma() {
        return yanzhengma;
    }

    public void setYanzhengma(String yanzhengma) {
        this.yanzhengma = yanzhengma;
    }

    public String getMytoken() {
        return mytoken;
    }

    public void setMytoken(String mytoken) {
        this.mytoken = mytoken;
    }
}
