/* 
	OBI 2015 - MODALIDADE INICIAÇÃO

	A Banda formada por alunos da escola vai fazer um show no clube. Eles vão tocar exatamente sete
músicas: J, K, L, M, N, O e P, não necessariamente nessa ordem. As músicas vão ser apresentadas de
acordo com as seguintes condições:

• N é a primeira ou a sétima música a ser apresentada. 

• L é apresentada em algum momento após J. 

• P é apresentada em algum momento após K.

• Exatamente uma música é apresentada entre J e M, seja J apresentada antes de M ou não.

• Exatamente uma música é apresentada entre K e N, seja K apresentada antes de N ou não. 
*/

split([H|T],H,[],T).

split([H|T],E,[H|F],S) :- 
	split(T,E,F,S).


regra1(E) :-
	nth0(6, E, n).

regra1(E) :-
	nth0(0, E, n).

regra2(E) :-
	split(E, j, _, B),
	member(l, B).

regra3(E) :- 
	split(E, k, _, B),
	member(p, B).

regra4(E) :-
	nth0(X, E, j),
	nth0(Y, E, m),
	N is X - Y,
	N = 2.

regra4(E) :-
	nth0(X, E, j),
	nth0(Y, E, m),
	N is Y - X,
	N = 2.

regra5(E) :-
nth0(X, E, k),
nth0(Y, E, n),
N is X - Y,
N = 2.

regra5(E) :-
	nth0(X, E, k),
	nth0(Y, E, n),
	N is Y - X,
	N = 2.

solucao(E):-
	regra1(E),
	regra2(E),
	regra3(E),
	regra4(E),
	regra5(E).
/*	
Questão 1.

Qual das seguintes é uma ordem em
que as músicas podem ser apresentadas, da pri-
meira para a sétima?

(A) J, L, O, M, K, P, N
(B) K, P, N, O, J, L, M
(C) M, O, J, L, K, P, N
(D) N, L, K, O, M, P, J
(E) N, P, K, O, J, L, M

(A) solucao([j, l, o, m, k, p, n]). false
(B) solucao([k, p, n, o, j, l, m]). false
(C) solucao([m, o, j, l, k, p, n]). true
(D) solucao([n, l, k, o, m, p, j]). false
(E) solucao([n, p, k, o, j, l, m]). false

*/