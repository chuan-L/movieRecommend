package mr.vo;

import java.util.Date;

/**
 * Created by LiChuan on 2019/3/24.
 */
public class MovieVo {
    private Integer movieId;
    private String name;
    private String alias;
    private String nation;
    private String language;
    private Integer length;
    private String director;
    private String actor;
    private String screenwriter;
    private Date releaseDate;
    private String introduce;
    private String typeName;//
    private String posterImgPath;//

    private String IMDb;
    private Integer ratingPeople;
    private Integer star1;
    private Integer star2;
    private Integer star3;
    private Integer star4;
    private Integer star5;
    private Float ratingAvg;

    public Float getRatingAvg() {
        return ratingAvg;
    }

    public void setRatingAvg(Float ratingAvg) {
        this.ratingAvg = ratingAvg;
    }

    public String getIMDb() {
        return IMDb;
    }

    public void setIMDb(String IMDb) {
        this.IMDb = IMDb;
    }

    public Integer getRatingPeople() {
        return ratingPeople;
    }

    public void setRatingPeople(Integer ratingPeople) {
        this.ratingPeople = ratingPeople;
    }

    public Integer getStar1() {
        return star1;
    }

    public void setStar1(Integer star1) {
        this.star1 = star1;
    }

    public Integer getStar2() {
        return star2;
    }

    public void setStar2(Integer star2) {
        this.star2 = star2;
    }

    public Integer getStar3() {
        return star3;
    }

    public void setStar3(Integer star3) {
        this.star3 = star3;
    }

    public Integer getStar4() {
        return star4;
    }

    public void setStar4(Integer star4) {
        this.star4 = star4;
    }

    public Integer getStar5() {
        return star5;
    }

    public void setStar5(Integer star5) {
        this.star5 = star5;
    }

    @Override
    public String toString() {
        return "MovieVo{" +
                "movieId=" + movieId +
                ", name='" + name + '\'' +
                ", alias='" + alias + '\'' +
                ", nation='" + nation + '\'' +
                ", language='" + language + '\'' +
                ", length=" + length +
                ", director='" + director + '\'' +
                ", actor='" + actor + '\'' +
                ", screenwriter='" + screenwriter + '\'' +
                ", releaseDate=" + releaseDate +
                ", introduce='" + introduce + '\'' +
                ", typeName='" + typeName + '\'' +
                ", posterImgPath='" + posterImgPath + '\'' +
                '}';
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getScreenwriter() {
        return screenwriter;
    }

    public void setScreenwriter(String screenwriter) {
        this.screenwriter = screenwriter;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getPosterImgPath() {
        return posterImgPath;
    }

    public void setPosterImgPath(String posterImgPath) {
        this.posterImgPath = posterImgPath;
    }
}
