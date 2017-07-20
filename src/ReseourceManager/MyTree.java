package ReseourceManager;
import java.awt.Component;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.DefaultTreeCellRenderer;

@SuppressWarnings("serial")
public class MyTree extends JTree implements TreeSelectionListener{
	MyFrame frame;
	MyList list;
	Object object;
	public MyTree(MyList list){
		this.list = list;
		setModel(new MyTreeModel(new Node()));
		this.addTreeSelectionListener(this);
		this.setCellRenderer(new FolderRenderer());
	}
	
	public void valueChanged(TreeSelectionEvent e) {
		object = this.getLastSelectedPathComponent();		//��ǰѡ��·��
		list.setnode((Node)object);										//��ǰѡ��·������list
		list.updateUI();															//ˢ�½���
	}
}

/**
 * ʵ��tree�ڵ�������Ĺ��� 
 */
@SuppressWarnings("serial")
class FolderRenderer extends DefaultTreeCellRenderer {
	public Component getTreeCellRendererComponent(JTree tree, Object object, boolean sel, boolean expanded,
			boolean leaf, int row, boolean hasFocus) {
		Node node = (Node) object;
		Icon icon = node.getIcon();
		setLeafIcon(icon);
		setOpenIcon(icon);
		setClosedIcon(icon);
		return super.getTreeCellRendererComponent(tree, object, sel, expanded, leaf, row, hasFocus);
	}
}