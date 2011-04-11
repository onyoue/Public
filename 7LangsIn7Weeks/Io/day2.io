fib := method(n,
	if(n < 3, 1, fib(n-1) + fib(n-2)))

fib2 := method(n,
	f0 := 1;
	f1 := 1;
	result := 0;
	for (i, 1, n-2, 1,
		(result = f0 + f1;
		f0 = f1;
		f1 = result;
		)))

Number div := method(n, if(n==0, 0, /(n)))
OperatorTable addOperator("div", 2)

List sum2 := method(map(v, v sum) sum)
# ab := list(list(1,2), list(3,4))
# ab sum2
