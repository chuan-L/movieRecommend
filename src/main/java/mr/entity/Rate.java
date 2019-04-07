package mr.entity;

import java.util.Date;

/**
 * Created by LiChuan on 2019/2/20.
 * 测试
 */
public class Rate {

    private Integer userId;
    private Integer movieId;
    private Integer rating;
    private Date createTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }



    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "userId=" + userId +
                ", movieId=" + movieId +
                ", rating=" + rating +
                ", createTime=" + createTime +
                '}';
    }
}
