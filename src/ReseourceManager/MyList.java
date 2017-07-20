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
		this.setFixedCellHeight(20); // ����listĿ¼��ʾ�ĸ߶�
		this.setModel(new ListModel()); // ����MyList��ģ�ͣ������±ߵ�ListModel
		this.setCellRenderer(new MyCellRenderer()); // ��Ⱦ��
	}

	public void setnode(Node node) { // ���ýڵ�
		this.node = node;
	}

	public class ListModel implements javax.swing.ListModel { // ��MyList.ListModel����ʵ�ּ̳еĳ��󷽷�}
		public int getSize() {

			if (node == null)
				return 0;
			else
				return node.getcount();
		}

		public Object getElementAt(int index) { // ����ֵ
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
		object = this.getSelectedValue(); // ��ǰѡ��·��
	}

	// ����List�б����հ״���ʱ��ȡ��ѡ��;
	public int locationToIndex(Point location) {
		int index = super.locationToIndex(location);
		if (index != -1 && !getCellBounds(index, index).contains(location)) {
			clearSelection();
			return -1;
		} else {
			return index;
		}
	}

	// ��Ⱦ��
	class MyCellRenderer extends JLabel implements ListCellRenderer {
		/**
		 * 
		 */

		public MyCellRenderer() {
			setOpaque(true); // ��ǰѡ�е���ʾͼ��ɼ�
		}

		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			setText(value.toString()); // ��ȡ��ǰѡ������ļ�Ŀ¼
			Node node = (Node) value;
			Icon icon = node.getIcon();
			setIcon(icon); // ��ȡ�ļ����Լ��ļ���ͼ��
			Color background;
			Color foreground;
			// ���ض����DnD�����ڼ�����Ӧ�ÿɼ���ָʾΪ����λ�õ�λ�ã������ǰû����ʾ�κ�λ�ã��򷵻� null��
			JList.DropLocation dropLocation = list.getDropLocation();
			if (dropLocation != null && !dropLocation.isInsert() && dropLocation.getIndex() == index) {
				background = Color.BLUE;
				foreground = Color.WHITE;
			} else if (isSelected) { // ��ǰѡ���б����ɫ
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