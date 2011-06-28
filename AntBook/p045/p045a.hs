module Main where
	-- p.45
	test = "ACDBCB"
	-- output = "ABCBCD"

	solve :: [Char] -> [Char]
	solve [] = []
	solve input
		| useLeft = ((head input) : solve (tail input))
		| otherwise = ((last input) : solve (take (length input - 1) input))
		where 
			reversed = reverse input
			useLeft = input <= reversed
