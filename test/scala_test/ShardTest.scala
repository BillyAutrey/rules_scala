package test.scala_test

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class ShardTest extends AnyFlatSpec with Matchers {
  behavior of "ShardTest"

  it should "run as shard 1" in {
    println("Shard 1")
    1 shouldEqual (1)
  }

  it should "run as shard 2" in {
    println("Shard 2")
    2 shouldEqual (2)
  }

  it should "run as shard 3" in {
    println("Shard 3")
    2 shouldEqual (2)
  }
}
