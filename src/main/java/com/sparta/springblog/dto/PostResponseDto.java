package com.sparta.springblog.dto;

import com.sparta.springblog.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {
    private Long id;
    private String title;
    private String author;
    private String contents;
    private LocalDateTime modifiedAt;
    private LocalDateTime createdAt;

    public PostResponseDto(Post post){
        this.id = post.getId();
        this.author = post.getAuthor();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.modifiedAt = post.getModifiedAt();
        this.createdAt = post.getCreatedAt();
    }
}
