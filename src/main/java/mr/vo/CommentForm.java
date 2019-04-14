package mr.vo;

/**
 * Created by LiChuan on 2019/4/9.
 */
public class CommentForm {
    private Integer userId;
    private Integer movieId;
    private String content;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CommentForm{" +
                "userId=" + userId +
                ", movieId=" + movieId +
                ", content='" + content + '\'' +
                '}';
    }
}
