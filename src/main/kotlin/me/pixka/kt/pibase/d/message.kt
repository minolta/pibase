package me.pixka.kt.pibase.d

import me.pixka.kt.base.d.En
import me.pixka.pibase.d.PiDevice
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.ManyToOne
import javax.transaction.Transactional

@Entity
class Message(@ManyToOne var messagetype: MessageType? = null,
              @Column(insertable = false, updatable = false) var messagetype_id: Long? = null,
              var source: String? = null, var message: String? = null,
              var openstatus: Boolean? = null, @ManyToOne var pidevice: PiDevice? = null,
              @Column(insertable = false, updatable = false) var pidevice_id: Long? = null,
              var gid: Long? = null, var messagedate: Date? = null, var toserver: Boolean? = null,
              var endevent: Date? = null) : En() {
    constructor() : this(openstatus = true, toserver = false)

    override fun toString(): String {
        return "${message} ${messagetype} ${pidevice} $messagedate"
    }
}

@Repository
interface MessageRepo : JpaRepository<Message, Long> {
    @Query("from Message m where m.source like %?1% or m.message like %?1% order by m.id desc")
    fun search(search: String, topage: Pageable): List<Message>?

    @Query("from Message m where m.toserver = ?1")
    fun notsend(b: Boolean, topage: Pageable): List<Message>?

    @Modifying
    @Transactional
    @Query("delete from Message d where d.toserver = true")
    fun deleteBySend()

}