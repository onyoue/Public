import scala.io.Source

var replaceDictionary = Map("hello" -> "hi!")
val dict = Source.fromFile("dict")
val dictLines = dict.getLines
dictLines.foreach(line => {
	val pair = line.split(" ")
	replaceDictionary += (pair(0) -> pair(1))
	})
dict.close

def replaceWord(word:String) = {
	replaceDictionary.getOrElse(word, word)
}

val source = Source.fromFile("test")
val lines = source.getLines
val replacedLines = 
	lines.map(line => 
		line.split(" ").map(replaceWord).reduceLeft(_ + " " + _))

replacedLines.foreach(println)
source.close
