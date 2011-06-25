module Main where
	-- Lake Counting (p.35)
	width :: Int
	width = 12
	height :: Int
	height = 10
	
	type Pos = (Int, Int)
	type Water = [Pos]
	
	test :: Water
	test = [(0,0), (9,0), (10,0),
		(1,1), (2,1), (3,1), (9,1), (10,1), (11,1),
		(4,2), (5,2), (9,2), (10,2),
		(9,3), (10,3),
		(9,4),
		(2,5), (9,5),
		(1,6), (3,6), (9,6), (10,6),
		(0,7), (2,7), (4,7), (10,7),
		(1,8), (3,8), (10,8),
		(2,9), (10,9)]
	
	bound :: Pos -> Bool
	bound (x, y) = x >= 0 && y >= 0 && x < width && y < height
	
	neighbs :: Pos -> [Pos]
	neighbs (x, y) = filter bound [(x-1,y-1), (x,y-1),
									(x+1,y-1), (x-1,y),
									(x+1,y), (x-1,y+1),
									(x,y+1), (x+1,y+1)]
	
	isNeighbor :: Pos -> Pos -> Bool
	isNeighbor input pos = elem input (neighbs pos)
	
	isOneOfNeighbors :: Pos -> [Pos] -> Bool
	isOneOfNeighbors input [] = False
	isOneOfNeighbors input (p:ps) = 
		if isNeighbor input p
			then True
			else isOneOfNeighbors input ps

	getLake :: Water -> Water -> Water
	getLake lake [] = lake
	getLake lake (w:ws) =
		if isOneOfNeighbors w lake
			then getLake (w:lake) ws
			else getLake lake ws
			
	getLakes :: Water -> Water -> Water -> [Water]-> [Water]
	getLakes lake [] [] result = (lake : result)
	getLakes lake [] (r:rest) result = 
		getLakes [r] rest [] (lake : result)
	getLakes lake (w:ws) rest result =
		if isOneOfNeighbors w lake
			then getLakes (w:lake) ws rest result
			else getLakes lake ws (w:rest) result

	solve :: Water -> [Water]
	solve (w:ws) = getLakes [w] ws [] []

	