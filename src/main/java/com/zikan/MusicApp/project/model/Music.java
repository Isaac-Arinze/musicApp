package com.zikan.MusicApp.project.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Value;


@AllArgsConstructor
@Getter
@RequiredArgsConstructor
@Entity
@Table(name = "music", uniqueConstraints =
@UniqueConstraint(columnNames = {"title", "year_of_prod"})) //LoveMe 2024
//@Data

public class Music {

    @Id
//    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
   // @Column (unique = true)
    private String title;
    @NotBlank
    @Length(min = 3, max = 25)
    private String artistName;
    @NotBlank
    @Length (min = 6, max = 25)
    private String albumName;
    @Range(min = 1, max = 6)
    private double musicDuration;

    @NotBlank
    @Length(min = 3)
    private String genre;
    @Min(1900)
    @Max(2024)
    private double yearOfProd;

//    @Value("${my_name}")
//    String name;

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public void setMusicDuration(double musicDuration) {
        this.musicDuration = musicDuration;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setYearOfProd(double yearOfProd) {
        this.yearOfProd = yearOfProd;
    }

    @Override
    public String toString() {
        return "Music{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", artistName='" + artistName + '\'' +
                ", albumName='" + albumName + '\'' +
                ", musicDuration=" + musicDuration +
                ", genre='" + genre + '\'' +
                ", yearOfProd=" + yearOfProd +
                '}';
    }
}
