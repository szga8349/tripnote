package com.lenovo.tripnote.webchat.mapper;

import com.lenovo.tripnote.webchat.entity.BProductOrder;
import com.lenovo.tripnote.webchat.entity.BProductOrderExample;
import com.lenovo.tripnote.webchat.entity.vo.BProductCustomerOrderResultVo;
import com.lenovo.tripnote.webchat.entity.vo.BProductOrderDetailVo;
import com.lenovo.tripnote.webchat.entity.vo.BProductOrderResultVo;
import com.lenovo.tripnote.webchat.entity.vo.BProductOrderSearchVo;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface BProductOrderMapper {
    /**
     * 条件统计
     * 参数:查询条件,null为整张表
     * 返回:查询个数
     * @ibatorgenerated 2018-07-09 16:50:58
     */
    int countByExample(BProductOrderExample example);

    /**
     * 批量条件删除
     * 参数:删除条件,null为整张表
     * 返回:删除个数
     * @ibatorgenerated 2018-07-09 16:50:58
     */
    int deleteByExample(BProductOrderExample example);

    /**
     * 批量条件查询
     * 参数:查询条件,null查整张表
     * 返回:对象集合
     * @ibatorgenerated 2018-07-09 16:50:58
     */
    List<BProductOrder> selectByExample(BProductOrderExample example);

    /**
     * 批量条件修改，空值条件不修改
     * 参数:1.要修改成的值，2.要修改条件
     * 返回:成功修改个数
     * @ibatorgenerated 2018-07-09 16:50:58
     */
    int updateByExampleSelective(@Param("record") BProductOrder record, @Param("example") BProductOrderExample example);

    /**
     * 批量条件修改，空值条件会修改成null
     * 参数:1.要修改成的值，2.要修改条件
     * 返回:成功修改个数
     * @ibatorgenerated 2018-07-09 16:50:58
     */
    int updateByExample(@Param("record") BProductOrder record, @Param("example") BProductOrderExample example);

    /**
     * 物理分页条件查询
     * 参数:1.查询条件 2.分页条件 new RowBounds(2,3) 
            从第2条开始显示，显示3条(从0开始编号)
     * 返回:成功修改个数
     * @ibatorgenerated 2018-07-09 16:50:58
     */
    List<BProductOrder> selectByExampleAndPage(BProductOrderExample example, RowBounds rowBound);

    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2018-07-09 16:50:58
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-07-09 16:50:58
     */
    int insert(BProductOrder record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-07-09 16:50:58
     */
    int insertSelective(BProductOrder record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2018-07-09 16:50:58
     */
    BProductOrder selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-07-09 16:50:58
     */
    int updateByPrimaryKeySelective(BProductOrder record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-07-09 16:50:58
     */
    int updateByPrimaryKey(BProductOrder record);
    

	List<BProductOrderResultVo> search(@Param("record")BProductOrderSearchVo search);

	List<BProductCustomerOrderResultVo> searchCustomer(@Param("record") BProductOrderSearchVo search);

	/**查询我的产品订单总数
	 * @param search
	 * @return
	 */
	Long searchCustomerTotal(@Param("record")BProductOrderSearchVo search);
	

	/**根据产品查询所有订单信息
	 * @param search
	 * @return
	 */
	List<BProductOrderDetailVo> listByProduct(@Param("record")BProductOrderSearchVo search);
}