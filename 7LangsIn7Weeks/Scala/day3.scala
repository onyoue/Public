import scala.io._
import scala.actors._
import Actor._

object PageLoader {
	def getPageSize(url : String) = Source.fromURL(url).mkString.length
	val reg = """[aA]\shref=""".r
	def countLink(url : String) = reg.findAllIn(Source.fromURL(url).mkString).length
}

val urls = List("http://www.amazon.com/",
				"http://www.twitter.com/",
				"http://www.google.com/")
				
def timeMethod(method: () => Unit) = {
	val start = System.nanoTime
	method()
	val end = System.nanoTime
	println("Method took " + (end - start) / 1000000000.0 + " seconds.")
}

def getPageSizeSequentially() = {
	for (url <- urls) {
		println("Size for " + url + ": " + PageLoader.getPageSize(url))
		println("countLink for " + url + ": " + PageLoader.countLink(url))
	}
}

def getPageSizeConcurrently() = {
	val caller = self
	
	for (url <- urls) {
		actor {
			caller ! ("PageSize", url, PageLoader.getPageSize(url))
			caller ! ("LinkCount", url, PageLoader.countLink(url))
		}
	}
	
	for (i <- 1 to urls.size * 2) {
		receive {
			case ("PageSize", url, size) =>
				println("Size for " + url + ": " + size)
			case ("LinkCount", url, size) =>
				println("LinkCount for " + url + ": " + size)
		}
	}
}

println("Sequential run:")
timeMethod { getPageSizeSequentially }

println("Concurrent run:")
timeMethod { getPageSizeConcurrently }
