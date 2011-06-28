module Main where
	-- p.47 Saruman's Army
	-- R = 10
	test = [1, 7, 15, 20, 30, 50]
	-- output = 3 (7, 30, 50)

	leftSide _ a _ [] = [a]
	leftSide r left prev (pos:poss)
		| pos <= (left + r) = leftSide r left pos poss
		| otherwise = (prev : (rightSide r prev (pos:poss)))

	rightSide _ _ [] = []
	rightSide r center (pos:poss)
		| pos <= (center + r) = rightSide r center poss
		| otherwise = leftSide r pos pos poss

	solve r input = leftSide r 0 0 input
	