module Main where
	
	tasks = [(1,3), (2,5), (4,7), (6,9), (8,10)]
	-- output = 3
	
	startTimeFilter startTime tasks =
		filter (\(s,e) -> startTime <= s) tasks
		
	firstEndTask candidate [] = candidate
	firstEndTask (start0, end0) ((start, end):inputs)
		| end < end0 = firstEndTask (start, end) inputs
		| otherwise = firstEndTask (start0, end0) inputs
		
	getFirstEndTask (t:ts) =
		firstEndTask t ts
		
	makeSchedule doTasks [] = doTasks
	makeSchedule doTasks inputs =
		makeSchedule ((start, end):doTasks) rest
		where
			(start, end) = getFirstEndTask inputs
			rest = startTimeFilter (end+1) inputs			
			
	solve inputs =
		makeSchedule [] inputs
		