/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.toystore.dao;

import java.util.List;

/**
 *
 * @author hongk
 */
public abstract class StoreDao<E, K> {
    String id;
    abstract public void insert(E e);
    abstract public void update(E e);
    abstract public void delete(K k);
    abstract public List<E> selectAll();
//    abstract public E selectAll();
    abstract public E selectById(K k);
    abstract public List<E> selectById1(K k);
    abstract public E selectByName(K k);
    abstract protected List<E> selectBySql(String sql, Object... args);
   
//    abstract protected List<E> selectBySql1(String sql, Object... args);

}
