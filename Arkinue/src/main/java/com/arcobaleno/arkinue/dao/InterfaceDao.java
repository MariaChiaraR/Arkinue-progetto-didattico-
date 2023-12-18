package com.arcobaleno.arkinue.dao;

import java.util.List;

public interface InterfaceDao <T>
{
	public T create(T obj);
	public List<T> retrive();
	public T update(T obj);
	public void delete(T obj);
	
	
}
