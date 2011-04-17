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

List myAverage := method(sum / size)

# 5

Array2 := List clone

List fill := method(n, val,
	for (i, 1, n, 1,
	append(val)))
	
Array2 appendRow := method(n, val,
	result := list();
	result fill(n, val);
	append(result))

dim := method(x, y,
	result := Array2 clone;
	for (i, 1, y, 1,
	result appendRow(x, 0)))

Array2 get := method(x, y,
	row := at(y);
	row at(x))

Array2 set := method(x, y, value,
	row := at(y);
	row atPut(x, value))

Array2 write := method(filename,
	f := File with(filename);
	f remove;
	f openForAppending;
	justSerialized(f);
	f close)
	
# a := dim(3,3)
# a write("tekitou.io")
# b := doFile("tekitou.io")


