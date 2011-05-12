-module(day1).
-export([word_count/1]).

word_count([32|B]) -> 1 + skip_space(B);
word_count([A|B]) -> word_count(B);
word_count([]) -> 1.

skip_space([32|B]) -> skip_space(B);
skip_space([]) -> 0;
skip_space(A) -> word_count(A).
