� ���� ������� �� ������� ��������� ������ ������� ������.
� ������� ������ �����, ��������, ������� ��� ������. ������� �� �������������, ����� �� ���������, � ��� �����.
PIC

��� ��������� ��� ��������!
� �������� ������� ������� ���������� ������� ����� �������� ���������, ������� ����������, ���� �� ���� � ������� ������, ��� ���� �� ������� ������ ������. �� �� ������ ��������� ���� (� ������, ������� ��� ������������) � ������ ������������.
�� ���� ������� ��������� ������ ������, �� ������ ������ ������ True, ���� � ������ ���������� ����, ����� � False. ������ �������������� ������, � ������� ���������� �������, �� ������ ��������� �(1).

������ �����
� ���� ������ ��� ����� ����������� ������ ������� � ��������, ��������� ������� ������ �� �����.
������� ������ ��������� �� ���� ������ �������� ������.
����� / ���������, �������������� ����� ������ �������� ���:
�++:
struct Node  
{  
    std::weak_ptr<Node> next;  
};
���� ������� ������ ���������� HasCycle.
��������� �������: bool HasCycle(std::shared_ptr<Node> head)
����� ���������� solution.h

Go:
type TListItem struct {  
value       string  
nextElement *TListItem  
}
���� ������� ������ ���������� HasCycle. ����� ���������� package main

JS:
class Node {  
    constructor(value) {  
        this.value = value;  
        this.next = null;  
    }  
}
���� ������� ������ ���������� hasCycle.

Python:
class Node:  
    def __init__(self, value, next=None):  
        self.value = value  
        self.next = next  
    def __repr__(self):  
        return self.value
���� ������� ������ ���������� hasCycle.

Java:
���� ������ ��������� public class Solution � public static String hasCycle (Node head)

class Node<V> {  
    private V value;  
    private Node<V> next;  
    public Node(V value, Node<V> next) {  
        this.value = value;  
        this.next = next; }  
    public V getValue() {  
        return value; }  
    public Node<V> getNext() {  
        return next; }  
    public void setNext(Node<V> next) {  
        this.next = next; }  
    public boolean hasNext() {  
        return next != null; }  
    public void setValue(V value) {  
        this.value = value; }  
}
������ ������
��� ���� ������, ����� JS, Java � Go ������� ������ ���������� ������ ��������: True/true ��� False/false
��� JS, Java � Go - ������ �True� ��� �False�
����������
����� ������������ ������ O(1) �������������� ������.
��� �������� ����� �������� ���������� make � ��������� ������� � ���� �����. ��� ���� ������ ����������������, ����� Java, ���� ����� ���� ������ ����� ������, ����� solution.py/cpp/go/js (���������� �����������)
��� Java ���� ������ ���������� Solution.java
