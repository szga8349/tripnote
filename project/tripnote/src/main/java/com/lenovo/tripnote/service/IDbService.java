package com.lenovo.tripnote.service;

public interface IDbService<T> {
	
	/**插入
	 * @param t
	 * @return
	 */
	public int insert(T t);

	/**修改
	 * @param t
	 * @return
	 */
	public T update(T t);

	/**根据主键值查询实体
	 * @param id
	 * @return
	 */
	public T getByKey(Integer id);

	/**根据主键值删除数据
	 * @param id
	 * @return
	 */
	public int deleteBykey(Integer id);

	/**根据条件删除数据
	 * @param t
	 * @return
	 */
	public int deleteCondition(T t);
	
}
