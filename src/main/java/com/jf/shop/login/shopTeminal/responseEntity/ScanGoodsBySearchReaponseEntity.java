package com.jf.shop.login.shopTeminal.responseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScanGoodsBySearchReaponseEntity {
    private PageInfoEntity pageInfo;
    private List<SkimGoods> skimGoodsList;

}
