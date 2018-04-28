/*
                                       CD Independente
	Uma banda formada por alunos e alunas da escola está gravando um CD com exatamente sete musicas
distintas – S, T, V, W, X, Y e Z. Cada musica ocupa exatamente uma das sete faixas contidas no
CD. Algumas das musicas são sucessos antigos de rock; outras são composicoes da propria banda. As 
seguintes restricoes devem ser obedecidas:

	• S ocupa a quarta faixa do CD.

	• Tanto W como Y precedem S no CD (ou seja, W e Y estao numa faixa que é tocada antes de S no CD).

 	• T precede W no CD (ou seja, T está numa faixa que é tocada antes de W).

 	• Um sucesso de rock ocupa a sexta faixa do CD.

 	• Cada sucesso de rock e imediatamente precedido no CD por uma composicao da banda (ou seja,
  no CD cada sucesso de rock toca imediatamente apos uma composicao da banda).

	• Z e um sucesso de rock.
*/

% Cada sucesso de rock e imediatamente precedido no CD por uma composicao da banda 



rock(X, CD) :-
	CD \= [X|_],
	nextto(Y, X, CD),
	\+ rock(Y, CD).

rock(z, [_,_,_,_,_,_,_]).


solucao(CD) :-
% s ocupa a quarta faixa do CD.
CD = [_,_,_,s,_,X,_],
% w e y precedem s
append(A, [s,_,X,_], CD),
member(w, A),
member(y, A),
nextto(t, w, CD),
% um sucesso de rock ocupa a sexta faixa do CD
rock(X, CD),
% z é um sucesso de rock 
rock(z, CD).


