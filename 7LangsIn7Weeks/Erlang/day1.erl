-module(day1).
-export([word_count/1]).
-export([count_to/1]).
-export ([error_message/1]).

word_count([32|B]) -> 1 + skip_space(B);
word_count([A|B]) -> word_count(B);
word_count([]) -> 1.

skip_space([32|B]) -> skip_space(B);
skip_space([]) -> 0;
skip_space(A) -> word_count(A).

count_to(0) -> [0];
count_to(N) -> count_to(N-1) ++ [N].

error_message(success) -> success;
error_message({error, Message}) -> "error: " ++ Message.
