package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private Long id;
    private String content;
    private Timestamp created;
    private Timestamp updated;
    private List<Label> labels;
    private PostStatus status;


}
