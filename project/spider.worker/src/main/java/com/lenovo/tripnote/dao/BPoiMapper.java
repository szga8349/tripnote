package com.lenovo.tripnote.dao;

import com.lenovo.tripnote.entity.BPoi;
import com.lenovo.tripnote.entity.BPoiExample;
import com.lenovo.tripnote.entity.BPoiWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface BPoiMapper {
    /**
     * 条件统计
     * 参数:查询条件,null为整张表
     * 返回:查询个数
     * @ibatorgenerated 2018-05-02 15:44:55
     */
    int countByExample(BPoiExample example);

    /**
     * 批量条件删除
     * 参数:删除条件,null为整张表
     * 返回:删除个数
     * @ibatorgenerated 2018-05-02 15:44:55
     */
    int deleteByExample(BPoiExample example);

    /**
     * 批量条件查询,支持大字段类型
     * 参数:查询条件,null查整张表
     * 返回:对象集合
     * @ibatorgenerated 2018-05-02 15:44:55
     */
    List<BPoiWithBLOBs> selectByExampleWithBLOBs(BPoiExample example);

    /**
     * 批量条件查询
     * 参数:查询条件,null查整张表
     * 返回:对象集合
     * @ibatorgenerated 2018-05-02 15:44:55
     */
    List<BPoi> selectByExample(BPoiExample example);

    /**
     * 批量条件修改，空值条件不修改
     * 参数:1.要修改成的值，2.要修改条件
     * 返回:成功修改个数
     * @ibatorgenerated 2018-05-02 15:44:55
     */
    int updateByExampleSelective(@Param("record") BPoiWithBLOBs record, @Param("example") BPoiExample example);

    /**
     * 批量条件修改，空值条件会修改成null,支持大字段类型
     * 参数:1.要修改成的值，2.要修改条件
     * 返回:成功修改个数
     * @ibatorgenerated 2018-05-02 15:44:55
     */
    int updateByExampleWithBLOBs(@Param("record") BPoiWithBLOBs record, @Param("example") BPoiExample example);

    /**
     * 批量条件修改，空值条件会修改成null
     * 参数:1.要修改成的值，2.要修改条件
     * 返回:成功修改个数
     * @ibatorgenerated 2018-05-02 15:44:55
     */
    int updateByExample(@Param("record") BPoi record, @Param("example") BPoiExample example);

    /**
     * 物理分页条件查询,支持大字段
     * 参数:1.查询条件 2.分页条件 new RowBounds(2,3) 
            从第2条开始显示，显示3条(从0开始编号)
     * 返回:成功修改个数
     * @ibatorgenerated 2018-05-02 15:44:55
     */
    List<BPoiWithBLOBs> selectByExampleWithBLOBsAndPage(BPoiExample example, RowBounds rowBound);

    /**
     * 物理分页条件查询
     * 参数:1.查询条件 2.分页条件 new RowBounds(2,3) 
            从第2条开始显示，显示3条(从0开始编号)
     * 返回:成功修改个数
     * @ibatorgenerated 2018-05-02 15:44:55
     */
    List<BPoi> selectByExampleAndPage(BPoiExample example, RowBounds rowBound);

    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2018-05-02 15:44:55
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-05-02 15:44:55
     */
    int insert(BPoiWithBLOBs record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-05-02 15:44:55
     */
    int insertSelective(BPoiWithBLOBs record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2018-05-02 15:44:55
     */
    BPoiWithBLOBs selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-05-02 15:44:55
     */
    int updateByPrimaryKeySelective(BPoiWithBLOBs record);

    /**
     * 根据主键修改，空值条件会修改成null,支持大字段类型
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-05-02 15:44:55
     */
    int updateByPrimaryKeyWithBLOBs(BPoiWithBLOBs record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-05-02 15:44:55
     */
    int updateByPrimaryKey(BPoi record);
}