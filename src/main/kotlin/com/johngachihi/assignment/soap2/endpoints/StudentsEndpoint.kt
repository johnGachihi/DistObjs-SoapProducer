package com.johngachihi.assignment.soap2.endpoints

import com.johngachihi.assignment.soap2.utils.toJaxbStudent
import com.johngachihi.assignment.soap2.repositories.StudentsRepository
import com.johngachihi.assignment.soap2.utils.toDBStudentEntity
import com.johngachihi.dist_objs.soap.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.ws.server.endpoint.annotation.Endpoint
import org.springframework.ws.server.endpoint.annotation.PayloadRoot
import org.springframework.ws.server.endpoint.annotation.RequestPayload
import org.springframework.ws.server.endpoint.annotation.ResponsePayload

@Endpoint
class StudentsEndpoint {

    @Autowired
    lateinit var studentsRepo: StudentsRepository

    @PayloadRoot(
        namespace = "http://johngachihi.com/dist-objs/soap",
        localPart = "getStudentRequest"
    )
    @ResponsePayload
    fun getStudent(
        @RequestPayload requestPayload: GetStudentRequest
    ) = GetStudentResponse().apply {
        val student = studentsRepo.findById(requestPayload.admissionNumber)

        student.ifPresent {
            setStudent(it.toJaxbStudent())
        }
    }

    @PayloadRoot(
        namespace = "http://johngachihi.com/dist-objs/soap",
        localPart = "registerStudentRequest"
    )
    @ResponsePayload
    fun registerStudent(
        @RequestPayload requestPayload: RegisterStudentRequest
    ) = RegisterStudentResponse().apply {
        val student = toDBStudentEntity(requestPayload.student)
        studentAdmissionNumber = studentsRepo.save(student).id!!
    }
}