package net.hiddevb.advent.advent2019.day03

data class Piece(val firstPoint: Point, val secondPoint: Point, val alignment: Alignment) {

    constructor(firstPoint: Point, secondPoint: Point) : this(
            firstPoint,
            secondPoint,
            if (firstPoint.y == secondPoint.y) Alignment.HORIZONTAL else Alignment.VERTICAL
    )

    fun getCrossingPoint(other: Piece): Point? {
        if (crosses(other)) {
            return if (alignment == Alignment.HORIZONTAL) {
                Point(firstPoint.y, other.firstPoint.x)
            } else {
                Point(other.firstPoint.y, firstPoint.x)
            }
        }
        return null
    }

    private fun crosses(other: Piece): Boolean {
        if (alignment != other.alignment) {
            return if (alignment == Alignment.HORIZONTAL) {
                this.crossesHorizontal(other)
            } else {
                other.crossesHorizontal(this)
            }
        }
        return false
    }

    private fun crossesHorizontal(other: Piece): Boolean =
            firstPoint.x <= other.firstPoint.x && other.firstPoint.x <= secondPoint.x
                    && other.firstPoint.y <= firstPoint.y && firstPoint.y <= other.secondPoint.y
}

data class Point(val x: Int, val y: Int)

enum class Alignment {
    HORIZONTAL, VERTICAL
}