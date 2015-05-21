package com.couchbase.workshop;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/query")
public class QueryController {

    @RequestMapping("/readYourWrite")
    public List<String> readYourWrite() {

        return null;
    }

    @RequestMapping("/prepared")
    public List<String> prepared() {
        
        return null;
    }

    @RequestMapping("/parameterized")
    public List<String> parameterized() {

        return null;
    }

}
