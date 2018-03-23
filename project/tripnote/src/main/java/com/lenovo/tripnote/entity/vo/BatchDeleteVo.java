package com.lenovo.tripnote.entity.vo;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**批处理删除ID
 * @author shijy2
 *
 */

@Getter
@Setter
@NoArgsConstructor
public class BatchDeleteVo {

	private List<Integer> ids;
}
