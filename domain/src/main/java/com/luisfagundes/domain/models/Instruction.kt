package com.luisfagundes.domain.models

data class Instruction(
    val id: Int,
    val name: String,
    val steps: List<Step>
)
