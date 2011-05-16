-module (day2).
-export ([dict/2]).
-export ([dict2/2]).
-export ([dict_filter/2]).

dict([{Key, Value}|T], Key) -> Value;
dict([{Key, Value}], Key) -> Value;
dict([H|T], Key) -> dict(T, Key);
dict([], Key) -> null.

dict2(List, Key) -> lists:map(fun({K, V}) -> V end, dict_filter(List, Key)).
dict_filter(List, Key) -> lists:filter(fun({K, V}) -> K =:= Key end, List).

