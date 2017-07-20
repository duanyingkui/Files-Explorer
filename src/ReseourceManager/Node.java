package ReseourceManager;
import java.io.File;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileSystemView;

public class Node implements ListSelectionListener{
	FileSystemView fsv=FileSystemView.getFileSystemView();		//���ϵͳ�ļ�
	File root=fsv.getFiles(fsv.getHomeDirectory(), true)[0];			
	
	File file;			
	File[] files;
	int count;
	String path;
	public Node(){
		this.file = root;								//���ļ���ַ(���ڵ�)����Ŀ¼����file
		files =  fsv.getFiles(root, true);		//����ļ�Ŀ¼
		count = files.length;
		path = file.getAbsolutePath();
	}
	
	public Node(File file){
		if (file.isDirectory()){						//isDirectory���Դ˳���·������ʾ���ļ��Ƿ���һ��Ŀ¼
			this.file = file;							//��ǰ���е�file�õ�treemodel��������file��ֵ
			files = fsv.getFiles(file, true);
			count = files.length;
			path = this.file.getAbsolutePath();
		}
	}
	//�����ļ����µ��ӽڵ㣨��treemodel�е�getchild���صõ��ģ�
	@SuppressWarnings("rawtypes")
	public Node(File file,JList list){
		this.file = file;
		files= fsv.getFiles(file, true);
		count = files.length;
	}
	//���ش˽ڵ���ӽڵ�������Ϊ�ļ��϶࣬��Ҫ��ȡ�ļ���Ŀ
	public int getcount(){
		return count;                                                                                                                                                                                                                                                                                                                                                       
	}
	public String getpath(){
		if(path!=null){
			return path;
		}
		return null;
	}
	public String toString(){
		return fsv.getSystemDisplayName(file);
	}
	//��ϵͳ�ļ����������ʾ���ļ���Ŀ¼���ļ��е�ͼ�ꡣ
	public Icon getIcon() {						
        return fsv.getSystemIcon(file);
    }
	
	public void valueChanged(ListSelectionEvent e) {
		
	}
	
}
