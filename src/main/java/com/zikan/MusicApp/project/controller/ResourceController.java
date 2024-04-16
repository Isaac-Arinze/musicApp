package com.zikan.MusicApp.project.controller;

import com.zikan.MusicApp.project.model.Music;
import com.zikan.MusicApp.project.model.MusicResource;
import com.zikan.MusicApp.project.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    private MusicService musicService;

    @GetMapping("/{id}")
    public ResponseEntity<MusicResource> getMusicResource(@PathVariable int id) {

        Music musicToSend = musicService.getMusicById(id).getBody();
        MusicResource musicResource = new MusicResource();
        musicResource.setMusic(musicToSend);

        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MusicAppController.class)
                        .getMusicById(id))
                        .withSelfRel();

        Link delete = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MusicAppController.class)
                        .deleteMusic(id))
                        .withRel("delete");

        Link update = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MusicAppController.class)
                        .updateMusic(id, musicToSend))
                        .withRel("update");

        Link allMusic = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MusicAppController.class)
                        .getAllMusic()).withRel("allMusic");
                         musicResource.add(selfLink, delete, update, allMusic);


                         return new ResponseEntity<>(musicResource, HttpStatus.OK);
    }


}


