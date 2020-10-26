import Direction.North
import Direction.West
import Direction.South
import Direction.East
import org.scalatest.FunSuite

import scala.util.{Failure, Success}

class DroneTest extends FunSuite {

  test("Drone.findPosition") {
    assert(Drone.findPosition("AAADAAIA", 0, 0, North) === Success(2, 4, North))
    assert(Drone.findPosition("AAADAAIA", 0, 0, North) === Success(2, 4, North))
    assert(Drone.findPosition("I", 0, 0, North) === Success(0, 0, West))
    assert(Drone.findPosition("I", 13, 43, South).failed.get.getMessage === "Drone.out.of.bounds")
    assert(Drone.findPosition("IAAAADAADIAAAIAAA", -4, 44, East).failed.get.getMessage === "Drone.out.of.bounds")
    assert(Drone.findPosition("AAAIAAADDAAAAA", 23, 1, West).failed.get.getMessage === "Drone.out.of.bounds")
  }

  test("Drone.turnLeft") {
    assert(Drone.turnLeft(North) === West)
    assert(Drone.turnLeft(West) === South)
    assert(Drone.turnLeft(South) === East)
    assert(Drone.turnLeft(East) === North)
  }

  test("Drone.turnRight") {
    assert(Drone.turnRight(North) === East)
    assert(Drone.turnRight(East) === South)
    assert(Drone.turnRight(South) === West)
    assert(Drone.turnRight(West) === North)
  }

}
