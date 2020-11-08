Скоро в Трешландии будут проводиться соревнования по спортивному ориентированию. Карта местности представлена в виде дерева. Посещая каждый пункт, можно зарабатывать или терять очки. Если в узле записано положительное число, это значение добавляется к текущему количеству очков участника. Если отрицательное - очки вычитаются. Если 0 - их количество не меняется.
Нужно определить, какое максимальное число очков можно заработать, попав из города A в город B.
Путь не обязательно должен проходить через корень или содержать лист. Путь должен содержать по крайней мере один узел.
PIC PIC
Формат ввода
На вход подается корень дерева.
Python:
class Node:  
    def __init__(self, value, left=None, right=None):  
        self.value = value  
        self.right = right  
        self.left = left
Ваша функция должна называться solution.

С++:
struct Node{  
  int value;  
  const Node* left = nullptr;  
  const Node* right = nullptr;  
};  
int Solution(const Node* root);
Нужно подключить solution_tree.h

Go:
type TNode struct {  
value  int  
left   *TNode  
right  *TNode  
}
Ваша функция должна называться Solution.

JS:
class CNode {  
    constructor(value) {  
        this.value = value;  
        this.left = null;  
        this.right = null;  
    }  
}
Ваша функция должна называться Solution.

Java:
Файл должен содержать public class Solution с public static String treeSolution(Node head)

class Node {  
    int value;  
    Node left;  
    Node right;  
 
    Node(int value) {  
        this.value = value;  
        right = null;  
        left = null;  
    }  
}  
Сигнатура функции: treeSolution(Node... heads)
Формат вывода
Функция должна вернуть число, равное максимальному количеству очков, которое можно заработать, попав из города А в город В.
Примечания
Решение нужно отправлять в виде файла с расширением соответствующем вашему языку программирования. Нужно выбирать компилятор make.
Для Java файл должен называться Solution.java
Для остальных языков программирования это имя использовать нельзя (имя solution тоже).