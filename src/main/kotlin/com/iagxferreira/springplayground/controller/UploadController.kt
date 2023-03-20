package com.iagxferreira.springplayground.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object
import com.amazonaws.util.IOUtils
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.*
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.support.StandardServletMultipartResolver

import java.io.File;
import java.io.InputStream

@RestController
class UploadController(
    val amazonS3: AmazonS3,
    val multipartResolver: StandardServletMultipartResolver
){
    val bucketName = "spring-playground-bucket"
    @PostMapping("/s3/upload")
    fun uploadFileToS3(request: HttpServletRequest): String {
        val multipartFile: MultipartFile? = multipartResolver.resolveMultipart(request).fileMap.values.firstOrNull()
        multipartFile?.let {
            val key = it.originalFilename

            val metadata = ObjectMetadata()
            metadata.contentType = it.contentType
            metadata.contentLength = it.size

            amazonS3.putObject(bucketName, key, it.inputStream, metadata)

            return "File uploaded successfully to S3!"
        }

        throw RuntimeException("No file was received in the request.")
    }

    @GetMapping("s3/download/{filename}")
    fun downloadFileFromS3(@PathVariable("filename") filename: String): ResponseEntity<ByteArray> {
        val s3Object: S3Object = amazonS3.getObject(bucketName, filename)
        val inputStream: InputStream = s3Object.objectContent
        val byteArray = IOUtils.toByteArray(inputStream)

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_OCTET_STREAM
        headers.contentDisposition = ContentDisposition.attachment().filename(filename).build()

        return ResponseEntity(byteArray, headers, HttpStatus.OK)
    }
}

