package com.sparta.springblog.controller;

import com.sparta.springblog.dto.PostRequestDto;
import com.sparta.springblog.dto.PostResponseDto;
import com.sparta.springblog.entity.Post;
import com.sparta.springblog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/")
    public ModelAndView home(){
        return new ModelAndView("index");
    }

    @PostMapping("/api/posts")
    public Post createPost(@RequestBody PostRequestDto requestDto){
        return postService.createPost(requestDto);
    }

    //전체조회
    @GetMapping("/api/posts")
    public List<Post> getPosts(){
        return postService.getPosts();
    }

    //아이디값으로 하나만 조회
    @GetMapping("/api/posts/{id}")
    public PostResponseDto getPost(@PathVariable Long id){
        return postService.getPost(id);
    }

    //수정
    @PutMapping("/api/posts/{id}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto){
        return postService.update(id, requestDto);
    }

    //삭제
    @DeleteMapping("/api/posts/{id}")
    public String deletePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto){
        return postService.deletePost(id, requestDto);
    }
}
