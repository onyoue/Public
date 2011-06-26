module Main where
	-- coin problem (p.42)
	-- c_1 = 3, c_5 = 2, c_10 = 1, c_50 = 3, c_100 = 0, c_500 = 2, a = 620
	
	coins = [(500, 2), (100, 0), (50, 3), (10, 1), (5, 2), (1, 3)]
	
	useCoin a (price, num)
		| a > price * num = num
		| otherwise = a `div` price

	solve 0 _ = []
	solve a ((p, n):coins) = 
		(p, used) : (solve (a-consumed) coins)
		where
			used = useCoin a (p, n)
			consumed = used * p
			