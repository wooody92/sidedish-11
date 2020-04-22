package dev.codesquad.java.sidedish11.controller;

import dev.codesquad.java.sidedish11.dto.ItemDetail;
import dev.codesquad.java.sidedish11.dto.ItemResponse;
import dev.codesquad.java.sidedish11.entity.Item;
import dev.codesquad.java.sidedish11.service.CategoryService;
import dev.codesquad.java.sidedish11.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiHomeController {
    private Logger logger = LoggerFactory.getLogger(ApiHomeController.class);

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ItemService itemService;

    @GetMapping("/")
    public ResponseEntity viewAll() {
        return new ResponseEntity(categoryService.viewAll(), HttpStatus.OK);
    }

    @GetMapping("/item")
    public ResponseEntity viewAllItems() {
        return new ResponseEntity(itemService.viewAll(), HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity test() {
        Item item = itemService.view(1L);
        ItemResponse itemResponse = new ItemResponse(item);
        return new ResponseEntity(itemResponse, HttpStatus.OK);
    }

    @GetMapping("/detail")
    public ResponseEntity detail() {
        Item item = itemService.view(1L);
        ItemDetail itemDetail = new ItemDetail(item);
        return new ResponseEntity(itemDetail, HttpStatus.OK);
    }
}
