package com.johngachihi.assignment.soap2.models

import javax.persistence.*

@Entity
data class Student(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int?,
    val name: String,
    val email: String,
    val phoneNumber: String,
    val address: String,
    val entryPoints: String
)