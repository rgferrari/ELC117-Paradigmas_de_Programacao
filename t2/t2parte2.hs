--exercicio 1
isBin :: String -> Bool
isBin "" = True
isBin (x:xs)
    | x == '1' = isBin xs
    | x == '0' = isBin xs
    | otherwise = False

--exercicio 2
isBin' :: String -> Bool
isBin' xs = length ( filter ( \x -> x /= '0' && x /= '1' ) xs ) == 0