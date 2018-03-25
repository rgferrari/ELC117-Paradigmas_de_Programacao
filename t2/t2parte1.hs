--exercicio 1
geraTabela :: Int -> [(Int,Int)]
geraTabela 0 = [];
geraTabela x = (x, x ^ 2 ) : geraTabela ( x - 1 )

--exercicio 2
checkChar :: Char -> String -> Bool
checkChar c [] = False;
checkChar c (x:str)
	| c == x = True
	| otherwise = checkChar c str
