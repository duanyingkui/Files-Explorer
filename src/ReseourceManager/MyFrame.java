package ReseourceManager;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.BevelBorder;

@SuppressWarnings("serial")
public class MyFrame extends JFrame {
	static String copypath,copyname,cutpath,cutname,pastepath;
	private String str;
	String unit,lengths;
	double length=0;
	Object object;
	int i;
	int j;
	boolean cutWhether =false;
	String [] goButton=new String[50];
	JFrame frame;
 	JSplitPane jsp;																	//分割面板
 	JScrollPane jslp1;																//滚动面板1
 	JScrollPane jslp2;																//滚动面板2
	//工具栏
 	JToolBar jtb;   
 	JTextField jtf1,jtf2;
	ImageIcon icon1,icon2,icon3,icon4;
	JButton back,forward,refresh,search;
	
	//菜单栏
	JMenuBar jmb;
	JMenu file,edit;																	//菜单栏  
	JMenuItem open,exit,copy,cut,paste,rename,delete,refresh1; 
																							//打开，新建，编辑，复制，剪切，粘贴，删除, 帮助， 关于Windows
	MyList list = new MyList();
 	MyTree tree = new MyTree(list);										//把list加到tree上
 	JPopupMenu popuplist = new JPopupMenu();					//列表的弹出菜单
 	JPopupMenu popuptree = new JPopupMenu();				//树的弹出菜单
 	/**
 	 * 
 	 */
 	public MyFrame(){
		frame = new JFrame();
		jtb = new JToolBar();														
		jmb = new JMenuBar();													
		jtf1 = new JTextField();													
    	jtf2 = new JTextField(" ");
    	//在分割面板上添加滚动面板
		jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,new JScrollPane(tree),new JScrollPane(list));
		//工具栏属性
		icon1 = new ImageIcon("images\\back.png");
    	back = new JButton(icon1);
    	icon2 = new ImageIcon("images\\forward.png");
    	forward = new JButton(icon2);
    	icon3 = new ImageIcon("images\\refresh.png");
    	refresh = new JButton(icon3);
    	icon4 = new ImageIcon("images\\search.png");
    	search = new JButton(icon4);
    	back.setBackground(Color.white);
    	forward.setBackground(Color.white);
    	refresh.setBackground(Color.white);
    	search.setBackground(Color.white);
    	//子菜单           
		file = new JMenu("文件(F)");
		edit = new JMenu("编辑(E)");
		open = new JMenuItem("打开");
		exit = new JMenuItem("关闭");
		copy = new JMenuItem("复制(C)");
		cut = new JMenuItem("剪切(X)");
		paste = new JMenuItem("粘贴(V)");
		delete = new JMenuItem("删除(D)");
		rename = new JMenuItem("重命名");
		refresh1 = new JMenuItem("刷新");
	
		//子菜单加入菜单
		file.add(open);
		file.add(exit);
		edit.add(copy);
		edit.add(cut);
		edit.add(paste);
		edit.add(delete);
		edit.add(rename);
		//菜单栏的边框风格
		jmb.setBorder(new BevelBorder(BevelBorder.RAISED));
		//菜单加入菜单栏
		jmb.add(file);
		jmb.add(edit);
    	//工具加入工具栏
		jtb.add(back);
    	jtb.add(forward);
    	jtb.add(jtf1);
    	jtb.add(refresh);
    	jtb.add(jtf2);
    	jtb.add(search);
    	//分割面板属性
    	jsp.setDividerSize(5);									//分隔条大小为5
        jsp.setDividerLocation(200);						//分隔条的位置最小为200，即滚动面板1的初始显示宽度
        jsp.setVisible(true);
        //工具栏，分割面板菜单栏加入窗体
        Image image = frame.getToolkit().getImage("images\\PC.png");
        frame.setIconImage(image);
        frame.add(jtb,BorderLayout.NORTH);
        frame.add(jsp,BorderLayout.CENTER);
        frame.setJMenuBar(jmb); 							//把菜单栏添加到jsp分割面板上
        frame.setTitle("资源管理器");
        frame.setSize(880,670);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        //列表的弹出子菜单
        JMenuItem openlist = new JMenuItem("打开");
        JMenu builtlist = new JMenu("新建");
        JMenuItem createfolder = new JMenuItem("文件夹");
        JMenuItem createtxt = new JMenuItem("文本文档");
        JMenuItem createword = new JMenuItem("Word文档");
        JMenuItem createppt = new JMenuItem("PPT演示文稿");
        JMenuItem createexcel= new JMenuItem("Excel工作表");
        JMenuItem renamelist = new JMenuItem("重命名");
        JMenuItem copylist = new JMenuItem("复制(C)");
        JMenuItem cutlist = new JMenuItem("剪切(X)");
        JMenuItem pastelist = new JMenuItem("粘贴(V)");
        JMenuItem deletelist = new JMenuItem("删除(D)");
        JMenuItem refresh1list = new JMenuItem("刷新");
        JMenuItem attributelist = new JMenuItem("属性");
        //复制、剪切、粘贴、删除的快捷键
        copylist.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.CTRL_MASK));
        cutlist.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,InputEvent.CTRL_MASK));
        pastelist.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,InputEvent.CTRL_MASK));
        deletelist.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,InputEvent.CTRL_MASK));
    
        //列表的子菜单
        popuplist.add(openlist);
        popuplist.add(builtlist);
        popuplist.add(renamelist );
        popuplist.add(copylist);
        popuplist.add(cutlist);
        popuplist.add(pastelist); 
        popuplist.add(deletelist);
        popuplist.add(refresh1list);
        popuplist.add(attributelist);
        //列表中新建的子菜单
        builtlist.add(createfolder);
        builtlist.add(createtxt);
        builtlist.add(createword);
        builtlist.add(createppt);
        builtlist.add(createexcel);
        //新建文件夹
        createfolder.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		builtFiles(jtf1.getText(),"\\新建文件夹");
        	}
        });
        //新建文件文档
        createtxt.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		builtFile(jtf1.getText(),"新建文本文档",".txt");
        		System.out.println(jtf1.getText());
        	}
        });
        //新建Word文档
        createword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				builtFile(jtf1.getText(),"新建Word文档",".docx");
			}
		});
        //新建PPT演示文稿
        createppt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				builtFile(jtf1.getText(),"新建PPT演示文稿",".pptx");
			}
		});
        //新建Excel工作表
        createexcel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				builtFile(jtf1.getText(),"新建Excel工作表",".xlsx");
			}
		});
        
        //属性的监听
        attributelist.addActionListener(new ActionListener() {  
             public void actionPerformed(ActionEvent event){
            	 attribute();
             }
      });
        //重命名的监听
        rename.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				reName();				
			}
		});
        renamelist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reName();
			}
		});
        //复制的监听
        copy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cutWhether=true;
				copyname = list.getSelectedValue().toString();
				copypath =jtf1.getText()+File.separator+copyname;
			}
		});
		copylist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				copyname = list.getSelectedValue().toString();
				copypath =jtf1.getText()+File.separator+copyname;
			}
		});
		//剪切的监听
		cut.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cutWhether=true;
					cutname = list.getSelectedValue().toString();
					cutpath =jtf1.getText()+File.separator+cutname;
					System.out.println("监听"+cutpath);
			}
		});
		cutlist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cutWhether=true;
				cutname = list.getSelectedValue().toString();
				cutpath =jtf1.getText()+File.separator+cutname;
				System.out.println("监听"+cutpath);
			}
		});
		
		//粘贴的监听
		//剪切粘贴
		paste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pastepath = jtf1.getText();
				try {
					cutpaste(pastepath);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		pastelist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pastepath = jtf1.getText();
					try {
						cutpaste(pastepath);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			}
		});
		//复制粘贴
		paste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pastepath = jtf1.getText();
				try {
					paste(pastepath);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		pastelist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pastepath = jtf1.getText();
					try {
						paste(pastepath);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			}
		});
		//删除的监听
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				delete(((Node)(list.getSelectedValue())).file.getPath());
			}
		});
		deletelist.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				delete(((Node)(list.getSelectedValue())).file.getPath());
			}
		});
 		//树的监听	
 		MouseAdapter mouseAdaptertree = new MouseAdapter() {
 			public void mouseClicked(MouseEvent e){
 				if (e.getButton() == MouseEvent.BUTTON3){
 					popuptree.show(tree, e.getX(), e.getY());
 				}
 				jtf1.setText(((Node)tree.object).file.toString());
 			}  
 		};
 		tree.addMouseListener(mouseAdaptertree);
 		//菜单栏"打开"
 		open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					list.setnode((Node)list.getSelectedValue());
					list.updateUI();
			}
	});	
 		//列表监听
 		MouseAdapter mouseAdapterlist = new MouseAdapter() {
 			public void mouseClicked(MouseEvent e) {
				//右键打开
 				if(list.isSelectionEmpty()){
					list.clearSelection();
					openlist.setEnabled(false);
					//builtlist.setEnabled(false);
					deletelist.setEnabled(false);
					cutlist.setEnabled(false);
					copylist.setEnabled(false);
					//pastelist.setEnabled(false);
					renamelist.setEnabled(false);
					attributelist.setEnabled(false);
				}else{
					openlist.setEnabled(true);
					//builtlist.setEnabled(true);
					deletelist.setEnabled(true);
					cutlist.setEnabled(true);
					copylist.setEnabled(true);
					//pastelist.setEnabled(true);
					renamelist.setEnabled(true);
					attributelist.setEnabled(true);
				}
 	 			if (e.getButton() == MouseEvent.BUTTON3){
 	 				list.getSelectedValue();
 	 				popuplist.show(list, e.getX(), e.getY()); 
 	 		    	list.setSelectedIndex(e.getY()/20); 	//获取当前选择的纵坐标 
 	 		    	}
 	 			// 双击打开
 	 			if(e.getClickCount()==2){
 	 				Object node;				//定义一个节点
 	 				String string;
 	 				node = list.getSelectedValue();
 	 				string = ((Node)node).file.toString();
 	 				File file = new File(string);
				
	 				if(!file.isFile()){							//如果是文件夹，则打开文件夹
	 					jtf1.setText(jtf1.getText()+File.separator+node);
	 					list.setnode((Node)node);
	 					list.updateUI();
					}
 	 				else if (file.isFile()){					//如果是文件，则打开文件
 	 					Desktop desktop = Desktop.getDesktop();
 	 					try{
 	 						desktop.open(file);
 	 					}catch(IOException e1){}
 	 				}
	 				list.clearSelection();
				}
 			}			
 		};
 		list.addMouseListener(mouseAdapterlist);
 		//鼠标右键打开
		ActionListener  open1= new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand()=="打开"){
				System.out.println("openlist");	
				Object node;									//定义一个字节
				String string;
				node = list.getSelectedValue();		//获取当前选中地址
				string = ((Node)node).file.toString();
				File file = new File(string);
	 				if(!file.isFile()){							//如果是文件夹，则打开文件夹
 	 					list.setnode((Node)node);
 	 					list.updateUI();
					}
 	 				else if (file.isFile()){					//如果是文件，则打开文件
 	 					Desktop desktop = Desktop.getDesktop();
 	 					try{
 	 						desktop.open(file);
 	 					}catch(IOException e1){}
 	 				}
				}
			}
	};
	openlist.addActionListener(open1);
		// 刷新的监听
		refresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				File file = new File(jtf1.getText());
				Node node = new Node(file);
				list.setnode(node);
				list.clearSelection();
				tree.updateUI();
				list.updateUI();
			}
		});
		refresh1list.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File file = new File(jtf1.getText());
				Node node = new Node(file);
				list.setnode(node);
				list.clearSelection();
				tree.updateUI();
				list.updateUI();
			}
		});
		//前进
		forward.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(j==0){
					j=0;
					forward.setEnabled(false);
				}else{	
					j--;
					Node node = new Node(new File(goButton[j]));
					jtf1.setText(goButton[j]);
					list.setnode(node);
					tree.updateUI();
					list.updateUI();
				}
			}
		});
		// 后退
		back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				forward.setEnabled(true);
				goButton[j]=jtf1.getText();
				j++; 
				String path[] = jtf1.getText().split("\\\\");
				StringBuilder stringBuilder = new StringBuilder();
				int pathCount = path.length - 1;
				if (pathCount == 0) {
					jtf1.setText("这台电脑");
				}
				for (int i = 0; i < pathCount; i++) {
					stringBuilder.append(path[i]).append(File.separator);
				}
				Node node = null;
				if (stringBuilder.toString().equals("")) {
					node = new Node();
				} else {
					node = new Node(new File(stringBuilder.toString()));
					jtf1.setText(stringBuilder.toString());
				}
				list.setnode(node);
				tree.updateUI();
				list.updateUI();
			}
		});
 		//“关闭”的监听
 		ActionListener listen = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == exit){
					System.exit(-1);
				}
			}
		};
 		exit.addActionListener(listen);
 	}
 	
 	//构造方法
 	//新建文件的方法
 	public void builtFile(String bulitpath,String bulitname,String bulitsuffix){
 		System.out.println(bulitpath);
 		File file = new File(bulitpath+File.separator+bulitname+bulitsuffix);
 		if(!file.exists()){
 			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
 			Node node = new Node(new File(jtf1.getText()));
 			list.setnode(node);
 			list.updateUI();
 			tree.updateUI();
 			i=1;
 			
 		}else {
 			while(file.exists()){
 				file = new File(bulitpath+File.separator+bulitname+"("+( i++)+")"+bulitsuffix);
 			}
 			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
 			Node node = new Node(new File(jtf1.getText()));
 			list.setnode(node);
 			list.updateUI();
 			tree.updateUI();
 			i=1;
 		}
 	}
 	//新建文件夹的方法
 	public void builtFiles(String bulitpath,String bulitname){
		File file = new File(bulitpath+bulitname);
		if(!file.exists()){
			file.mkdir();
			Node node = new Node(new File(jtf1.getText()));
			list.setnode(node);
			list.updateUI();
			tree.updateUI();
			i=1;
		}else {
			while(file.exists()){
					file = new File(bulitpath+bulitname+"("+ (i++) +")");
			}
			file.mkdir();
			Node node = new Node(new File(jtf1.getText()));
			list.setnode(node);
			list.updateUI();
			tree.updateUI();
			i=1;
		}
	}
 	//重命名的方法
 	 	@SuppressWarnings("unused")
		public void reName(){
 			File thisfile = new File(jtf1.getText()+File.separator+list.getSelectedValue());
 			String oldname = thisfile.getName();
 			String inputValue = JOptionPane.showInputDialog("重命名"+"\n"+"提示："+"重命名文件后，请加后缀名！！！"+"\n"+"             文件名不能为符号 \\ * / ? ” < > | 空格"+"\n"+"警告："+"若要改变后缀名，请慎重！！！");
 			File inputfile = new File(inputValue);
 			if(inputValue.isEmpty()){
 				JOptionPane.showMessageDialog(null, "没有输入名称","错误！！！" , JOptionPane.ERROR_MESSAGE);
 			}else if(!inputValue.isEmpty()){
 				if(inputValue.equals(oldname)){
 					JOptionPane.showMessageDialog(null, "名称没有发生改变","错误！！！" , JOptionPane.ERROR_MESSAGE);
 					}else if(!inputValue.equals(oldname)){
 						System.out.println(inputValue);
 						System.out.println(oldname);
 					thisfile.renameTo(new File(jtf1.getText()+File.separator+inputValue));
 					Node node = new Node(new File(jtf1.getText()));
 					list.setnode(node);
 					list.updateUI();
 					tree.updateUI();
 				}
 			}
 		}

	// 复制的方法
	@SuppressWarnings("unused")
	public void paste(String pastepath) throws IOException {
		System.out.println(copypath);
		if (copypath != null) {
			File fi1 = new File(copypath);
			if (fi1.isFile()) {
				str = pastepath + File.separator + copyname;
				BufferedInputStream fis = new BufferedInputStream(new FileInputStream(fi1));
				BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(str));
				byte[] b = new byte[5 * 1024];
				int length;
				while ((length = fis.read(b)) != -1) {
					fos.write(b);
				}
				fos.flush();
				fis.close();
				fos.close();// 关闭流
				File file1 = new File(jtf1.getText());
				Node node = new Node(file1);
				list.setnode(node);
				list.updateUI();
				tree.updateUI();
				System.out.println("文件复制成功");
			} else if (fi1.isDirectory()) {
				File file = new File(pastepath + File.separator + fi1.getName());
				pasteFiles(fi1.listFiles(), file);
				File file1 = new File(jtf1.getText());
				Node node = new Node(file1);
				list.setnode(node);
				list.updateUI();
				tree.updateUI();
				System.out.println("文件夹复制成功");
			}
		}
	}

	// 剪切的方法
	@SuppressWarnings("unused")
	public void cutpaste(String pastepath) throws IOException {
		System.out.println(cutpath);
		if (cutpath != null) {
			File fi1 = new File(cutpath);
			if (fi1.isFile()) {
				System.out.println("文件");
				str = pastepath + File.separator + cutname;
				File fi2 = new File(str);
				fi2.createNewFile();
				FileInputStream fis = new FileInputStream(fi1);// 输入流
				FileOutputStream fos = new FileOutputStream(fi2);// 输出流
				byte[] b = new byte[5 * 1024];
				int len;
				while ((len = fis.read(b)) != -1) {
					fos.write(b);
				}
				fis.close();
				fos.close();// 关闭流
				File file1 = new File(jtf1.getText());
				Node node = new Node(file1);
				list.setnode(node);
				list.updateUI();
				tree.updateUI();
				System.out.println("文件剪切成功");
			}else  {	
					System.out.println("文件夹");
					File file = new File(pastepath+"\\"+fi1.getName());
					pasteFiles(fi1.listFiles(), file);
					System.err.println("文件夹剪切成功");
					
					File file1 = new File(jtf1.getText());
					Node node  = new Node(file1);
					list.setnode(node);
					list.updateUI();
					tree.updateUI();
			delete(cutpath);
			}
		}
	}
	// 粘贴的方法
	@SuppressWarnings("unused")
	public static void pasteFiles(File[] f, File file) {
		if (!file.exists())
			file.mkdir();
		for (int i = 0; i < f.length; i++) {
			if (f[i].isFile()) {
				try {
					BufferedInputStream fis = new BufferedInputStream(new FileInputStream(f[i]));
					BufferedOutputStream fos = new BufferedOutputStream(
							new FileOutputStream(new File(file.getPath() + File.separator + f[i].getName())));
					byte[] b = new byte[5 * 1024];
					int length;
					if ((fis.read(b)) != -1) {
						fos.write(b);
					}
					fos.flush();
					fos.close();
					fis.close(); // 关闭流
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (f[i].isDirectory()) {
				File fi = new File(file.getPath() + "/" + f[i].getName());
				fi.mkdir();
				pasteFiles(f[i].listFiles(), fi);
			}
		}
	}
 		//删除的方法
 		public void delete(String listobject) {
 			File file = new File(listobject);
 			Node node = null;
 			if(!(file.isDirectory())){
 				file.delete();
 				node  = new Node(file);
 				list.setnode(node);
 				list.updateUI();
 				tree.updateUI();
 			}else if(file.isDirectory()){
 				File[] fileList = file.listFiles();
 				for (int i = 0; i < fileList.length; i++) {
 					if (!fileList[i].isDirectory()) {
 						fileList[i].delete();
 					}else if(fileList[i].isDirectory()){
 						delete(fileList[i].getPath());
 					} 
 				}
 				file.delete();
 			}
 			node  = new Node(new File(jtf1.getText()));
 			list.setnode(node);
 			list.updateUI();
 			tree.updateUI();
 		}
 		//文件大小
 		public long getFileSize(File f) throws Exception {
 		       @SuppressWarnings("unused")
			long s=0;
 		       return s = f.length();
 		}
 		//文件夹大小
 		public long getDirectorySize(File f)throws NullPointerException {
 	       long size = 0;
 	       File flie[] = f.listFiles();
 	       for (int i = 0; i < flie.length; i++)
 	       {
 	           if (flie[i].isDirectory()) {
 	               size = size + getDirectorySize(flie[i]);
 	           } else {
 	               size = size + flie[i].length();
 	           }
 	       }
 	       return size;
 	    }
 		//属性的构造方法
 		public void attribute() {
 			Object object = list.getSelectedValue();
 			if (object != null) {
 				File f = new File(jtf1.getText() + list.getSelectedValue());
 				//文件夹大小的计算
 				if (f.isDirectory()) {
 					length = getDirectorySize(f) + 0.00000000001;

 					if (length < 1025) {
 						length = length;
 						unit = "B\n";
 					}

 					if (length < (1024 * 1024 + 1) && length > 1024) {
 						length = length / 1024;
 						unit = "K\n";
 					}

 					if (length < 1024 * 1024 * 1024 + 1 && length > 1024 * 1024) {
 						length = length / (1024 * 1024);
 						unit = "M\n";
 					}

 					if (length > 1024 * 1024 * 1024) {
 						length = length / (1024 * 1024 * 1024);
 						unit = "G\n";
 					}
 				}
 				//文件大小的计算
 				if (f.isFile()) {
 					if (f.length() < 1025) {
 						length = ((float) f.length() + 0.00001);
 						unit = "B\n";
 					}

 					if (f.length() < (1024 * 1024 + 1) && f.length() > 1024) {
 						length = (float) f.length() / 1024 + 0.00001;
 						unit = "K\n";
 					}

 					if (f.length() < 1024 * 1024 * 1024 + 1 && f.length() > 1024 * 1024) {
 						length = (float) f.length() / (1024 * 1024) + 0.00001;
 						unit = "M\n";
 					}

 					if (f.length() > 1024 * 1024 * 1024) {
 						length = (float) f.length() / (1024 * 1024 * 1024) + 0.0001;
 						unit = "G\n";
 					}
 				}
 				lengths = String.valueOf(length).substring(0, String.valueOf(length).indexOf(".") + 3);
 				if (length == 0.00000000001) {
 					lengths = "0";
 				}

 				SimpleDateFormat s = new SimpleDateFormat("yyyy年MM月dd日HH 时mm分ss秒");
 				System.out.println("文件的大小:" + lengths + unit);
 				JOptionPane.showMessageDialog(frame,
 						"路径：" + f.getAbsolutePath() + "\n" + "是否可读：" + f.canRead() + "\n" + "是否可写：" + f.canWrite() + "\n"
 								+ "文件的大小：" + "" + lengths + unit + "文件是否被隐藏:" + f.isHidden() + "\n" + "文件上次修改的时间:"
 								+ s.format(new Date(f.lastModified())),
 						"属性", 1);

 			} else {
 				JOptionPane.showMessageDialog(frame, "没有选中文件夹", "警告", 2);
 			}
 		}
 	
	public static void main(String[] e) {                                                                                       
		new MyFrame();
	}

}
