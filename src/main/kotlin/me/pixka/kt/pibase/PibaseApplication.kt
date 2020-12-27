package me.pixka.kt.pibase

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableAsync
@EnableScheduling
@ComponentScan(basePackages = arrayOf("me.pixka"))
class PibaseApplication

fun main(args: Array<String>) {
	runApplication<PibaseApplication>(*args)
}
