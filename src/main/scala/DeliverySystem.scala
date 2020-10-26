import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Paths}

import Direction.{Direction, East, North, South, West}
import com.osinka.i18n.{Lang, Messages}

import scala.io.Source
import scala.util.{Failure, Success, Try, Using}

object DeliverySystem extends App {

  val InitialDronePositionX = 0
  val InitialDronePositionY = 0
  val InitialDroneOrientation = Direction.North
  implicit val UserLang: Lang = Lang("es")

  println("Starting system")
  args.map(a => {
    println(s"Processing ${a}")
    val reportName = a.replace("in", "out")
    readInstructions(a) match {
      case Success(instructions) => buildDroneReport(instructions, reportName)
      case Failure(exception) => println(exception)
    }
  })

  def buildDroneReport(instructions: String, reportName: String): Unit = {
    var x = InitialDronePositionX
    var y = InitialDronePositionY
    var direction = InitialDroneOrientation
    writeDroneReport(
      Messages("Delivery.report") +
        instructions
          .split("\n")
          .map(i => {
            Drone.findPosition(i, x, y, direction) match {
              case Success(position) =>
                x = position._1
                y = position._2
                direction = position._3
                position
              case Failure(exception) =>
                exception.getMessage
            }
          })
          .map {
            case (x: Int, y: Int, d: Direction) => localizeReport(x, y, d)
            case error: String => Messages(error)
          }
          .mkString("\n"), reportName
    )
  }

  def readInstructions(file: String): Try[String] = {
    Using(Source.fromFile(file)) { source => source.mkString }
  }

  def writeDroneReport(report: String, reportName: String): Unit = {
    if(report.split("\n").length > 3+1) {
      Files.write(Paths.get(reportName), Messages("Drone.capacity.exceeded").getBytes(StandardCharsets.UTF_8))
    } else {
      Files.write(Paths.get(reportName), report.getBytes(StandardCharsets.UTF_8))
    }
  }

  def localizeReport(x: Int, y: Int, d: Direction): String = {
    val direction = Messages(d.toString)
    Messages("Report", x, y, direction)
  }

}
