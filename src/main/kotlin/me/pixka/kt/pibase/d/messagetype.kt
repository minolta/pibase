package me.pixka.kt.pibase.d

import me.pixka.kt.base.d.En
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import javax.persistence.Column
import javax.persistence.Entity

@Entity
class MessageType(var name: String,
                  @Column(columnDefinition = "text") var description: String? = null) : En() {

    constructor():this(name="")
}


@Repository
interface MessageTypeRepo : JpaRepository<MessageType, Long> {
    @Query("from MessageType mt where mt.name like %?1%")
    abstract fun search(search: String, topage: Pageable): List<MessageType>?

    abstract fun findByName(n: String): MessageType?
}