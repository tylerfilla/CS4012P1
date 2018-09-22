/*
 * Tyler Filla
 * CS 4012 - P1
 */

package edu.umsl.tsfn88.cs4012.project1.dao;

import java.util.List;

interface DAO<T> {

    List<T> getAll();

    void insert(T t);

    void update(T t);

    void delete(T t);

}
