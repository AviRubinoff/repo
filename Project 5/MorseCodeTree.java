import java.util.ArrayList;

/**
 * This is a MorseCodeTree which is specifically used for the conversion of Morse code to English.
 * It relies on a root (reference to root of the tree). The root is set to null when the tree is empty.
 * The class uses an external generic TreeNode class which consists of a reference to the data and a
 * reference to the left and right child. The TreeNode is parameterized as a String. This class uses a
 * private member root (reference to a TreeNode) The constructor will call the buildTree method.
 * @author Avi Rubinoff
 */
public class MorseCodeTree implements LinkedConverterTreeInterface<String>
{
    private TreeNode<String> root;

    /**
     * Constructor - calls the buildTree method.
     */
    public MorseCodeTree()
    {
        root = new TreeNode<>("");
        buildTree();
    }

    /**
     * Returns a reference to the root
     * @return reference to root
     */
    @Override
    public TreeNode<String> getRoot()
    {
        return root;
    }

    /**
     * sets the root of the Tree
     * @param newNode a copy of newNode will be the new root
     */
    @Override
    public void setRoot(TreeNode<String> newNode)
    {
        root = new TreeNode<String>(newNode);
    }

    /**
     * Adds result to the correct position in the tree based on the code
     * This method will call the recursive method addNode
     * @param code the code for the new node to be added
     * @param letter the letter for the corresponding code
     * @return the linkedConverterTree with the new node added
     */
    @Override
    public LinkedConverterTreeInterface<String> insert(String code, String letter)
    {
        addNode(root, code, letter);
        return this;
    }

    /**
     * This is a recursive method that adds element to the correct position
     * in the tree based on the code.
     * @param root the root of the tree for this particular recursive instance of addNode
     * @param code the code for this particular recursive instance of addNode
     * @param letter the data of the new TreeNode to be added
     */
    @Override
    public void addNode(TreeNode<String> root, String code, String letter)
    {
        if (code.equals("."))
            root.addLeftChild(letter);
        else if (code.equals("-"))
            root.addRightChild(letter);
        else
        {
            if (code.charAt(0) == '.')
                addNode(root.getLeftChild(), code.substring(1), letter);
            else addNode(root.getRightChild(), code.substring(1), letter);
        }
    }

    /**
     * Fetch the data in the tree based on the code
     * This method will call the recursive method fetchNode
     * @param code the code that describes the traversals within the tree
     * @return the result that corresponds to the code
     */
    @Override
    public String fetch(String code)
    {
        if (code.length() < 6)
            return fetchNode(root, code);
        else return "Invalid input";
    }

    /**
     * This is the recursive method that fetches the data of the TreeNode
     * that corresponds with the code
     * @param root the root of the tree for this particular recursive instance of addNode
     * @param code the code for this particular recursive instance of fetchNode
     * @return the data corresponding to the code
     */
    @Override
    public String fetchNode(TreeNode<String> root, String code)
    {
        if (code.equals(""))
            return root.getData();
        else if (code.charAt(0) == '.')
            return fetchNode(root.getLeftChild(), code.substring(1));
        else if (code.charAt(0) == '-')
            return fetchNode(root.getRightChild(), code.substring(1));
        else return "Invalid input";
    }

    /**
     * This operation is not supported for a LinkedConverterTree
     * @param data data of node to be deleted
     * @return reference to the current tree
     * @throws UnsupportedOperationException
     */
    @Override
    public LinkedConverterTreeInterface<String> delete(String data) throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException();
    }

    /**
     * This operation is not supported for a LinkedConverterTree
     * @return reference to the current tree
     * @throws UnsupportedOperationException
     */
    @Override
    public LinkedConverterTreeInterface<String> update() throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException();
    }

    /**
     * This method builds the LinkedConverterTree by inserting TreeNodes<T>
     * into their proper locations
     */
    @Override
    public void buildTree()
    {
        insert(".", "e");
        insert("-", "t");
        insert("..", "i");
        insert(".-", "a");
        insert("-.", "n");
        insert("--", "m");
        insert("...", "s");
        insert("..-", "u");
        insert(".-.", "r");
        insert(".--", "w");
        insert("-..", "d");
        insert("-.-", "k");
        insert("--.", "g");
        insert("---", "o");
        insert("....", "h");
        insert("...-", "v");
        insert("..-.", "f");
        insert(".-..", "l");
        insert(".--.", "p");
        insert(".---", "j");
        insert("-...", "b");
        insert("-..-", "x");
        insert("-.-.", "c");
        insert("-.--", "y");
        insert("--..", "z");
        insert("--.-", "q");
    }

    /**
     * Returns an ArrayList of the items in the linked converter Tree in LNR (Inorder) Traversal order
     * Used for testing to make sure tree is built correctly
     * @return an ArrayList of the items in the linked Tree
     */
    @Override
    public ArrayList<String> toArrayList()
    {
        ArrayList<String> result = new ArrayList<>();
        LNRoutputTraversal(root, result);
        return result;
    }

    /**
     * The recursive method to put the contents of the linked converter tree in an ArrayList<T>
     * LNR (Inorder)
     * @param root the root of the tree for this particular recursive instance
     * @param list the ArrayList that will hold the contents of the tree in LNR order
     */
    @Override
    public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list)
    {
        if (root.getLeftChild() != null)
            LNRoutputTraversal(root.getLeftChild(), list);
        list.add(root.getData());
        if (root.getRightChild() != null)
            LNRoutputTraversal(root.getRightChild(), list);
    }
}