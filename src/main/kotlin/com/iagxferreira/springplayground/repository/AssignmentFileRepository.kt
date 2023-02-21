package com.iagxferreira.springplayground.repository

import com.iagxferreira.springplayground.model.AssignmentFile
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*


@Repository
    interface AssignmentFileRepository : JpaRepository<AssignmentFile, UUID> {
    fun readAssignmentFileByName(name: String): AssignmentFile
}