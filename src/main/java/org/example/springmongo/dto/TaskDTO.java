package org.example.springmongo.dto;

import java.util.Date;

public record TaskDTO(String id,String title, String description, Date dueDate) {
}
