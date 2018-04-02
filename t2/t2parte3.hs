toInt :: String -> [Int]
toInt [] = []
toInt (x:xs) = ( read [x] :: Int ) : ( toInt xs )

isEanOk :: String -> Bool
isEanOkAux3 [] = 0
isEanOkAux3 xs = ( ( head xs ) * 3 ) + ( isEanOkAux1 ( tail xs ) )
isEanOkAux1 [] = 0
isEanOkAux1 xs = ( ( head xs ) * 1 ) + ( isEanOkAux3 ( tail xs ) )
isEanOk xs
    | ( length xs ) /= 13 = undefined
    | otherwise = ( ( last (toInt xs) ) + ( isEanOkAux1 (init (toInt xs) ) ) ) `mod` 10 == 0

