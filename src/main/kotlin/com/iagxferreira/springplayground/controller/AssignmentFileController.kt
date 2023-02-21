package com.iagxferreira.springplayground.controller

import com.iagxferreira.springplayground.service.AssignmentFileService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile


@RestController("files")
class AssignmentFileController {
    @Autowired
    private val assignmentService: AssignmentFileService? = null
    @PostMapping("/upload")
    fun uploadAssignment(@RequestParam("assignment") assignment: MultipartFile?): ResponseEntity<String> {
        val response = assignment?.let { assignmentService?.uploadAssignment(it) }
        return ResponseEntity.status(HttpStatus.OK).body(response)
    }

    @GetMapping("/download/{fileName}")
    fun downloadAssignment(@PathVariable fileName: String): ResponseEntity<*> {
        val response: ByteArray? = assignmentService?.downloadAssignment(fileName)
        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType("application/pdf"))
            .header(
                HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"$fileName\""
            )
            .body(response)
    }
}