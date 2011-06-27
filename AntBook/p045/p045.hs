module Main where
	-- p.45
	test = "ACDBCB"
	-- output = "ABCBCD"

	update :: [Char] -> [([Char], [Char])] -> [Char]
	update candidate ((output, input):elements)
		| (left < right) = solve newCandidate (elements ++ [leftElement])
		| (left > right) = solve newCandidate (elements ++ [rightElement])
		| otherwise = solve newCandidate (elements ++ leftElement:rightElement:[])
		where 
			left = head input
			right = last input				
			leftElement = (output ++ [left], tail input)
			rightElement = (output ++ [right], take (length input - 1) input)
			newCandidate = output ++ [left]
	
	solve :: [Char] -> [([Char], [Char])] -> [Char]
	solve candidate [] = candidate
	solve candidate ((output, []):elements) = output
	solve candidate ((output, input):elements) =
		if (take (length candidate) output <= candidate)
		then
			update candidate ((output, input):elements)
		else
			solve candidate elements	

	solveUtil input =
		solve "" [("", input)]
