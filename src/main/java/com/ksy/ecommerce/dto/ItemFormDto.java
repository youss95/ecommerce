package com.ksy.ecommerce.dto;


import com.ksy.ecommerce.constant.ItemSellStatus;
import com.ksy.ecommerce.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ItemFormDto {

    private Long id;

    @NotBlank(message = "required")
    private String itemNm;

    @NotNull(message = "price required")
    private Integer price;

    @NotBlank(message = "name required")
    private String itemDetail;

    @NotNull(message = "stock required")
    private Integer stockNumber;

    private ItemSellStatus itemSellStatus;

    private List<ItemImgDto> itemImgDtoList = new ArrayList<>();

    private List<Long> itemImgIds = new ArrayList<>();

    public Item createItem() {
        Item item = new Item(null,"kk",1000,100,"detail",ItemSellStatus.SELL, LocalDateTime.now(),null);
        return item;
    }

}
