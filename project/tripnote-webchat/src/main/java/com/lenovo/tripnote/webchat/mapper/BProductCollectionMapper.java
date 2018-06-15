package com.lenovo.tripnote.webchat.mapper;

import com.lenovo.tripnote.webchat.entity.BProductCollection;
import com.lenovo.tripnote.webchat.entity.BProductCollectionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface BProductCollectionMapper {
    /**
     * 条件统计
     * 参数:查询条件,null为整张表
     * 返回:查询个数
     * @ibatorgenerated 2018-06-15 09:12:43
     */
    int countByExample(BProductCollectionExample example);

    /**
     * 批量条件删除
     * 参数:删除条件,null为整张表
     * 返回:删除个数
     * @ibatorgenerated 2018-06-15 09:12:43
     */
    int deleteByExample(BProductCollectionExample example);

    /**
     * 批量条件查询
     * 参数:查询条件,null查整张表
     * 返回:对象集合
     * @ibatorgenerated 2018-06-15 09:12:43
     */
    List<BProductCollection> selectByExample(BProductCollectionExample example);

    /**
     * 批量条件修改，空值条件不修改
     * 参数:1.要修改成的值，2.要修改条件
     * 返回:成功修改个数
     * @ibatorgenerated 2018-06-15 09:12:43
     */
    int updateByExampleSelective(@Param("record") BProductCollection record, @Param("example") BProductCollectionExample example);

    /**
     * 批量条件修改，空值条件会修改成null
     * 参数:1.要修改成的值，2.要修改条件
     * 返回:成功修改个数
     * @ibatorgenerated 2018-06-15 09:12:43
     */
    int updateByExample(@Param("record") BProductCollection record, @Param("example") BProductCollectionExample example);

    /**
     * 物理分页条件查询
     * 参数:1.查询条件 2.分页条件 new RowBounds(2,3) 
            从第2条开始显示，显示3条(从0开始编号)
     * 返回:成功修改个数
     * @ibatorgenerated 2018-06-15 09:12:43
     */
    List<BProductCollection> selectByExampleAndPage(BProductCollectionExample example, RowBounds rowBound);

    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2018-06-15 09:12:43
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-06-15 09:12:43
     */
    int insert(BProductCollection record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-06-15 09:12:43
     */
    int insertSelective(BProductCollection record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2018-06-15 09:12:43
     */
    BProductCollection selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-06-15 09:12:43
     */
    int updateByPrimaryKeySelective(BProductCollection record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-06-15 09:12:43
     */
    int updateByPrimaryKey(BProductCollection record);
}