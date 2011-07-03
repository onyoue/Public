module Main where
import Array

dp :: Int -> Int -> [Int] -> [Int] -> Array (Int, Int) Int
dp n w values weights = 
	a where
		a = array ((0, 0), (n, w))
			([((0,j), 0) | j <- [0..w]] ++
			 [((i,0), 0) | i <- [0..n]] ++
			 [((i,j), 
				if (j < (weights !! (i-1)))
					then (a!(i-1,j))
					else max (a!(i-1,j)) (a!(i-1, j-(weights !! (i-1))) + (values !! (i-1))))
					| i <- [1..n], j <- [1..w]])

values0 :: [Int]
values0 = [3, 2, 4, 2]
weights0 :: [Int]
weights0 = [2, 1 ,3, 2]
-- w = 5
-- output = 7 (7, 1, 3)


main :: IO ()
main = putStrLn "Hello World"
