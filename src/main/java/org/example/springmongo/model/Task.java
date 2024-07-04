package org.example.springmongo.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@Builder
public class Task {
    @Id
    private String id;
    private String title;
    private String description;
    private Date dueDate;
}
