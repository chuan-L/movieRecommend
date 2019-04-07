package mr.bo;

import java.util.Date;

/**
 * Created by LiChuan on 2019/3/18.
 */
public class RatingRecord {
    private Integer userId;
    private Integer movieId;
    private Integer rating;

    public RatingRecord(Integer userId, Integer movieId, Integer rating) {
        this.userId = userId;
        this.movieId = movieId;
        this.rating = rating;
    }

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

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "RatingRecord{" +
                "userId=" + userId +
                ", movieId=" + movieId +
                ", rating=" + rating +
                '}';
    }
}
