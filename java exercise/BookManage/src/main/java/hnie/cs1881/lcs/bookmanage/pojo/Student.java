package hnie.cs1881.lcs.bookmanage.pojo;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Table(name = "student")
public class Student {
    @Id
    @NotEmpty(message = "学号不能为空")
    @Length(min = 4,max = 15,message = "用户名在4到15位字符之间")
    private String userid;
    @Email
    private String email;
    @NotEmpty(message = "密码不能为空")
    @Length(min = 4,max = 15,message = "密码长度在5位在16字符之间")
    private String password;

    private String isroot;
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIsroot() {
        return isroot;
    }

    public void setIsroot(String isroot) {
        this.isroot = isroot;
    }
}
