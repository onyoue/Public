module Main where
	-- Maze (p.37)
	
	width :: Int
	width = 10
	height :: Int
	height = 10
	
	type Pos = (Int, Int)
	type Maze = [Pos]
	type Queue = [(Pos, Int)]
	
	test :: Maze
	test = [(8,0),
			(0,1), (1,1), (2,1), (3,1), (4,1), (5,1), (7,1), (8,1),
			(0,2), (2,2), (5,2), (8,2),
			(0,3), (2,3), (3,3), (4,3), (5,3), (6,3), (7,3), (8,3), (9,3),
			(2,4), (5,4),
			(0,5), (1,5), (2,5), (3,5), (5,5), (6,5), (7,5), (8,5),
			(0,6), (8,6),
			(0,7), (1,7), (2,7), (3,7), (5,7), (6,7), (7,7), (8,7), (9,7),
			(0,8), (5,8), (9,8),
			(0,9), (1,9), (2,9), (3,9), (5,9), (6,9), (7,9), (8,9)]
			
	-- output = 22
	
	start :: Pos
	start = (1,0)
	
	goal :: Pos
	goal = (8,9)
	
	isNeighbor :: Pos -> Pos -> Bool
	isNeighbor (x0,y0) (x1,y1) = abs(x1 - x0) + abs(y1 - y0) == 1
	
	findNeighbors :: Maze -> Pos -> [Pos]
	findNeighbors maze pos = filter (isNeighbor pos) maze
	
	findPath :: Maze -> Queue -> Pos -> Maybe Int
	findPath [] [] _ = Nothing
	findPath [_] [] _ = Nothing
	findPath maze ((pos, level):queue) goalPos =
		if pos == goalPos
			then Just level
			else findPath mazeRest (queue ++ newItems) goalPos
			where
				newNodes = findNeighbors maze pos
				mazeRest = filter (\x -> not(elem x newNodes)) maze
				newItems = [(x, level+1) | x <- newNodes]
	
	
	solve :: Maze -> Maybe Int
	solve maze =
		findPath maze [(start,0)] goal