module Main where
	import Data.Char
	qsort [] = []
	qsort (h:t) = qsort l ++ [h] ++ qsort r
		where 
			l = [x | x <- t, x <= h]
			r = [x | x <- t, x > h]

	qsortf comp [] = []
	qsortf comp (h:t) = qsortf comp l ++ [h] ++ qsortf comp r
		where 
			l = [x | x <- t, comp x h <= 0]
			r = [x | x <- t, comp x h > 0]
	
	comp0 a b = if a <= b then -1 else 1
	comp1 a b = if a > b then -1 else 1

	atoi :: [Char] -> Int
	atoi s = foldl (\x carryOver -> carryOver + x * 10)
		0
		(map digitToInt (filter isDigit s))
