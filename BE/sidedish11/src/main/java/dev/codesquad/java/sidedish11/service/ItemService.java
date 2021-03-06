package dev.codesquad.java.sidedish11.service;

import dev.codesquad.java.sidedish11.dto.ItemDetail;
import dev.codesquad.java.sidedish11.dto.ItemDetailResponse;
import dev.codesquad.java.sidedish11.dto.ItemResponse;
import dev.codesquad.java.sidedish11.entity.Item;
import dev.codesquad.java.sidedish11.exception.DataNotFoundException;
import dev.codesquad.java.sidedish11.repository.ItemDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static dev.codesquad.java.sidedish11.common.CommonUtils.*;

@Service
public class ItemService {
    private Logger logger = LoggerFactory.getLogger(ItemService.class);

    @Autowired
    private ItemDao itemDao;

    @Transactional
    public ItemResponse getItem(Long categoryId, String hash) {
        Item item = findItem(categoryId, hash);
        ItemResponse itemResponse = new ItemResponse(item);
        return itemResponse;
    }

    @Transactional
    public ItemDetailResponse getItemDetail(String hash) {
        Item item = findItem(hash);
        ItemDetail itemDetail = new ItemDetail(item);
        ItemDetailResponse itemDetailResponse = new ItemDetailResponse(itemDetail);
        return itemDetailResponse;
    }

    private Item findItem(Long categoryId, String hash) {
        return itemDao.findByCategoryIdAndHash(categoryId, hash).orElseThrow(() -> new DataNotFoundException(ITEM_NOT_FOUND));
    }

    private Item findItem(String hash) {
        return itemDao.findByHash(hash).orElseThrow(() -> new DataNotFoundException(ITEM_NOT_FOUND));
    }
}
