package com.luisfagundes.data.remote.models

data class InstructionResponse(
    val name: String,
    val steps: List<StepResponse>
)
