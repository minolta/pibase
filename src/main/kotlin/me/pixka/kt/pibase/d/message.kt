package me.pixka.kt.pibase.d

import me.pixka.kt.base.d.En
import me.pixka.pibase.d.PiDevice
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ManyToOne

@Entity
class Message(@ManyToOne var messagetype: MessageType? = null,
              @Column(insertable = false, updatable = false) var messagetype_id: Long? = null,
              var source: String? = null, var message: String? = null,
              var openstatus: Boolean? = null, @ManyToOne var pidevice: PiDevice? = null,
              @Column(insertable = false, updatable = false) var pidevice_id: Long? = null,
              var gid: Long? = null, var messagedate: Date? = null) : En() {
    constructor() : this(openstatus = true)
}

@Repository
interface MessageRepo : JpaRepository<Message, Long> {
    @Query("from Message m where m.source like %?1%")
    abstract fun search(search: String, topage: Pageable): List<Message>?
}