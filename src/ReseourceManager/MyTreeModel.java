package ReseourceManager;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.*;

public class MyTreeModel implements TreeModel{
	Node node;
	public MyTreeModel(Node node){
		this.node = node;
		
	}
	
	public void addTreeModelListener(TreeModelListener l) {
		
	}
	
	//返回files数组中的各个子节点的个数   files本身是一个数组,给它一个下标就返回下标对应的各个文件，给node里面的file带参构造
	public Object getChild(Object parent, int index) {
		return new Node(((Node)parent).files[index]);
	}

	public int getChildCount(Object parent) {
		return ((Node)parent).getcount();
	}

	public int getIndexOfChild(Object parent, Object child) {
		return 0;
	}

	public Object getRoot() {
		return new Node();
	}
	//判断是不是叶子 
	public boolean isLeaf(Object node) {				
			return false;                             
	}

	public void removeTreeModelListener(TreeModelListener l) {
		
	}

	public void valueForPathChanged(TreePath path, Object newValue) {
		
	 }

	

}
