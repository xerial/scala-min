package scalamin

import xerial.core.log.LogLevel


//--------------------------------------
//
// HelloTest.scala
// Since: 2012/11/20 12:57 PM
// 
//--------------------------------------

/**
 * To run the this test suite, do:
 * {{{
 *   bin/sbt "~test-only *HelloTest"
 * }}}
 */
class HelloTest extends MySpec {

  "Hello" should {
    "have main" in {
      Hello.main(Array.empty)
    }

    "display log messages" in {
      // To see the log messages higher than the debug level,
      // launch test with`bin/sbt "~test-only *HelloTest" -Dloglevel=debug`
      trace("trace log")
      debug("debug log")
      info("info log")
      warn("warning")
      error("error")
      fatal("fatal error")
    }

    "display formatted logs" in {
      info("Hello %s!!", "World")
      info("Floating point value: pi = %.10f, rad=%.3e", math.Pi, math.toRadians(math.Pi))
    }

    "test something" in {
      val a = Array(1, 2, 3, 4)
      debug("contents of a:[%s]", a.mkString(", "))

      a.size should be (4)
      a(0) should be (1)
      a.find(_ == 2) should be ('defined)

      a should be (Array(1, 2, 3, 4))
    }

    "add a tag to test" taggedAs("test1") in {
      // You can run only this test with "bin/sbt "~test-only *HelloTest -- include(test1)"
      debug("test1 is running")
    }

    "measure the code block performance" in {

      val N = 100000
      val R = 100

      time("loop", repeat=10, logLevel=LogLevel.INFO) {
        block("for", repeat=R) {
          for(i <- 0 until N) {
            // do nothing
          }
        }

        block("while", repeat=R) {
          var i = 0
          while(i < N) {
            // do nothing
            i += 1
          }
        }
      }
    }

    "measure the parallel collection pefromance" in {

      val a = Array.ofDim[Int](100000)
      val R = 10

      def multiply(e:Int) = e * e

      time("array ops", repeat=10, logLevel=LogLevel.INFO) {
        block("single-core", repeat=R) {
          a.map ( multiply )
        }

        block("multi-core", repeat=R) {
          a.par.map ( multiply )
        }
      }

    }

  }

}
