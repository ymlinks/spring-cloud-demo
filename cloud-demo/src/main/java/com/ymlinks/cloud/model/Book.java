package com.ymlinks.cloud.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "wx", type = "books")
public class Book {

    @Id
    private String id;

    private String title;

    private String author;

    private String releaseDate;
}