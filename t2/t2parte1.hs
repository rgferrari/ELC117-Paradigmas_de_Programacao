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

--exercicio 3
coordenadas :: [(Float,Float)] -> [(Float,Float)]
coordenadas [] = [];
coordenadas ((x,x1):xs) = (x+2, x1+2) : coordenadas xs

--exercicio 4
geraTabela' :: Int -> [(Int,Int)]
aux 0 = [];
aux x = (x, x^2) : aux(x-1)
geraTabela' x = reverse (aux x)
