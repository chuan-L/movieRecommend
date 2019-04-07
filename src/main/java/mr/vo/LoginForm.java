package mr.vo;

/**
 * Created by LiChuan on 2019/3/21.
 */
public class LoginForm {
    private String telephone;
    private String password;

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginForm{" +
                "telephone='" + telephone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
