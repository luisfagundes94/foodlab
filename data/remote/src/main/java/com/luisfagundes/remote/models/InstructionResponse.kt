package com.luisfagundes.data.responses

data class InstructionResponse(
    val name: String,
    val steps: List<StepResponse>
)
