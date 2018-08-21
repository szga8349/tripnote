package com.lenovo.tripnote.webchat.mapper;

import com.lenovo.tripnote.webchat.entity.BClassify;
import com.lenovo.tripnote.webchat.entity.BClassifyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface BClassifyMapper {
    /**
     * 条件统计
     * 参数:查询条件,null为整张表
     * 返回:查询个数
     * @ibatorgenerated 2018-08-21 17:10:20
     */
    int countByExample(BClassifyExample example);

    /**
     * 批量条件删除
     * 参数:删除条件,null为整张表
     * 返回:删除个数
     * @ibatorgenerated 2018-08-21 17:10:20
     */
    int deleteByExample(BClassifyExample example);

    /**
     * 批量条件查询
     * 参数:查询条件,null查整张表
     * 返回:对象集合
     * @ibatorgenerated 2018-08-21 17:10:20
     */
    List<BClassify> selectByExample(BClassifyExample example);

    /**
     * 批量条件修改，空值条件不修改
     * 参数:1.要修改成的值，2.要修改条件
     * 返回:成功修改个数
     * @ibatorgenerated 2018-08-21 17:10:20
     */
    int updateByExampleSelective(@Param("record") BClassify record, @Param("example") BClassifyExample example);

    /**
     * 批量条件修改，空值条件会修改成null
     * 参数:1.要修改成的值，2.要修改条件
     * 返回:成功修改个数
     * @ibatorgenerated 2018-08-21 17:10:20
     */
    int updateByExample(@Param("record") BClassify record, @Param("example") BClassifyExample example);

    /**
     * 物理分页条件查询
     * 参数:1.查询条件 2.分页条件 new RowBounds(2,3) 
            从第2条开始显示，显示3条(从0开始编号)
     * 返回:成功修改个数
     * @ibatorgenerated 2018-08-21 17:10:20
     */
    List<BClassify> selectByExampleAndPage(BClassifyExample example, RowBounds rowBound);

    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2018-08-21 17:10:20
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-08-21 17:10:20
     */
    int insert(BClassify record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-08-21 17:10:20
     */
    int insertSelective(BClassify record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2018-08-21 17:10:20
     */
    BClassify selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-08-21 17:10:20
     */
    int updateByPrimaryKeySelective(BClassify record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-08-21 17:10:20
     */
    int updateByPrimaryKey(BClassify record);
}