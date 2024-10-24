package com.example.houp.tocomwel.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@JacksonXmlRootElement(localName = "response")
public class ReportToObject {

    @JacksonXmlProperty(localName = "header")
    private Header header;

    @JacksonXmlProperty(localName = "body")
    private Body body;

    @Getter
    @NoArgsConstructor
    public static class Header {
        @JacksonXmlProperty(localName = "resultCode")
        private String resultCode;

        @JacksonXmlProperty(localName = "resultMsg")
        private String resultMsg;
    }

    @Getter
    @NoArgsConstructor
    public static class Body {
        @JacksonXmlProperty(localName = "items")
        private Items items;

        @JacksonXmlProperty(localName = "numOfRows")
        private String numOfRows;

        @JacksonXmlProperty(localName = "pageNo")
        private String pageNo;

        @JacksonXmlProperty(localName = "totalCount")
        private String totalCount;
    }

    @Getter
    @NoArgsConstructor
    public static class Items {
        @JacksonXmlElementWrapper(useWrapping = false)
        @JacksonXmlProperty(localName = "item")
        private List<Item> item;
    }

    @Getter
    @NoArgsConstructor
    public static class Item {
        @JacksonXmlProperty(localName = "accnum")
        private String accnum;

        @JacksonXmlProperty(localName = "kinda")
        private String kinda;

        @JacksonXmlProperty(localName = "kindb")
        private String kindb;

        @JacksonXmlProperty(localName = "kindc")
        private String kindc;

        @JacksonXmlProperty(localName = "title")
        private String title;

        @JacksonXmlProperty(localName = "noncontent")
        @JacksonXmlCData
        private String noncontent;
    }
}
