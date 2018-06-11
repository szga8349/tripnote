package com.lenovo.tripnote.webchat.mapper;

import com.lenovo.tripnote.webchat.entity.BProductImage;
import com.lenovo.tripnote.webchat.entity.BProductImageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface BProductImageMapper {
    /**
     * 条件统计
     * 参数:查询条件,null为整张表
     * 返回:查询个数
     * @ibatorgenerated 2018-06-06 14:07:52
     */
    int countByExample(BProductImageExample example);

    /**
     * 批量条件删除
     * 参数:删除条件,null为整张表
     * 返回:删除个数
     * @ibatorgenerated 2018-06-06 14:07:52
     */
    int deleteByExample(BProductImageExample example);

    /**
     * 批量条件查询
     * 参数:查询条件,null查整张表
     * 返回:对象集合
     * @ibatorgenerated 2018-06-06 14:07:52
     */
    List<BProductImage> selectByExample(BProductImageExample example);

    /**
     * 批量条件修改，空值条件不修改
     * 参数:1.要修改成的值，2.要修改条件
     * 返回:成功修改个数
     * @ibatorgenerated 2018-06-06 14:07:52
     */
    int updateByExampleSelective(@Param("record") BProductImage record, @Param("example") BProductImageExample example);

    /**
     * 批量条件修改，空值条件会修改成null
     * 参数:1.要修改成的值，2.要修改条件
     * 返回:成功修改个数
     * @ibatorgenerated 2018-06-06 14:07:52
     */
    int updateByExample(@Param("record") BProductImage record, @Param("example") BProductImageExample example);

    /**
     * 物理分页条件查询
     * 参数:1.查询条件 2.分页条件 new RowBounds(2,3) 
            从第2条开始显示，显示3条(从0开始编号)
     * 返回:成功修改个数
     * @ibatorgenerated 2018-06-06 14:07:52
     */
    List<BProductImage> selectByExampleAndPage(BProductImageExample example, RowBounds rowBound);

    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2018-06-06 14:07:52
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-06-06 14:07:52
     */
    int insert(BProductImage record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-06-06 14:07:52
     */
    int insertSelective(BProductImage record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2018-06-06 14:07:52
     */
    BProductImage selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-06-06 14:07:52
     */
    int updateByPrimaryKeySelective(BProductImage record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-06-06 14:07:52
     */
    int updateByPrimaryKey(BProductImage record);
}