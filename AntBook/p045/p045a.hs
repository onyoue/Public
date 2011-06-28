module Main where
	-- p.45
	test = "ACDBCB"
	-- output = "ABCBCD"

	solve :: [Char] -> [Char] -> [Char]
	solve candidate [] = candidate
	solve candidate input
		| useLeft = solve (candidate ++ [head input]) (tail input)
		| otherwise = solve (candidate ++ [last input]) (take (length input - 1) input)
		where 
			reversed = reverse input
			useLeft = input <= reversed
	
	solveUtil input =
		solve "" input
