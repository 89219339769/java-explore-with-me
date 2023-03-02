package com.example.mainserver.comment.model;
import lombok.*;
import java.time.LocalDateTime;




@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CommentDto {

    private Long id;


    private String description;


    private Long userId;


    private Long eventId;


    private LocalDateTime createdOn;


}
