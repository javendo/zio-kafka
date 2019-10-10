import scala.concurrent.duration.{ Duration => ScalaDuration }
import zio.duration._
import pureconfig._
import pureconfig.ConvertHelpers._

package object configuration {
  val configuration = (new Configuration with Live).config
  implicit val zioDurationReader = ConfigReader.fromNonEmptyString[Duration](catchReadError(Duration.fromScala _ compose ScalaDuration.create))
  implicit val anyRefReader = ConfigReader.fromNonEmptyString[AnyRef](catchReadError(_.toString))
}
