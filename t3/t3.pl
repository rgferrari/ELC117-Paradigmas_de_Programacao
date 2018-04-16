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
potN0(-1, []).
potN0(N, L) :-
pow(2, N, C),
L = [C | T],
N1 is N - 1,
potN0(N1,T).

% exercicio 5
zipmult([],[],[]).
zipmult(L1,L2,L3) :-
L1 = [C | T],
L2 = [C1 | T1],
B is C * C1,
L3 = [B | T2],
zipmult(T,T1,T2).

% exercicio 6
potenciasAux(-1,[]).
potenciasAux(N,L) :-
pow(2,N,X),
L = [X | T],
N1 is N - 1,
potenciasAux(N1,T).

potencias(N,L):-
potenciasAux(N,A),
reverse(A,L).


% exercicio 7
positivos([],[]).
positivos(L1,L2) :-
L1 = [H | T],
L2 = [H1 | T1],
H > 0,
H1 is H,
positivos(T,T1).

positivos(L1,L2) :-
L1 = [_ | T],
positivos(T,L2).


% exercicio 8
%mesmaPosicao(A,L1,L2):-