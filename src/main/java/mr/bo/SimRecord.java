package mr.bo;

/**
 * Created by LiChuan on 2019/3/18.
 */
public class SimRecord {
    int id;
    double simlarity;

    public SimRecord() {
    }

    public SimRecord(Integer id, Double simlarity) {
        this.id = id;
        this.simlarity = simlarity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSimlarity() {
        return simlarity;
    }

    public void setSimlarity(double simlarity) {
        this.simlarity = simlarity;
    }
}
