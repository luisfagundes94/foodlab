package com.luisfagundes.data.remote.mappers

import com.luisfagundes.data.remote.models.InstructionResponse
import com.luisfagundes.data.remote.models.StepResponse
import com.luisfagundes.domain.models.Instruction
import com.luisfagundes.domain.models.Step

object InstructionMapper {
    @JvmName("mapToDomainInstructionResponse")
    fun List<InstructionResponse>.mapToDomain(): List<Instruction> =
        this.map { it.toDomain() }

    private fun InstructionResponse.toDomain() = Instruction(
        name = this.name,
        steps = this.steps.mapToDomain()
    )

    @JvmName("mapToDomainStepResponse")
    private fun List<StepResponse>.mapToDomain(): List<Step> =
        this.map { it.toDomain() }

    private fun StepResponse.toDomain() = Step(
        step = this.step,
        number = this.number
    )
}
