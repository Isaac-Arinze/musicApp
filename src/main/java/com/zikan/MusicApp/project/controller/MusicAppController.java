package com.zikan.MusicApp.project.controller;


import com.zikan.MusicApp.project.model.Music;
import com.zikan.MusicApp.project.model.MusicResource;
import com.zikan.MusicApp.project.service.MusicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping ("/music")
public class MusicAppController {

    private final MusicService musicService;

    @GetMapping("/allMusic")
    public ResponseEntity<List<Music>> getAllMusic(){
        return musicService.getAllMusic();
    }
    @GetMapping("/single/{id}")
    public ResponseEntity<Music> getMusicById(@PathVariable int id){
        return musicService.getMusicById(id);
    }
    @PostMapping("/single")
    public ResponseEntity<Music> addNewMusic(@RequestBody @Valid Music music){
        return musicService.addNewMusic(music);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Music> updateMusic (@PathVariable int id, @Valid @RequestBody Music music){
        return musicService.updateMusic(id, music);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Music> deleteMusic (@PathVariable int id){
        return musicService.deleteMusic(id);
    }

}
