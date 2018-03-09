package com.lenovo.tripnote.dao;

import com.lenovo.tripnote.entity.BTraffic;
import com.lenovo.tripnote.entity.BTrafficExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface BTrafficMapper {
    /**
     * 条件统计
     * 参数:查询条件,null为整张表
     * 返回:查询个数
     * @ibatorgenerated 2018-03-09 16:53:11
     */
    int countByExample(BTrafficExample example);

    /**
     * 批量条件删除
     * 参数:删除条件,null为整张表
     * 返回:删除个数
     * @ibatorgenerated 2018-03-09 16:53:11
     */
    int deleteByExample(BTrafficExample example);

    /**
     * 批量条件查询
     * 参数:查询条件,null查整张表
     * 返回:对象集合
     * @ibatorgenerated 2018-03-09 16:53:11
     */
    List<BTraffic> selectByExample(BTrafficExample example);

    /**
     * 批量条件修改，空值条件不修改
     * 参数:1.要修改成的值，2.要修改条件
     * 返回:成功修改个数
     * @ibatorgenerated 2018-03-09 16:53:11
     */
    int updateByExampleSelective(@Param("record") BTraffic record, @Param("example") BTrafficExample example);

    /**
     * 批量条件修改，空值条件会修改成null
     * 参数:1.要修改成的值，2.要修改条件
     * 返回:成功修改个数
     * @ibatorgenerated 2018-03-09 16:53:11
     */
    int updateByExample(@Param("record") BTraffic record, @Param("example") BTrafficExample example);

    /**
     * 物理分页条件查询
     * 参数:1.查询条件 2.分页条件 new RowBounds(2,3) 
            从第2条开始显示，显示3条(从0开始编号)
     * 返回:成功修改个数
     * @ibatorgenerated 2018-03-09 16:53:11
     */
    List<BTraffic> selectByExampleAndPage(BTrafficExample example, RowBounds rowBound);

    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2018-03-09 16:53:11
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-03-09 16:53:11
     */
    int insert(BTraffic record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-03-09 16:53:11
     */
    int insertSelective(BTraffic record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2018-03-09 16:53:11
     */
    BTraffic selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-03-09 16:53:11
     */
    int updateByPrimaryKeySelective(BTraffic record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-03-09 16:53:11
     */
    int updateByPrimaryKey(BTraffic record);
}