package com.lenovo.tripnote.webchat.entity.vo;

import java.util.List;

import com.lenovo.tripnote.webchat.entity.BProduct;
import com.lenovo.tripnote.webchat.entity.BProductImage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BProductDetailVo extends BProduct {
	
       private List<BProductImage> imageUrls;
       
       private List<BProductOrderCollageDetailVo> collages;
}
