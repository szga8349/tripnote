package com.lenovo.tripnote.dao;

import com.lenovo.tripnote.entity.BHotel;
import com.lenovo.tripnote.entity.BHotelExample;
import com.lenovo.tripnote.entity.BHotelWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface BHotelMapper {
    /**
     * 条件统计
     * 参数:查询条件,null为整张表
     * 返回:查询个数
     * @ibatorgenerated 2018-05-02 15:44:54
     */
    int countByExample(BHotelExample example);

    /**
     * 批量条件删除
     * 参数:删除条件,null为整张表
     * 返回:删除个数
     * @ibatorgenerated 2018-05-02 15:44:54
     */
    int deleteByExample(BHotelExample example);

    /**
     * 批量条件查询,支持大字段类型
     * 参数:查询条件,null查整张表
     * 返回:对象集合
     * @ibatorgenerated 2018-05-02 15:44:54
     */
    List<BHotelWithBLOBs> selectByExampleWithBLOBs(BHotelExample example);

    /**
     * 批量条件查询
     * 参数:查询条件,null查整张表
     * 返回:对象集合
     * @ibatorgenerated 2018-05-02 15:44:54
     */
    List<BHotel> selectByExample(BHotelExample example);

    /**
     * 批量条件修改，空值条件不修改
     * 参数:1.要修改成的值，2.要修改条件
     * 返回:成功修改个数
     * @ibatorgenerated 2018-05-02 15:44:54
     */
    int updateByExampleSelective(@Param("record") BHotelWithBLOBs record, @Param("example") BHotelExample example);

    /**
     * 批量条件修改，空值条件会修改成null,支持大字段类型
     * 参数:1.要修改成的值，2.要修改条件
     * 返回:成功修改个数
     * @ibatorgenerated 2018-05-02 15:44:54
     */
    int updateByExampleWithBLOBs(@Param("record") BHotelWithBLOBs record, @Param("example") BHotelExample example);

    /**
     * 批量条件修改，空值条件会修改成null
     * 参数:1.要修改成的值，2.要修改条件
     * 返回:成功修改个数
     * @ibatorgenerated 2018-05-02 15:44:54
     */
    int updateByExample(@Param("record") BHotel record, @Param("example") BHotelExample example);

    /**
     * 物理分页条件查询,支持大字段
     * 参数:1.查询条件 2.分页条件 new RowBounds(2,3) 
            从第2条开始显示，显示3条(从0开始编号)
     * 返回:成功修改个数
     * @ibatorgenerated 2018-05-02 15:44:54
     */
    List<BHotelWithBLOBs> selectByExampleWithBLOBsAndPage(BHotelExample example, RowBounds rowBound);

    /**
     * 物理分页条件查询
     * 参数:1.查询条件 2.分页条件 new RowBounds(2,3) 
            从第2条开始显示，显示3条(从0开始编号)
     * 返回:成功修改个数
     * @ibatorgenerated 2018-05-02 15:44:54
     */
    List<BHotel> selectByExampleAndPage(BHotelExample example, RowBounds rowBound);

    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2018-05-02 15:44:54
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-05-02 15:44:54
     */
    int insert(BHotelWithBLOBs record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-05-02 15:44:54
     */
    int insertSelective(BHotelWithBLOBs record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2018-05-02 15:44:54
     */
    BHotelWithBLOBs selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-05-02 15:44:54
     */
    int updateByPrimaryKeySelective(BHotelWithBLOBs record);

    /**
     * 根据主键修改，空值条件会修改成null,支持大字段类型
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-05-02 15:44:54
     */
    int updateByPrimaryKeyWithBLOBs(BHotelWithBLOBs record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-05-02 15:44:54
     */
    int updateByPrimaryKey(BHotel record);
}