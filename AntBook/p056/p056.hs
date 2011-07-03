module Main where
import Array
-- longest common subsequence
s0 = "abcd"
t0 = "becd"
-- output = 3 ("bcd")

dp :: [Char] -> [Char] -> Int -> Int -> Array (Int, Int) Int
dp s t m n = 
	a where
		a = array ((0, 0), (m, n))
			([((0,j), 0) | j <- [0..n]] ++
			 [((i,0), 0) | i <- [0..m]] ++
			 [((i,j), 
				if ((s !! (i-1)) == (t !! (j-1)))
				then (a!(i-1,j-1)) + 1
				else max (a!(i-1,j)) (a!(i, j-1)))
				| i <- [1..m], j <- [1..n]])
					
main :: IO ()
main = putStrLn "Hello World"
