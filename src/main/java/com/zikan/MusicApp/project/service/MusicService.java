package com.zikan.MusicApp.project.service;


import com.zikan.MusicApp.project.Repository.MusicRepository;
import com.zikan.MusicApp.project.model.Music;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.RecursiveTask;

@RequiredArgsConstructor
@Service
public class MusicService {

    private final MusicRepository musicRepository;

    @Cacheable(value = "allMusic")
    public ResponseEntity<List<Music>> getAllMusic(){
        System.out.println("This was reached");
        return new ResponseEntity<>(musicRepository.findAll(), HttpStatus.OK);

    }
    @Cacheable(value = "getById", key = "#id")
    public  ResponseEntity<Music> getMusicById(int id){
        System.out.println("This has been bridged also");
        Music music = musicRepository.findById(id).get();
        return new ResponseEntity<>(music, HttpStatus.OK);
    }
    public ResponseEntity<Music>addNewMusic (Music music){

        Integer id = musicRepository.getMusicId();
        System.out.println(id);

        Music musicWithId = new Music();
                musicWithId.setId(id + 1);
                musicWithId.setTitle(music.getTitle());
                musicWithId.setArtistName(music.getArtistName());
                musicWithId.setAlbumName(music.getAlbumName());
                musicWithId.setMusicDuration(music.getMusicDuration());
                musicWithId.setGenre(music.getGenre());
                musicWithId.setYearOfProd(music.getYearOfProd());

        return new ResponseEntity<>(musicRepository.save(musicWithId), HttpStatus.CREATED);
    }

    @CacheEvict(value = {"allMusic", "getByid"}, key = "#id")
    public ResponseEntity<Music> updateMusic(int id, Music music){
        Music dbMusic = musicRepository.findById(id).get();
        dbMusic.setMusicDuration(music.getMusicDuration());
        dbMusic.setAlbumName(music.getAlbumName());
        dbMusic.setArtistName(music.getArtistName());
        dbMusic.setTitle(music.getTitle());
        dbMusic.setGenre(music.getGenre());
        dbMusic.setYearOfProd(music.getYearOfProd());

        return  new ResponseEntity<>(musicRepository.save(dbMusic), HttpStatus.ACCEPTED);
    }
    public ResponseEntity <Music> deleteMusic(int id){
       Music music = musicRepository.findById(id).get();
       musicRepository.deleteById(id);
       return  new ResponseEntity<>(music, HttpStatus.OK);

    }
}
