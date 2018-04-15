% aquecimento
repete(0, _, []).
repete(N, C, L) :-
N > 0, 
L = [C | T],
N1 is N - 1,
repete(N1, C, T).

% exercicio 1
zeroInit([]).
zeroInit(L):-
L = [0 | _].

% exercicio 2
has5([_,_,_,_,_]).

% exercicio 3
hasN(L, N) :-
length(L, N).

% exercicio 4
potN0(0, []).
potN0(N, L) :-
pow(2, N, C),
L = [C | T],
N1 is N - 1,
potN0(N1,T).