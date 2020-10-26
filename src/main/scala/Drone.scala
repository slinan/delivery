
import Direction.{Direction, East, North, South, West}

import scala.util.{Failure, Success, Try}


object Drone {

  val MaximumDistance = 10

  def findPosition(instructions: String, pX: Int, pY: Int, pDirection: Direction): Try[(Int, Int, Direction)] = {

    var x = pX
    var y = pY
    var direction = pDirection

    instructions.foreach {
      case Instruction.Move => direction match {
        case North => y = y + 1
        case South => y = y - 1
        case East => x = x + 1
        case West => x = x - 1
      }
      case Instruction.TurnLeft => direction = turnLeft(direction)
      case Instruction.TurnRight => direction = turnRight(direction)
    }
    if (Math.abs(x) <= MaximumDistance && Math.abs(y) <= MaximumDistance) {
      Success(x, y, direction)
    } else {
      Failure(new Exception("Drone.out.of.bounds"))
    }

  }

  def turnLeft(direction: Direction): Direction = direction match {
    case North => West
    case West => South
    case South => East
    case _ => North
  }

  def turnRight(direction: Direction): Direction = direction match {
    case North => East
    case East => South
    case South => West
    case _ => North
  }

}
