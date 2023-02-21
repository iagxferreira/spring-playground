package com.iagxferreira.springplayground.service

import com.iagxferreira.springplayground.model.AssignmentFile
import com.iagxferreira.springplayground.repository.AssignmentFileRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.util.*


@Service
class AssignmentFileService(
    @Autowired
    val assignmentRepository: AssignmentFileRepository
) {
    fun uploadAssignment(request: MultipartFile): String {
        return try {
            val assignment = request.originalFilename?.let {
                AssignmentFile(id = UUID.randomUUID(), name = it, content = request.bytes) }
            if (assignment != null) {
                assignmentRepository.save(assignment)
            }
            "Assignment submitted successfully"
        } catch (ex: IOException) {
            throw RuntimeException(ex)
        }
    }

    fun downloadAssignment(name: String): ByteArray? {
        val assignment = assignmentRepository.readAssignmentFileByName(name)
        return assignment.content
    }
}