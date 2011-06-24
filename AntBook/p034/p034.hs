module Main where
	-- a = [1, 2, 4, 7]
	-- k = 13
	-- output = Yes (13 = 2 + 4 + 7)
	-- 
	-- a = [1, 2, 4, 7]
	-- k = 15
	-- output = No
	
	solve [] k = []
	solve (first:rest) k =
		if first == k then [first]
		else
			let firstUsed = solve rest (k-first)
			in
				if (length firstUsed) > 0
				then first : firstUsed
				else solve rest k
					