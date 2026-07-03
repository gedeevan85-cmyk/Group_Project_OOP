/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.List;
/**
 *
 * @author gedee
 */
public interface InterfaceDAO<T> {
    void save(T object);

    void update(T object);

    void delete(int id);

    T findById(int id);

    List<T> findAll();
}
