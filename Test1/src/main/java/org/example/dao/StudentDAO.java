package org.example.dao;

import org.example.model.Student;

import java.util.List;

public class StudentDAO implements DAOInterface<Student> {

    private List<Student> students;

    @Override
    public List<Student> getAll() {
        return null;
    }

    @Override
    public Student getById(int id) {
        return null;
    }

    @Override
    public void add(Student entity) {

    }

    @Override
    public void update(Student entity) {

    }

    @Override
    public void delete(int id) {

    }
}
