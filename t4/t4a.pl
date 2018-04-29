/*
	CD Independente

	Uma banda formada por alunos e alunas da escola está gravando um CD com exatamente sete músicas
distintas – S, T, V, W, X, Y e Z. Cada música ocupa exatamente uma das sete faixas contidas no
CD. Algumas das músicas são sucessos antigos de rock; outras são composições da própria banda. As
seguintes restrições devem ser obedecidas:

	1- S ocupa a quarta faixa do CD.

	2- Tanto W como Y precedem S no CD (ou seja, W e Y estão numa faixa que é tocada antes de S
no CD).

	3- T precede W no CD (ou seja, T está numa faixa que é tocada antes de W).

	4- Um sucesso de rock ocupa a sexta faixa do CD.

	5- Cada sucesso de rock é imediatamente precedido no CD por uma composição da banda (ou seja,
no CD cada sucesso de rock toca imediatamente após uma composição da banda).

	6- Z é um sucesso de rock.
*/

% divide uma lista em duas 
split([H|T],H,[],T).

split([H|T],E,[H|F],S) :- 
split(T,E,F,S).

ocupa(X, Y, CD):-
nth0(X, CD, Y).

precede(X, Y, CD):-
split(CD, Y, A, _),
member(X, A).

% Cada sucesso de rock é imediatamente precedido no CD por uma composição da banda.
rock(X, [_,_,_,_,_,X,_]) :-
	\+ nextto(z, X, [_,_,_,_,_,X,_]).

rock(z, CD) :-
	CD = [H|_],
	H \= z,
	nextto(X, z, CD),
	\+ rock(X, CD).

/*
Questão 11.
Qual das seguintes alternativas po-
deria ser a ordem das músicas no CD, da primeira
para a sétima faixa?

(A) T, W, V, S, Y, X, Z
(B) V, Y, T, S, W, Z, X
(C) X, Y, W, S, T, Z, S
(D) Y, T, W, S, X, Z, V
(E) Z, T, X, W, V, Y, S
*/

% Solução da questão 11:

solucao(CD) :-
% S ocupa a quarta faixa do CD.
ocupa(3, s, CD),

% Tanto W como Y precedem S no CD.
precede(w, s, CD),
precede(y, s, CD),

% T precede W no CD.
precede(t, w, CD),

% Um sucesso de rock ocupa a sexta faixa do CD.
CD = [_,_,_,_,_,X,_],
rock(X, CD),

% Z é um sucesso de rock 
rock(z, CD).

