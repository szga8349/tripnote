package com.lenovo.tripnote.webchat.mapper;

import com.lenovo.tripnote.webchat.entity.BProductOrderCollage;
import com.lenovo.tripnote.webchat.entity.BProductOrderCollageExample;
import com.lenovo.tripnote.webchat.entity.vo.BProductOrderCollageDetailVo;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface BProductOrderCollageMapper {
    /**
     * 条件统计
     * 参数:查询条件,null为整张表
     * 返回:查询个数
     * @ibatorgenerated 2018-07-09 16:50:59
     */
    int countByExample(BProductOrderCollageExample example);

    /**
     * 批量条件删除
     * 参数:删除条件,null为整张表
     * 返回:删除个数
     * @ibatorgenerated 2018-07-09 16:50:59
     */
    int deleteByExample(BProductOrderCollageExample example);

    /**
     * 批量条件查询
     * 参数:查询条件,null查整张表
     * 返回:对象集合
     * @ibatorgenerated 2018-07-09 16:50:59
     */
    List<BProductOrderCollage> selectByExample(BProductOrderCollageExample example);

    /**
     * 批量条件修改，空值条件不修改
     * 参数:1.要修改成的值，2.要修改条件
     * 返回:成功修改个数
     * @ibatorgenerated 2018-07-09 16:50:59
     */
    int updateByExampleSelective(@Param("record") BProductOrderCollage record, @Param("example") BProductOrderCollageExample example);

    /**
     * 批量条件修改，空值条件会修改成null
     * 参数:1.要修改成的值，2.要修改条件
     * 返回:成功修改个数
     * @ibatorgenerated 2018-07-09 16:50:59
     */
    int updateByExample(@Param("record") BProductOrderCollage record, @Param("example") BProductOrderCollageExample example);

    /**
     * 物理分页条件查询
     * 参数:1.查询条件 2.分页条件 new RowBounds(2,3) 
            从第2条开始显示，显示3条(从0开始编号)
     * 返回:成功修改个数
     * @ibatorgenerated 2018-07-09 16:50:59
     */
    List<BProductOrderCollage> selectByExampleAndPage(BProductOrderCollageExample example, RowBounds rowBound);

    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2018-07-09 16:50:59
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-07-09 16:50:59
     */
    int insert(BProductOrderCollage record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2018-07-09 16:50:59
     */
    int insertSelective(BProductOrderCollage record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2018-07-09 16:50:59
     */
    BProductOrderCollage selectByPrimaryKey(Integer id);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-07-09 16:50:59
     */
    int updateByPrimaryKeySelective(BProductOrderCollage record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2018-07-09 16:50:59
     */
    int updateByPrimaryKey(BProductOrderCollage record);
    
	
	
	/**一个拼团里面包括那些人员信息
	 * @param id:拼团ID
	 * @return
	 */
	List<BProductOrderCollageDetailVo> listUser(Integer id);
	

	/**产品对应的拼团信息
	 * @param valueOf
	 * @return
	 */
	List<BProductOrderCollageDetailVo> listCollage(Integer valueOf);
}