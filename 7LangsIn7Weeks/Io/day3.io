Builder := Object clone

Builder indent := 0
Builder writeIndent := method(
	for(i, 1, indent, write("  ")))
Builder forward := method(
	writeIndent()
	writeln("<", call message name, ">")
	call message arguments foreach(
		arg,
		indent = indent + 1;
		content := self doMessage(arg);
		if(content type == "Sequence", writeIndent(); writeln(content));
		indent = indent - 1)
	writeIndent()
	writeln("</", call message name, ">"))
	
Builder ul(
	li("Io"),
	li("Lua"),
	li("javaScript"))
