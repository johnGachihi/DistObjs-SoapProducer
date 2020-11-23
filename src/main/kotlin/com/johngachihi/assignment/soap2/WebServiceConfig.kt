package com.johngachihi.assignment.soap2

import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.ws.config.annotation.EnableWs
import org.springframework.ws.config.annotation.WsConfigurerAdapter
import org.springframework.ws.transport.http.MessageDispatcherServlet
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition
import org.springframework.xml.xsd.SimpleXsdSchema
import org.springframework.xml.xsd.XsdSchema

@EnableWs
@Configuration
class WebServiceConfig: WsConfigurerAdapter() {

    @Bean
    fun messageDispatcherServlet(
        applicationContext: ApplicationContext
    ): ServletRegistrationBean<MessageDispatcherServlet> {
        val servlet = MessageDispatcherServlet()
        servlet.setApplicationContext(applicationContext)
        servlet.isTransformWsdlLocations = true

        return ServletRegistrationBean(servlet, "/ws/*")
    }

    @Bean(name = ["students"])
    fun defaultWsdl11Definition2(studentsSchema: XsdSchema): DefaultWsdl11Definition =
        DefaultWsdl11Definition().apply {
            setPortTypeName("StudentsPort")
            setLocationUri("/ws")
            setTargetNamespace("http://johngachihi.com/dist-objs/soap")
            setSchema(studentsSchema)
        }

    @Bean
    fun studentsSchema() = SimpleXsdSchema(ClassPathResource("students.xsd"))
}