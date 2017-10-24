package me.pixka.pibase.r

import java.math.BigDecimal
import java.util.Date

import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

import me.pixka.pibase.d.Pijob

@Repository
interface PijobRepo : JpaRepository<Pijob, Long> {

    fun findByRefid(id: Long?): Pijob

    fun findByPidevice_id(id: Long?): List<*>

    @Query("from Pijob pj where (pj.hlow <= ?1 and pj.hhigh >=?1) and (pj.tlow <= ?2 and pj.thigh >=?2)  and pj.job_id = ?3 and (pj.stime is null and pj.etime is null)")
    fun findByHT(h: BigDecimal, t: BigDecimal, id: Long?): List<*>

    /**
     * สำหรับ ค้นหา แต่ค่า h อย่างเดียว
     *
     * @param h
     * @param id
     * @param b
     * @return
     */
    @Query("from Pijob pj where (pj.hlow <= ?1 and pj.hhigh >=?1) and pj.job_id = ?2 and (pj.stime is null and pj.etime is null)  and pj.enable = ?3")
    fun findByH(h: BigDecimal, id: Long?, b: Boolean): List<*>

    @Query("from Pijob pj where (pj.tlow <= ?1 and pj.thigh >=?1)  and pj.job_id = ?2 and (pj.stime is null and pj.etime is null) and pj.enable = ?3")
    fun findByT(t: BigDecimal, jobtypeid: Long?, b: Boolean): List<*>

    @Query("from Pijob pj where (pj.tlow <= ?1 and pj.thigh >=?1) and pj.job_id = ?2" + " and (pj.stime is null and pj.etime is null) ")
    fun findByDS(t: BigDecimal, jobtypeid: Long?): List<*>

    @Query("from Pijob pj where (pj.tlow <= ?1 and pj.thigh >=?1) and pj.job_id = ?2" + " and (pj.stime is null and pj.etime is null and pj.enable = ?3) ")
    fun findByDS(t: BigDecimal, jobtypeid: Long?, e: Boolean?): List<*>

    @Query("from Pijob pj order by pj.tlow  ")
    fun fintAllOrderByT(page: Pageable): List<*>

    @Query("from Pijob pj where (pj.tlow <= ?1 and pj.thigh >=?1)  and pj.job_id = ?3 and (pj.stime >= ?2 and pj.etime <= ?2)")
    fun findByDS(t: BigDecimal, time: Date, jobid: Long?): List<*>

    fun findByJob_id(id: Long?): List<*>

    @Query("from Pijob pj where pj.pidevice_id = ?1")
    fun searchByDeviceid(id: Long?, page: Pageable): List<*>

    /**
     * สำหรับค้นหา one command
     *
     * @param b
     * @return
     */
    @Query("from Pijob pj where pj.job_id = null and pj.enable = true")
    fun findByOne(b: Boolean): List<*>
    @Query("from Pijob p where p.pidevice.name like %?1% or p.name like %?1% ")
    fun search(search: String, topage: Pageable): List<Pijob>?

}
