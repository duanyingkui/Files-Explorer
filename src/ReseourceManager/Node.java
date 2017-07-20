package ReseourceManager;
import java.io.File;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileSystemView;

public class Node implements ListSelectionListener{
	FileSystemView fsv=FileSystemView.getFileSystemView();		//获得系统文件
	File root=fsv.getFiles(fsv.getHomeDirectory(), true)[0];			
	
	File file;			
	File[] files;
	int count;
	String path;
	public Node(){
		this.file = root;								//传文件地址(父节点)，把目录传给file
		files =  fsv.getFiles(root, true);		//获得文件目录
		count = files.length;
		path = file.getAbsolutePath();
	}
	
	public Node(File file){
		if (file.isDirectory()){						//isDirectory测试此抽象路径名表示的文件是否是一个目录
			this.file = file;							//当前类中的file得到treemodel传进来的file的值
			files = fsv.getFiles(file, true);
			count = files.length;
			path = this.file.getAbsolutePath();
		}
	}
	//各个文件夹下的子节点（从treemodel中的getchild返回得到的）
	@SuppressWarnings("rawtypes")
	public Node(File file,JList list){
		this.file = file;
		files= fsv.getFiles(file, true);
		count = files.length;
	}
	//返回此节点的子节点数。因为文件较多，需要获取文件数目
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
	//在系统文件浏览器中显示的文件、目录或文件夹的图标。
	public Icon getIcon() {						
        return fsv.getSystemIcon(file);
    }
	
	public void valueChanged(ListSelectionEvent e) {
		
	}
	
}
