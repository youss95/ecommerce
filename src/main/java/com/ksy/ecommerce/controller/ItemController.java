package com.ksy.ecommerce.controller;

import com.ksy.ecommerce.dto.ItemFormDto;
import com.ksy.ecommerce.entity.Item;
import com.ksy.ecommerce.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;


        @PostMapping(value = "/admin/item/new")
        public String itemNew(@Valid ItemFormDto itemFormDto, BindingResult bindingResult, @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList){
            log.info("ksy: " + itemFormDto.getItemNm());
            log.info("st: " + itemImgFileList.get(0).getOriginalFilename());
            if(bindingResult.hasErrors()){
                return "item/itemForm";
            }

            if(itemImgFileList.get(0).isEmpty() && itemFormDto.getId() == null){
                System.out.println("error!!!");
            }

            try {
                itemService.saveItem(itemFormDto, itemImgFileList);
            } catch (Exception e){
                log.info("error!!!!! not saved !!!!");
            }

            return "success";

    }
}
