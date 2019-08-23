package com.example.translate.Controller;

import com.example.translate.Service.pullXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class ShowData {

    @Autowired
    private pullXML pullxml;

    @GetMapping("/show")
    public Map<String,Object> getdata(String Word) {

        Map map=new HashMap();

        map=pullxml.readStringXmlOut(Word);

        return map;
    }
}