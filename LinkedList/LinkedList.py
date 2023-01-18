class Node:
    # Constructor for Node
    def __init__ (self, data):
        self.data = data
        self.next = None
    # To string
    def __str__(self):
        return str(self.data)

class LinkedList:
    # Constructor
    def __init__(self):
        self.head = None
    # To string
    def __str__(self):
        # Print that the list is empty
        if self.head is None:
            print("Linked list is empty")
            return ""
    
        output = ""
        current_node = self.head
        
        # Loop through every node
        while(current_node != None):
            output += str(current_node) + " "
            current_node = current_node.next
        
        return output
    
    # Add @ head
    def push(self, new_data):
        new_node = Node(new_data)
        new_node.next = self.head
        self.head = new_node
    
    # Insert after a node 
    def insert(self, prev_node, new_data):
        # Testing for incorrect data type
        if type(prev_node).__name__ != "Node":
            print("Prev node is not a node")
            return
        
        if prev_node is None:
            print("Previous node does not exist")
            return

        new_node = Node(new_data)
        new_node.next =  prev_node.next
        prev_node.next = new_node
    
    # Add at the end
    def add(self, new_data):
        # If attempting to add lists
        if type(new_data).__name__ == "LinkedList":
            print("Cannot add LinkedList")
            return
        
        if self.head is None:
            self.head = Node(new_data)
            return
        # If list is empty add to the bottom
        new_node = Node(new_data)
        current_node = self.head
        
        # Loop to the end and then add the node there
        while(current_node.next != None):
            current_node = current_node.next
            
        current_node.next = new_node
    
    # Gets the node at an index
    def get(self, index):
        # Testing for incorrect data type
        if(not isinstance(index, int)):
            print("Index is not an int")
            return
        
        # If list is empty
        if self.head is None:
            print("Linked list is empty")
            return
        
        if(index < 0):
            print("Index out of bounds")
            return

        count = 0
        current_node = self.head
        
        # Loop until you reach the end or reach the index
        while(current_node != None and count < index):
            current_node = current_node.next
            count += 1
                
        if(current_node == None):
            print("Index out of bounds")
            return
        
        return current_node
    
    # pops the head
    def pop (self):
        popped = self.head.next
        self.head = self.head.next
        
        return popped
    
    # Remove at a index
    def remove(self, index):
        
        # Testing for incorrect data type
        if(not isinstance(index, int)):
            print("Index is not an int")
            return
        
        # If list is empty
        if self.head is None:
            print("Linked list is empty")
            return

        if(index >= self.size()):
            print("Index out of bounds")
            return
        
        if(index == 0):
            return self.pop()

        node = self.get(index - 1)
        removed = node.next
        
        if(index == self.size() - 1):
            node.next = None
            return removed
        
        node.next = node.next.next
        
        return removed
    
    # A mwthod to clear the list
    def clear(self):
        while(self.head != None):
            self.pop()
    
    # A method to get the size of the list 
    def size (self):
        count = 0
        current_node = self.head
        
        # Loop to the end
        while(current_node != None):
            count += 1
            if(current_node.next is None):
                break
            current_node = current_node.next
            
        return count
