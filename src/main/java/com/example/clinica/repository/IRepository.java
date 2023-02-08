package com.example.clinica.repository;

import com.example.clinica.domain.Entity;

import java.util.List;

public interface IRepository<Integer, E extends Entity<Integer>> {
    List<E> findAll();
    E save (E e);
}
