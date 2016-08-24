package com.wangab.web.vob;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by wanganbang on 8/10/16.
 */
public class FileBasicPrams {
    @ApiModelProperty(hidden = true)
    private String fileName;
    @ApiModelProperty(hidden = true)
    private String extType;
    private String flag;

    @JsonCreator
    public FileBasicPrams(@JsonProperty("filename") String fileName, @JsonProperty("exttype") String extType, @JsonProperty("flag") String flag) {
        this.fileName = fileName;
        this.extType = extType;
        this.flag = flag;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getExtType() {
        return extType;
    }

    public void setExtType(String extType) {
        this.extType = extType;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
