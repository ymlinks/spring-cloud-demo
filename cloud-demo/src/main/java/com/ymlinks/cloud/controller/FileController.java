package com.ymlinks.cloud.controller;

import com.ymlinks.cloud.model.Book;
import com.ymlinks.cloud.service.BookService;
import com.ymlinks.cloud.service.OssService;
import com.ymlinks.common.controller.BaseController;
import com.ymlinks.common.dto.ResultTO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RequestMapping
@RestController
public class FileController extends BaseController {

    @Resource
    private OssService ossService;
    @Resource
    private BookService bookService;

    @GetMapping("/token")
    public ResultTO getToken() {
        return sendResult(ossService.getToken());
    }

    @PostMapping("/save")
    public ResultTO saveBook(@RequestBody Book book) {
        bookService.save(book);
        return sendResult(null);
    }

}
