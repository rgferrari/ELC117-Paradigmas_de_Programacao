import Data.Char

--exercico 1
isVowel :: Char -> Bool
isVowel c = elem c "aeiou"

--exercicio 2
addComma :: [String] -> [String]
addComma str = map(\x -> x ++ ",")str

--exercicio 3
htmlListItems :: [String] -> [String]
--htmlListItems str = map(\x -> "<li>" ++ x ++ "</li>")str
aux str = "<li>" ++ str ++ "</li>"
htmlListItems str = map aux str

--exercicio 4
semVogais :: String -> String
--semVogais xs = filter(not . \x -> elem x "aeiou")xs 
aux2 x = elem x "aeiou"
semVogais x = filter (not . aux2) x

--exercicio 5
codifica :: String -> String
--codifica xs = map ( \x -> if ( x /= ' ' ) then '-' else x ) xs
aux3 x = if ( x /= ' ' ) then '-' else x
codifica xs = map aux3 xs

--exercicio 6
firstName :: String -> String
firstName xs = takeWhile (/= ' ') xs

--exercicio 7
isInt :: String -> Bool
isInt xs = length ( filter ( not . \x -> elem x "0123456789") xs ) == 0

--exercicio 8
lastName :: String -> String
lastName xs = reverse ( takeWhile (/= ' ') ( reverse xs ) )

--exercicio 9 
userName :: String -> String
userName xs = map toLower ( [ head ( firstName xs ) ] ++ ( lastName xs ) ) 
 






















