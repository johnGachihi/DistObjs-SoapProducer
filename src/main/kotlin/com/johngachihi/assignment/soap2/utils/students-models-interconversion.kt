package com.johngachihi.assignment.soap2.utils

import com.johngachihi.assignment.soap2.models.Student

fun Student.toJaxbStudent() =
    com.johngachihi.dist_objs.soap.Student().also {
        it.admissionNumber = id!!
        it.name = name
        it.email = email
        it.address = address
        it.phoneNumber = phoneNumber
        it.entryPoints = entryPoints
    }

fun toDBStudentEntity(
    jaxbStudent: com.johngachihi.dist_objs.soap.Student
): Student = Student(
    jaxbStudent.admissionNumber,
    jaxbStudent.name,
    jaxbStudent.email,
    jaxbStudent.phoneNumber,
    jaxbStudent.address,
    jaxbStudent.entryPoints
)