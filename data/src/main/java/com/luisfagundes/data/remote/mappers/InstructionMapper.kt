package com.luisfagundes.data.remote.mappers

import com.luisfagundes.data.local.models.InstructionEntity
import com.luisfagundes.data.local.models.StepEntity
import com.luisfagundes.data.remote.models.InstructionResponse
import com.luisfagundes.data.remote.models.StepResponse
import com.luisfagundes.domain.models.Instruction
import com.luisfagundes.domain.models.Step
import java.util.UUID

object InstructionMapper {
    @JvmName("mapToDomainInstructionResponse")
    fun List<InstructionResponse>.mapToDomain(): List<Instruction> =
        this.map { it.toDomain() }

    fun InstructionEntity.toDomainModel(): Instruction {
        return Instruction(
            id = this.id,
            name = this.name,
            steps = this.steps.map { it.toDomain() }
        )
    }

    fun Instruction.toEntityModel(): InstructionEntity {
        return InstructionEntity(
            id = this.id,
            name = this.name,
            steps = this.steps.map { it.toEntity() }
        )
    }

    private fun InstructionResponse.toDomain() = Instruction(
        id = UUID.randomUUID().hashCode(),
        name = this.name,
        steps = this.steps.map { it.toDomain() }
    )

    private fun Step.toEntity() = StepEntity(
        id = this.id,
        step = this.step,
        number = this.number
    )

    private fun StepResponse.toDomain() = Step(
        id = UUID.randomUUID().hashCode(),
        step = this.step,
        number = this.number
    )

    private fun StepEntity.toDomain() = Step(
        id = this.id,
        step = this.step,
        number = this.number
    )
}
