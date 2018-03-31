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

--exercicio 4
--bin2dec' :: [Int] -> Int 
--auxBin2Dec' (x:xs) = ( x * ( 2^ (length xs) ) )
--bin2dec' xs = map auxBin2Dec' xs

--exercicio 5
dec2bin :: Int -> [Int]
dec2binAux 0 = [0]
dec2binAux 1 = [1]
dec2binAux x = ( x `mod` 2 ) : ( dec2binAux ( x `div` 2 ) ) 
dec2bin x = reverse ( dec2binAux x )

--exercicio 6
isHex :: String -> Bool
isHex "" = undefined
isHex xs = ( length ( filter ( \x -> notElem x "0123456789ABCDEFabcdef" ) xs ) ) == 0
