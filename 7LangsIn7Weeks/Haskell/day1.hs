module Main where
	allEven :: [Integer] -> [Integer]
	allEven [] = []
	allEven (h:t) = if even h then h:allEven t else allEven t

	allEven2 :: [Integer] -> [Integer]
	allEven2 xs = filter even xs

	allEven3 :: [Integer] -> [Integer]
	allEven3 xs = [ x | x <- xs, even x ]

	allEven4 :: [Integer] -> [Integer]
	allEven4 [] = []
	allEven4 (h:t) = if odd h then allEven4 t else h : allEven4 t

	rev [] = []
	rev (h:t) = rev t ++ [h]

	colorPairs = [(a, b) | a <- colors, b <- colors, a < b]
		where colors = ["black", "white", "blue", "red"]
		
	multiTable = [(a, b, a * b) | a <- [1..12], b <- [1..12]]
 
	mapTable = [[a,b,c,d,e] | a<-as, b<-bs, c<-cs, d<-ds, e<-es]
		where 
			states = ["Alabama", "Mississippi", "Georgia", "Tenessee", "Florida"]
			colors = ["red", "green", "blue"]
			lst = map (\x -> [(x, f) | f <- colors]) states
			[as, bs, cs, ds, es] = lst
			
	validateMap [(_,a),(_,m),(_,g),(_,t),(_,f)] = 
		m /= t && m /= a && a /= t && a /= m && a /= g && a /= f && g /= f && g /= t

	validMapTable = filter validateMap mapTable



