module Main where
import Array

-- w=7
-- (w,v) = [(3,4), (4,5), (2,3)]
-- output = 10

dp :: Int -> Int -> [Int] -> [Int] -> Array (Int, Int) Int
dp n w values weights = 
	a where
		a = array ((0, 0), (n, w))
			([((0,j), 0) | j <- [0..w]] ++
			 [((i,0), 0) | i <- [0..n]] ++
			 [((i,j), 
				if (j < (weights !! (i-1)))
					then (a!(i-1,j))
					else max (a!(i-1,j)) (a!(i, j-(weights !! (i-1))) + (values !! (i-1))))
					| i <- [1..n], j <- [1..w]])
weights0 :: [Int]
weights0 = [3, 4, 2]
values0 :: [Int]
values0 = [4, 5, 3]

solve = (dp 3 7 values0 weights0) ! (3,7)

main :: IO ()
main = putStrLn "Hello World"
