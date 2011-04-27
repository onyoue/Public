Builder := Object clone

OperatorTable addAssignOperator(":", "atPutNumber")

Map atPutNumber := method(
	self atPut(
		call evalArgAt(0) asMutable removePrefix("\"") removeSuffix("\""),
		call evalArgAt(1))
)

Builder curlyBrackets := method(
	r := Map clone
	call message arguments foreach(arg,
		r doMessage(arg)
		)
	r
)



Builder indent := 0
Builder writeIndent := method(
	for(i, 1, indent, write("  ")))
Builder forward := method(
	writeIndent()
	attr := self doMessage(call message arguments at(0))
	if(attr type == "Map", 
		writeln("<", call message name, " ", 
			attr keys foreach(arg, write(arg, ":\"", attr at(arg), "\" ")), ">");
		call message arguments removeAt(0),
		writeln("<", call message name, ">")
	)
	call message arguments foreach(
		arg,
		indent = indent + 1;
		content := self doMessage(arg);
		if(content type == "Sequence", writeIndent(); writeln(content));
		indent = indent - 1)
	writeIndent()
	writeln("</", call message name, ">"))

