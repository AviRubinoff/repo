/**
 * The external Tree Node for Linked Trees.
 * @author Avi Rubinoff
 * @param <T> the type of data stored in the node.
 */
public class TreeNode<T>
{
    private T data;
    private TreeNode<T> leftChild;
    private TreeNode<T> rightChild;

    /**
     * Default constructor, sets children to null.
     *
     * @param dataNode the data stored in the node.
     */
    public TreeNode(T dataNode)
    {
        data = dataNode;
        leftChild = null;
        rightChild = null;
    }

    /**
     * Copy constructor. Should return a deep copy.
     *
     * @param node the TreeNode to be copied.
     */
    public TreeNode(TreeNode<T> node)
    {
        T data = node.getData();
        this.data = data;
        this.setRightChild(node.getRightChild());
        this.setLeftChild(node.getLeftChild());
    }

    private void setLeftChild(TreeNode<T> leftChild)
    {
        this.leftChild = leftChild;
    }

    private void setRightChild(TreeNode<T> rightChild)
    {
        this.rightChild = rightChild;
    }

    /**
     * Getter method for the node's data.
     *
     * @return the node's data.
     */
    public T getData()
    {
        return data;
    }

    /**
     * Getter method for the node's left child.
     *
     * @return the node's left child.
     */
    public TreeNode<T> getLeftChild()
    {
        return leftChild;
    }

    /**
     * Getter method for the node's right child.
     *
     * @return the node's right child.
     */
    public TreeNode<T> getRightChild()
    {
        return rightChild;
    }

    /**
     * Creates a new node with the specified data and sets it as this node's left child.
     * @param data the data to go in the left child
     */
    protected void addLeftChild(T data)
    {
        this.leftChild = new TreeNode<>(data);
    }

    /**
     * Creates a new node with the specified data and sets it as this node's right child.
     * @param data the data to go in the right child
     */
    protected void addRightChild(T data)
    {
        this.rightChild = new TreeNode<>(data);
    }
}
