class Node:
    def __init__(self, data) -> None:
        self.data=data
        self.next=None

def print_backward_recursive(head):
    if not head:
        return
    print_backward_recursive(head.next)
    print(head.data, end=" ")

# To create some test cases
def create_linked_list(arr):
    head = Node(arr[0])
    current = head
    for i in range(1, len(arr)):
        current.next = Node(arr[i])
        current = current.next
    return head

# To print the linked list
def print_linked_list(head):
    current = head
    while current:
        print(current.data, end=" ")
        current = current.next
    print()

# To test the function
head = create_linked_list([1, 2, 3, 4, 5])
print_linked_list(head)
print_backward_recursive(head)