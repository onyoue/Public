module Main where
	dictGet _ [] = Nothing
	dictGet k ((key, value) : t) = 
		if k == key 
			then Just value 
			else (dictGet k t)

