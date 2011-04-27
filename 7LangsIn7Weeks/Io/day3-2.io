curlyBrackets := method(
	r := List clone
	call message arguments foreach(arg,
		r append(arg)
		)
	r
)
