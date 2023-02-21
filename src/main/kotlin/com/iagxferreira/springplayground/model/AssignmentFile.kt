package com.iagxferreira.springplayground.model

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import java.util.UUID

@Entity
@Table(name = "assignments")
data class AssignmentFile(
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "BINARY(16)")
    var id: UUID,

    @Column(name = "name", columnDefinition = "VARCHAR(50)")
    var name: String,

    @Column(name = "content", columnDefinition = "BLOB")
    var content: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AssignmentFile

        if (id != other.id) return false
        if (name != other.name) return false
        if (!content.contentEquals(other.content)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + content.contentHashCode()
        return result
    }
}