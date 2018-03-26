--exercicio 1
isBin :: String -> Bool
auxBin "" = True
auxBin (x:xs)
	| x == '1' = auxBin xs
    | x == '0' = auxBin xs
    | otherwise = False
isBin "" = False
isBin (x:xs)
    | x == '1' = auxBin xs
    | x == '0' = auxBin xs
    | otherwise = False

--exercicio 2
isBin' :: String -> Bool
isBin' xs = length ( filter ( \x -> x /= '0' && x /= '1' ) xs ) == 0

--exercicio 3
bin2dec :: [Int] -> Int
auxBin2Dec [] expoente = 0
auxBin2Dec (x:xs) expoente = (x*(2^expoente)) + auxBin2Dec xs ((length xs)-1)
bin2dec [] = undefined
bin2dec bits = auxBin2Dec bits ((length bits)-1)
