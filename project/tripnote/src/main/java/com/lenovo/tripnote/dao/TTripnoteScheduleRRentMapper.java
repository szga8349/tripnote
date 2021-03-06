package com.lenovo.tripnote.dao;

import com.lenovo.tripnote.entity.TTripnoteScheduleRRent;
import com.lenovo.tripnote.entity.TTripnoteScheduleRRentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TTripnoteScheduleRRentMapper {
    /**
     * 条件统计
     * 参数:查询条件,null为整张表
     * 返回:查询个数
     * @ibatorgenerated 2018-03-08 18:03:03
     */
    int countByExample(TTripnoteScheduleRRentExample example);

    /**
     * 批量条件删除
     * 参数:删除条件,null为整张表
     * 返回:删除个数
     * @ibatorgenerated 2018-03-08 18:03:03
     */
    int deleteByExample(TTripnoteScheduleRRentExample example);

    /**
     * 批量条件查询
     * 参数:查询条件,null查整张表
     * 返回:对象集合
     * @ibatorgenerated 2018-03-08 18:03:03
     */
    List<TTripnoteScheduleRRent> selectByExample(TTripnoteScheduleRRentExample example);

    /**
     * 批量条件修改，空值条件不修改
     * 参数:1.要修改成的值，2.要修改条件
     * 返回:成功修改个数
     * @ibatorgenerated 2018-03-08 18:03:03
     */
    int updateByExampleSelective(@Param("record") TTripnoteScheduleRRent record, @Param("example") TTripnoteScheduleRRentExample example);

    /**
     * 批量条件修改，空值条件会修改成null
     * 参数:1.要修改成的值，2.要修改条件
     * 返回:成功修改个数
     * @ibatorgenerated 2018-03-08 18:03:03
     */
    int updateByExample(@Param("record") TTripnoteScheduleRRent record, @Param("example") TTripnoteScheduleRRentExample example);

    /**
     * 物理分页条件查询
     * 参数:1.查询条件 2.分页条件 new RowBounds(2,3) 
            从第2条开始显示，显示3条(从0开始编号)
     * 返回:成功修改个数
     * @ibatorgenerated 2018-03-08 18:03:03
     */
    List<TTripnoteScheduleRRent> selectByExampleAndPage(TTripnoteScheduleRRentExample example, RowBounds rowBound);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-03-08 18:03:03
     */
    int insert(TTripnoteScheduleRRent record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-03-08 18:03:03
     */
    int insertSelective(TTripnoteScheduleRRent record);
}