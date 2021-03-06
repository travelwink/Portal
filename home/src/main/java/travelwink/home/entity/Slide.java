package travelwink.home.entity;

import lombok.Data;

@Data
public class Slide{
    private int id;
    private String title;
    private String content;
    private int theme;
    private String imgSrc;
    private String imgMobileSrc;
    private int imgSize;
    private String url;
}
