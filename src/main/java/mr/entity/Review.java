package mr.entity;

import java.util.Date;

/**
 * Created by LiChuan on 2019/4/8.
 */
public class Review {
    private Integer reviewId;
    private Integer userId;
    private Integer topicId;
    private String content;
    private Date createTime;
    private Integer likeNumber;
    private Integer dislikeNumber;
    private Integer commentNumber;

    public Integer getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(Integer commentNumber) {
        this.commentNumber = commentNumber;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(Integer likeNumber) {
        this.likeNumber = likeNumber;
    }

    public Integer getDislikeNumber() {
        return dislikeNumber;
    }

    public void setDislikeNumber(Integer dislikeNumber) {
        this.dislikeNumber = dislikeNumber;
    }


}
