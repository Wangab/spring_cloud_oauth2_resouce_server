package com.wangab.web.controller;

import com.mongodb.gridfs.GridFSFile;
import com.wangab.web.vob.FileBasicPrams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wanganbang on 8/5/16.
 */
@RestController
@RequestMapping("/files")
public class FileOpreController {
    @Resource
    private GridFsTemplate gridFsTemplate;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ApiOperation(value = "文件上传接口", notes = "输入一个文件和一个JSON格式的拓展消息", produces = "application/json")
    public @ResponseBody  ResponseEntity<Map<String, String>> saveFile(MultipartFile file, @RequestBody FileBasicPrams prams) {
        Map<String, String> map = new HashMap<>();
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            map.put("errormsg", e.getMessage());
            return new ResponseEntity<Map<String, String>>(map, HttpStatus.BAD_REQUEST);
        }
        GridFSFile gridFSFile = gridFsTemplate.store(inputStream, prams.getFileName(), prams.getExtType(), prams);

        map.put("fid", gridFSFile.getId().toString());
        map.put("time", gridFSFile.getUploadDate().toString());
        map.put("size", gridFSFile.getLength() + "");

        return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);

    }

    @RequestMapping("/test")
    public Map<String, String> test() {
        Map map = new HashMap();
        map.put("test", "1212");
        return map;
    }
}
