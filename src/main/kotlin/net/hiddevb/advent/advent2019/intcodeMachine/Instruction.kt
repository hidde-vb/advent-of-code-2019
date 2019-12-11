package net.hiddevb.advent.advent2019.intcodeMachine

data class Instruction(val optCode: Int,
                       val size: Int,
                       val function: (pointer: Int, intCodes: Array<Int>) -> Array<Int>) {

    fun execute(intCodes: Array<Int>, pointer: Int) = function(pointer, intCodes)

    fun nextInstruction(current: Int): Int = current + size
}

fun getDefaultInstructionList(): List<Instruction> {
    return listOf(
            Instruction(1, 4) { pointer: Int, intCodes: Array<Int> ->
                intCodes[intCodes[pointer + 3]] = intCodes[intCodes[pointer + 1]] + intCodes[intCodes[pointer + 2]]
                intCodes
            },
            Instruction(2, 4) { pointer: Int, intCodes: Array<Int> ->
                intCodes[intCodes[pointer + 3]] = intCodes[intCodes[pointer + 1]] * intCodes[intCodes[pointer + 2]]
                intCodes
            },
            Instruction(99, 1) { pointer: Int, intCodes: Array<Int> ->
                println("DONE: ${intCodes[0]} for ${intCodes[1]},${intCodes[2]}")
                intCodes
            })
}