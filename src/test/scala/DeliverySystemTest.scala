import org.scalatest.FunSuite

import scala.util.Success


class DeliverySystemTest extends FunSuite {

  test("DeliverySystem.readInstructions") {
    assert(DeliverySystem.readInstructions("./in01.txt") === Success("AAAAIAA\nDDDAIAD\nAAIADAD\n"))
  }
}
