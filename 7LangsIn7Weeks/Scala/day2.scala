val a = List("test", "alskdjf", "word")
println(a.foldLeft(0)((sum, value) => sum + value.size))

