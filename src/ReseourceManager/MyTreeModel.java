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
	
	//����files�����еĸ����ӽڵ�ĸ���   files������һ������,����һ���±�ͷ����±��Ӧ�ĸ����ļ�����node�����file���ι���
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
	//�ж��ǲ���Ҷ�� 
	public boolean isLeaf(Object node) {				
			return false;                             
	}

	public void removeTreeModelListener(TreeModelListener l) {
		
	}

	public void valueForPathChanged(TreePath path, Object newValue) {
		
	 }

	

}
