package com.lenovo.tripnote.webchat.entity.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BProductCashFlowTotalVo {
	  /**
     * 完成单数
     */
    private Integer salesCount;
    
    /**
     * 总收益
     */
    private Float totalProfit;
}
