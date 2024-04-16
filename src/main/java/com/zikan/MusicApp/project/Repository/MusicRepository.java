package com.zikan.MusicApp.project.Repository;

import com.zikan.MusicApp.project.model.Music;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicRepository extends JpaRepository<Music, Integer> {


    @Query("SELECT MAX(id) FROM Music")
    Integer getMusicId();
    Music findByArtistName(String artistName);
    Music findByTitle (String title);
    Music findByGenre(String genre);
    Music findByYearOfProd (double year);
    Music findByAlbumName (String album);
}
