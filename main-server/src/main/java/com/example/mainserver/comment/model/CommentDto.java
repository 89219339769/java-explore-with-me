package com.example.mainserver.comment.model;
import com.example.mainserver.event.model.Event;
import com.example.mainserver.user.model.User;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;




@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CommentDto {

    private Long id;


    private String description;


    private User user;


    private Event event;


    private LocalDateTime createdOn;


}
