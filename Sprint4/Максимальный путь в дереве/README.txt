����� � ���������� ����� ����������� ������������ �� ����������� ��������������. ����� ��������� ������������ � ���� ������. ������� ������ �����, ����� ������������ ��� ������ ����. ���� � ���� �������� ������������� �����, ��� �������� ����������� � �������� ���������� ����� ���������. ���� ������������� - ���� ����������. ���� 0 - �� ���������� �� ��������.
����� ����������, ����� ������������ ����� ����� ����� ����������, ����� �� ������ A � ����� B.
���� �� ����������� ������ ��������� ����� ������ ��� ��������� ����. ���� ������ ��������� �� ������� ���� ���� ����.
PIC PIC
������ �����
�� ���� �������� ������ ������.
Python:
class Node:  
    def __init__(self, value, left=None, right=None):  
        self.value = value  
        self.right = right  
        self.left = left
���� ������� ������ ���������� solution.

�++:
struct Node{  
  int value;  
  const Node* left = nullptr;  
  const Node* right = nullptr;  
};  
int Solution(const Node* root);
����� ���������� solution_tree.h

Go:
type TNode struct {  
value  int  
left   *TNode  
right  *TNode  
}
���� ������� ������ ���������� Solution.

JS:
class CNode {  
    constructor(value) {  
        this.value = value;  
        this.left = null;  
        this.right = null;  
    }  
}
���� ������� ������ ���������� Solution.

Java:
���� ������ ��������� public class Solution � public static String treeSolution(Node head)

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
��������� �������: treeSolution(Node... heads)
������ ������
������� ������ ������� �����, ������ ������������� ���������� �����, ������� ����� ����������, ����� �� ������ � � ����� �.
����������
������� ����� ���������� � ���� ����� � ����������� ��������������� ������ ����� ����������������. ����� �������� ���������� make.
��� Java ���� ������ ���������� Solution.java
��� ��������� ������ ���������������� ��� ��� ������������ ������ (��� solution ����).