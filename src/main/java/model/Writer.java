package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Writer {
    private Long id;
    private String firstName;
    private String lastName;
    private List<Post> posts;
}
