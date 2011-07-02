module Main where

-- Fence Repair (p.49)
-- sorted list
test = [5, 8, 8]
-- minimum cost to cut a board
-- output = 34

firstSecond (first, second) [] rest = (first:second:rest)
firstSecond (first, second) (cost:costs) rest 
	| cost < first = firstSecond (cost, first) costs (second:rest)
	| cost < second = firstSecond (first, cost) costs (second:rest)
	| otherwise = firstSecond (first, second) costs (cost:rest)

mincut cost [] = cost
mincut cost [a] = cost
mincut cost (a:b:costs) =
	mincut (cost + first + second) newCosts
	where
		(first:second:rest) = if (a < b) 
								then firstSecond (a, b) costs []
								else firstSecond (b, a) costs []
		newCosts = ((first + second):rest)

solve input =
	mincut 0 input

main :: IO ()
-- main = print [1, 2, 3]
main = putStrLn "Hello World"
