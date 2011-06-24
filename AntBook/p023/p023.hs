module Main where
	-- L = 10 sao　length
	-- n = 3 number of ants
	-- x = {2, 6, 7} ant positions
	-- min = 4 (left, right, right) minimum time until all ants fall
	-- max = 8 (right, right, right) maximum time
	
	minToEdge len x = if x < x2 then x else x2
		where x2 = len - x
		
	maxToEdge len x = if x < x2 then x2 else x
		where x2 = len - x

	calcTime len positions edgeDistanceFunc =
		maximum (map (edgeDistanceFunc len) positions)
		
	minTime :: (Ord a, Num a) => a -> [a] -> a
	minTime len positions = 
		calcTime len positions minToEdge
		
	maxTime :: (Ord a, Num a) => a -> [a] -> a
	maxTime len positions = 
		calcTime len positions maxToEdge
	