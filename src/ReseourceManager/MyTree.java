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
		object = this.getLastSelectedPathComponent();		//当前选中路径
		list.setnode((Node)object);										//当前选中路径传给list
		list.updateUI();															//刷新界面
	}
}

/**
 * 实现tree节点绘制器的功能 
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