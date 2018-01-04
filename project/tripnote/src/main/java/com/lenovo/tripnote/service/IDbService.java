package com.lenovo.tripnote.service;

public interface IDbService<T> {
	
	public int insert(T t);

	public T update(T t);

	public T getByKey(Integer id);

	public int deleteBykey(Integer id);

	public int deleteCondition(T t);
}
