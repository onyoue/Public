module Main where

	import List
	
	solve :: [Char] -> Bool
	solve [] = True
	solve [a] = True
	solve (a:b:chars) 
		| a == b = False
		| otherwise = solve (b:chars)
	
	isAllUnique input = solve (sort input)