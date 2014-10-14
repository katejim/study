/**
 * Created by kate on 07.10.14.
 */
object Task1 extends App {
  val greetStrings = new Array[String](3)
  greetStrings(0) = "Hello"
  greetStrings(1) = ", "
  greetStrings(2) = "world!\n"
  greetStrings(0) = "sdfsd"

  def func(temp: Array[String]): Unit = {
    temp(1) = "sdfs"
    temp.foreach(println)
  }

  val numbers = List.apply(0,1,5,2,3)

  Console println  10

  func(greetStrings)
  numbers.foreach(println)

}
