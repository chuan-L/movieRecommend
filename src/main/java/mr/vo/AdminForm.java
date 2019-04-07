package mr.vo;

/**
 * Created by LiChuan on 2019/3/25.
 */
public class AdminForm {
    private String name;
    private String password;

    @Override
    public String toString() {
        return "AdminForm{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
