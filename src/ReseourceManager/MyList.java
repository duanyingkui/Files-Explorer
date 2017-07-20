package ReseourceManager;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

@SuppressWarnings({ "serial", "rawtypes" })
public class MyList extends JList implements ListSelectionListener {
	Node node;
	Object object;

	@SuppressWarnings("unchecked")
	public MyList() {
		this.setFixedCellHeight(20); // 设置list目录显示的高度
		this.setModel(new ListModel()); // 设置MyList的模型，传入下边的ListModel
		this.setCellRenderer(new MyCellRenderer()); // 渲染器
	}

	public void setnode(Node node) { // 设置节点
		this.node = node;
	}

	public class ListModel implements javax.swing.ListModel { // 类MyList.ListModel必须实现继承的抽象方法}
		public int getSize() {

			if (node == null)
				return 0;
			else
				return node.getcount();
		}

		public Object getElementAt(int index) { // 索引值
			// System.out.println("used");
			if (node == null)
				return 0;
			else
				// System.out.println(node.files.length+"length");
				// System.out.println(node.count);
				return new Node(node.files[index], null);
		}

		public void addListDataListener(ListDataListener l) {

		}

		public void removeListDataListener(ListDataListener l) {
		}
	}

	public void valueChanged(ListSelectionEvent e) {
		object = this.getSelectedValue(); // 当前选中路径
	}

	// 设置List列表点击空白处的时候取消选中;
	public int locationToIndex(Point location) {
		int index = super.locationToIndex(location);
		if (index != -1 && !getCellBounds(index, index).contains(location)) {
			clearSelection();
			return -1;
		} else {
			return index;
		}
	}

	// 渲染器
	class MyCellRenderer extends JLabel implements ListCellRenderer {
		/**
		 * 
		 */

		public MyCellRenderer() {
			setOpaque(true); // 当前选中的显示图标可见
		}

		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			setText(value.toString()); // 获取当前选中项的文件目录
			Node node = (Node) value;
			Icon icon = node.getIcon();
			setIcon(icon); // 获取文件夹以及文件的图标
			Color background;
			Color foreground;
			// 返回对组件DnD操作期间此组件应该可见地指示为放置位置的位置；如果当前没有显示任何位置，则返回 null。
			JList.DropLocation dropLocation = list.getDropLocation();
			if (dropLocation != null && !dropLocation.isInsert() && dropLocation.getIndex() == index) {
				background = Color.BLUE;
				foreground = Color.WHITE;
			} else if (isSelected) { // 当前选中列表的颜色
				background = Color.orange;
				foreground = Color.WHITE;
			} else {
				background = Color.WHITE;
				foreground = Color.BLACK;
			}
			;
			setBackground(background);
			setForeground(foreground);
			return this;
		}
	}
}