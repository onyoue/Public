module Main where
	-- input [2, 3, 4, 5, 10] output 12 (3, 4, 5 is selected)
	-- input [4, 5, 10, 20] output 0 (no triangle generated)
	-- 3 <= n <= 100 (number of candidates)
	-- 1 <= ai <= 10^6 (length of an edge)
	-- input should be sorted

	triangles es = [(a,b,c) | a<-es, b<-es, c<-es, a < b, b < c, a+b>c]	
	maxTriangleLength edgeLengths = 
		if length triangleLengths == 0
			then 0
			else maximum triangleLengths
			where
				triangleLengths = map (\(a,b,c) -> a+b+c) (triangles edgeLengths)
	
		