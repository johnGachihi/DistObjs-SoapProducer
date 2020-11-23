package com.johngachihi.assignment.soap2.repositories

import com.johngachihi.assignment.soap2.models.Student
import org.springframework.data.repository.CrudRepository

interface StudentsRepository: CrudRepository<Student, Int>