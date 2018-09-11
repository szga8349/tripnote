package com.lenovo.tripnote.webchat.mapper;

import com.lenovo.tripnote.webchat.entity.BProduct;
import com.lenovo.tripnote.webchat.entity.BProductExample;
import com.lenovo.tripnote.webchat.entity.vo.BProductDetailVo;
import com.lenovo.tripnote.webchat.entity.vo.BProductResultVo;
import com.lenovo.tripnote.webchat.entity.vo.BProductSearchVo;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface BProductMapper {
    /**
     * 条件统计
     * 参数:查询条件,null为整张表
     * 返回:查询个数
     * @ibatorgenerated 2018-06-07 16:43:43
     */
    int countByExample(BProductExample example);

    /**
     * 批量条件删除
     * 参数:删除条件,null为整张表
     * 返回:删除个数
     * @ibatorgenerated 2018-06-07 16:43:43
     */
    int deleteByExample(BProductExample example);

    /**
     * 批量条件查询,支持大字段类型
     * 参数:查询条件,null查整张表
     * 返回:对象集合
     * @ibatorgenerated 2018-06-07 16:43:43
     */
    List<BProduct> selectByExampleWithBLOBs(BProductExample example);

    /**
     * 批量条件查询
     * 参数:查询条件,null查整张表
     * 返回:对象集合
     * @ibatorgenerated 2018-06-07 16:43:43
     */
    List<BProduct> selectByExample(BProductExample example);

    /**
     * 批量条件修改，空值条件不修改
     * 参数:1.要修改成的值，2.要修改条件
     * 返回:成功修改个数
     * @ibatorgenerated 2018-06-07 16:43:43
     */
    int updateByExampleSelective(@Param("record") BProduct record, @Param("example") BProductExample example);

    /**
     * 批量条件修改，空值条件会修改成null,支持大字段类型
     * 参数:1.要修改成的值，2.要修改条件
     * 返回:成功修改个数
     * @ibatorgenerated 2018-06-07 16:43:43
     */
    int updateByExampleWithBLOBs(@Param("record") BProduct record, @Param("example") BProductExample example);

    /**
     * 批量条件修改，空值条件会修改成null
     * 参数:1.要修改成的值，2.要修改条件
     * 返回:成功修改个数
     * @ibatorgenerated 2018-06-07 16:43:43
     */
    int updateByExample(@Param("record") BProduct record, @Param("example") BProductExample example);

    /**
     * 物理分页条件查询,支持大字段
     * 参数:1.查询条件 2.分页条件 new RowBounds(2,3) 
            从第2条开始显示，显示3条(从0开始编号)
     * 返回:成功修改个数
     * @ibatorgenerated 2018-06-07 16:43:43
     */
    List<BProduct> selectByExampleWithBLOBsAndPage(BProductExample example, RowBounds rowBound);

    /**
     * 物理分页条件查询
     * 参数:1.查询条件 2.分页条件 new RowBounds(2,3) 
            从第2条开始显示，显示3条(从0开始编号)
     * 返回:成功修改个数
     * @ibatorgenerated 2018-06-07 16:43:43
     */
    List<BProduct> selectByExampleAndPage(BProductExample example, RowBounds rowBound);

    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2018-06-07 16:43:43
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-06-07 16:43:43
     */
    int insert(BProduct record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-06-07 16:43:43
     */
    int insertSelective(BProduct record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2018-06-07 16:43:43
     */
    BProduct selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-06-07 16:43:43
     */
    int updateByPrimaryKeySelective(BProduct record);

    /**
     * 根据主键修改，空值条件会修改成null,支持大字段类型
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-06-07 16:43:43
     */
    int updateByPrimaryKeyWithBLOBs(BProduct record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-06-07 16:43:43
     */
    int updateByPrimaryKey(BProduct record);
    

	List<BProductResultVo> searchProduct(@Param("record")BProductSearchVo searchVo);

	BProductDetailVo detail(Integer valueOf);
}