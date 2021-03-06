package com.lenovo.tripnote.webchat.mapper;

import com.lenovo.tripnote.webchat.entity.BProductCashflow;
import com.lenovo.tripnote.webchat.entity.BProductCashflowExample;
import com.lenovo.tripnote.webchat.entity.vo.BProductCashFlowDetailVo;
import com.lenovo.tripnote.webchat.entity.vo.BProductCashFlowResultVo;
import com.lenovo.tripnote.webchat.entity.vo.BProductCashFlowSearchVo;
import com.lenovo.tripnote.webchat.entity.vo.BProductCashFlowTotalVo;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface BProductCashflowMapper {
    /**
     * 条件统计
     * 参数:查询条件,null为整张表
     * 返回:查询个数
     * @ibatorgenerated 2018-08-21 19:20:23
     */
    int countByExample(BProductCashflowExample example);

    /**
     * 批量条件删除
     * 参数:删除条件,null为整张表
     * 返回:删除个数
     * @ibatorgenerated 2018-08-21 19:20:23
     */
    int deleteByExample(BProductCashflowExample example);

    /**
     * 批量条件查询
     * 参数:查询条件,null查整张表
     * 返回:对象集合
     * @ibatorgenerated 2018-08-21 19:20:23
     */
    List<BProductCashflow> selectByExample(BProductCashflowExample example);

    /**
     * 批量条件修改，空值条件不修改
     * 参数:1.要修改成的值，2.要修改条件
     * 返回:成功修改个数
     * @ibatorgenerated 2018-08-21 19:20:23
     */
    int updateByExampleSelective(@Param("record") BProductCashflow record, @Param("example") BProductCashflowExample example);

    /**
     * 批量条件修改，空值条件会修改成null
     * 参数:1.要修改成的值，2.要修改条件
     * 返回:成功修改个数
     * @ibatorgenerated 2018-08-21 19:20:23
     */
    int updateByExample(@Param("record") BProductCashflow record, @Param("example") BProductCashflowExample example);

    /**
     * 物理分页条件查询
     * 参数:1.查询条件 2.分页条件 new RowBounds(2,3) 
            从第2条开始显示，显示3条(从0开始编号)
     * 返回:成功修改个数
     * @ibatorgenerated 2018-08-21 19:20:23
     */
    List<BProductCashFlowDetailVo> selectByExampleAndPage(BProductCashflowExample example, RowBounds rowBound);

    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2018-08-21 19:20:23
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-08-21 19:20:23
     */
    int insert(BProductCashflow record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-08-21 19:20:23
     */
    int insertSelective(BProductCashflow record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2018-08-21 19:20:23
     */
    BProductCashflow selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-08-21 19:20:23
     */
    int updateByPrimaryKeySelective(BProductCashflow record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-08-21 19:20:23
     */
    int updateByPrimaryKey(BProductCashflow record);
    
    
    
    /**查询 收益
     * @param vo
     * @return
     */
    List<BProductCashFlowResultVo> searchBProductCashflow(@Param("record")BProductCashFlowSearchVo vo);
    
    /**查询用户的账号余额
     * @param userId
     * @return
     */
    Float searchBalance(Integer userId);

	/**查询 收益的总得概括信息:总交易单数和总收益
	 * @param searchVo
	 * @return
	 */
	BProductCashFlowTotalVo searchTotal(@Param("record")BProductCashFlowSearchVo searchVo);
    
}