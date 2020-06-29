package com.example.bookmarkmyshow.bookmarks;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.example.bookmarkmyshow.model.Movie;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "tmdbBookmarks",indices = {@Index(value = {"imageUrl"},unique = true)})
public class BookmarkMovie {

    @PrimaryKey(autoGenerate = true)
    private int uniqueid;

    @ColumnInfo(name = "imageUrl")
    private String imageUrl;
    //TODO:name
//    private String releaseDate;
//    private String name;


    @ColumnInfo(name = "poster_path")
    private String posterPath;
    @ColumnInfo(name = "adult")
    private boolean adult;
    @ColumnInfo(name = "overview")
    private String overview;
    @ColumnInfo(name = "release_date")
    private String releaseDate;
//    @ColumnInfo(name = "genre_ids")
//    private List<Integer> genreIds = new ArrayList<Integer>();
    @ColumnInfo(name = "id")
    private Integer id;
    @ColumnInfo(name = "original_title")
    private String originalTitle;
    @ColumnInfo(name = "original_language")
    private String originalLanguage;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "backdrop_path")
    private String backdropPath;
    @ColumnInfo(name = "popularity")
    private Double popularity;
    @ColumnInfo(name = "vote_count")
    private Integer voteCount;
    @ColumnInfo(name = "video")
    private Boolean video;
    @ColumnInfo(name = "vote_average")
    private Double voteAverage;




    public BookmarkMovie(int uniqueid, String imageUrl, String title, String releaseDate) {
        this.imageUrl = imageUrl;
        this.releaseDate = releaseDate;
        this.title = title;
        this.uniqueid = uniqueid;
    }

    public BookmarkMovie(){}

    public int getUniqueid() {
        return uniqueid;
    }

    public void setUniqueid(int uniqueid) {
        this.uniqueid = uniqueid;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
//
//    public List<Integer> getGenreIds() {
//        return genreIds;
//    }
//
//    public void setGenreIds(List<Integer> genreIds) {
//        this.genreIds = genreIds;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }
}
