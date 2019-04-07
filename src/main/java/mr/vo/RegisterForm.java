package mr.vo;

/**
 * Created by LiChuan on 2019/3/21.
 */
public class RegisterForm {
    private String telephone;
    private String password;
    private String nickName;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

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
        return "RegisterForm{" +
                "telephone='" + telephone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
