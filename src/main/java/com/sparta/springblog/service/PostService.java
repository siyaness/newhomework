package com.sparta.springblog.service;

import com.sparta.springblog.dto.PostRequestDto;
import com.sparta.springblog.dto.PostResponseDto;
import com.sparta.springblog.entity.Post;
import com.sparta.springblog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    // 패스워드비교
    public boolean pwEquals(String getPw, String postPw){
        if(getPw.equals(postPw))
            return true;
        return false;
    }

    // 작성하기(저장)
    @Transactional
    public Post createPost(PostRequestDto requestDto){
        Post post = new Post(requestDto);
        postRepository.save(post);
        return post;
    }

    // 조회하기
    @Transactional
    public List<Post> getPosts(){
        return postRepository.findAllByOrderByModifiedAtDesc();
    }

    @Transactional
    public PostResponseDto getPost(Long id){
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디 존재하지않음")
        );
        PostResponseDto postResponseDto = new PostResponseDto(post);
        return postResponseDto;
    }


    @Transactional
    public PostResponseDto update(Long id, PostRequestDto requestDto){
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디 존재하지않음")
        );
        //비밀번호 확인
        if(pwEquals(post.getPassword(), requestDto.getPassword())){
            post.update(requestDto);
        }
        PostResponseDto postResponseDto = new PostResponseDto(post);
        return postResponseDto;
    }


    @Transactional
    public String deletePost(Long id, PostRequestDto requestDto){
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디 존재하지않음")
        );
        if(pwEquals(post.getPassword(), requestDto.getPassword())){
            postRepository.deleteById(id);
            return "{"+"success:"+ "true}";
        }
        return "{"+"success:"+ "false}";
    }
}
