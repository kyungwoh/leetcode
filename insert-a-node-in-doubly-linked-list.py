# https://practice.geeksforgeeks.org/problems/insert-a-node-in-doubly-linked-list/1
{
class Node:
	def __init__(self, data):
		self.data = data
		self.next = None
		self.prev = None
class DoublyLinkedList:
	def __init__(self):
		self.head = None
	def append(self, new_data):
		new_node = Node(new_data)
		new_node.next = None
		if self.head is None:
			new_node.prev = None
			self.head = new_node
			return
		last = self.head
		while(last.next is not None):
			last = last.next
		last.next = new_node
		new_node.prev = last
		return
	def printList(self, node):
		while(node.next is not None):
			node = node.next
		while node.prev is not None:
		    node = node.prev
		while(node is not None):
		    print(node.data, end=" ")
		    node = node.next
		print()
		
if __name__=='__main__':
    t = int(input())
    for i in range(t):
        n = int(input())
        arr = map(int, input().strip().split())
        llist = DoublyLinkedList()
        for e in arr:
            llist.append(e)
        pos,data = map(int, input().strip().split())
        addNode(llist.head, pos, data)
        llist.printList(llist.head)
# Contributed by: Harshit Sidhwa

}

''' Please note that it's Function problem i.e.
you need to write your solution in the form of Function(s) only.
Driver Code to call/invoke your function is mentioned above. '''

def addNode(head, p, data):
    # Code here
    last = head
    while last.next and p>0:
        last = last.next
        p -= 1
    
    next2 = last.next
    curr = Node(data)
    curr.prev = last
    last.next = curr
    curr.next = next2
    if next2: next2.prev = curr
