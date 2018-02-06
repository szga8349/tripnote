package com.lenovo.tripnote.entity.vo;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**定制导出模板时所需参数对象
 * @author shijy2
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class BExportVo {
     private List<Integer> tripnoteIds;
     private Integer createUserId;
     private Integer type;
}
