package linkedlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NodeTest {
    private Node<String> node;

    @BeforeEach
    void setUp() {
        node = new Node<>("Test Value");
    }

    @Test
    void testConstructor() {
        assertEquals("Test Value", node.getValue());
        assertNull(node.getNext());
        assertNull(node.getPrevious());
    }

    @Test
    void testSetValue() {
        node.setValue("New Value");
        assertEquals("New Value", node.getValue());

        node.setValue(null);
        assertNull(node.getValue());

        node.setValue("Another Value");
        assertEquals("Another Value", node.getValue());
    }

    @Test
    void testSetAndGetNext() {
        Node<String> nextNode = new Node<>("Next Node");

        assertNull(node.getNext());

        node.setNext(nextNode);
        assertEquals(nextNode, node.getNext());
        assertEquals("Next Node", node.getNext().getValue());

        node.setNext(null);
        assertNull(node.getNext());
    }

    @Test
    void testSetAndGetPrevious() {
        Node<String> prevNode = new Node<>("Previous Node");

        assertNull(node.getPrevious());

        node.setPrevious(prevNode);
        assertEquals(prevNode, node.getPrevious());
        assertEquals("Previous Node", node.getPrevious().getValue());

        node.setPrevious(null);
        assertNull(node.getPrevious());
    }

    @Test
    void testNodeWithDifferentTypes() {
        Node<Integer> intNode = new Node<>(42);
        assertEquals(42, intNode.getValue());

        Node<Double> doubleNode = new Node<>(3.14);
        assertEquals(3.14, doubleNode.getValue(), 0.01);

        class Person {
            String name;
            Person(String name) { this.name = name; }
        }
        Node<Person> personNode = new Node<>(new Person("John"));
        assertEquals("John", personNode.getValue().name);
    }

    @Test
    void testNodeChain() {
        Node<String> node1 = new Node<>("First");
        Node<String> node2 = new Node<>("Second");
        Node<String> node3 = new Node<>("Third");

        node1.setNext(node2);
        node2.setPrevious(node1);
        node2.setNext(node3);
        node3.setPrevious(node2);

        assertEquals(node2, node1.getNext());
        assertEquals(node3, node1.getNext().getNext());
        assertEquals("Third", node1.getNext().getNext().getValue());

        assertEquals(node2, node3.getPrevious());
        assertEquals(node1, node3.getPrevious().getPrevious());
        assertEquals("First", node3.getPrevious().getPrevious().getValue());

        assertEquals(node1, node2.getPrevious());
        assertEquals(node3, node2.getNext());
    }

    @Test
    void testIsolatedNode() {
        Node<String> isolated = new Node<>("Isolated");

        assertNull(isolated.getNext());
        assertNull(isolated.getPrevious());
        assertEquals("Isolated", isolated.getValue());

        isolated.setValue("Still Isolated");
        assertEquals("Still Isolated", isolated.getValue());
    }

    @Test
    void testCircularReference() {
        Node<String> node1 = new Node<>("A");
        Node<String> node2 = new Node<>("B");

        node1.setNext(node2);
        node2.setPrevious(node1);
        node2.setNext(node1);
        node1.setPrevious(node2);

        assertEquals(node2, node1.getNext());
        assertEquals(node1, node1.getNext().getNext()); // Back to node1
        assertEquals(node2, node1.getPrevious()); // Also via previous

        assertEquals("A", node1.getValue());
        assertEquals("B", node1.getNext().getValue());
        assertEquals("A", node1.getNext().getNext().getValue());
    }

    @Test
    void testUpdateValueInChain() {
        Node<String> node1 = new Node<>("Original");
        Node<String> node2 = new Node<>("Middle");
        Node<String> node3 = new Node<>("End");

        node1.setNext(node2);
        node2.setPrevious(node1);
        node2.setNext(node3);
        node3.setPrevious(node2);

        node2.setValue("Updated Middle");

        assertEquals("Original", node1.getValue());
        assertEquals("Updated Middle", node2.getValue());
        assertEquals("Updated Middle", node1.getNext().getValue());
        assertEquals("End", node3.getValue());
        assertEquals("Updated Middle", node3.getPrevious().getValue());
    }

    @Test
    void testNullNodeValue() {
        Node<String> nullNode = new Node<>(null);

        assertNull(nullNode.getValue());

        Node<String> nextNode = new Node<>("Has Value");
        nullNode.setNext(nextNode);

        assertEquals(nextNode, nullNode.getNext());
        assertEquals("Has Value", nullNode.getNext().getValue());

        nullNode.setValue("Now Has Value");
        assertEquals("Now Has Value", nullNode.getValue());

        nullNode.setValue(null);
        assertNull(nullNode.getValue());
    }
}