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
 	JSplitPane jsp;																	//�ָ����
 	JScrollPane jslp1;																//�������1
 	JScrollPane jslp2;																//�������2
	//������
 	JToolBar jtb;   
 	JTextField jtf1,jtf2;
	ImageIcon icon1,icon2,icon3,icon4;
	JButton back,forward,refresh,search;
	
	//�˵���
	JMenuBar jmb;
	JMenu file,edit;																	//�˵���  
	JMenuItem open,exit,copy,cut,paste,rename,delete,refresh1; 
																							//�򿪣��½����༭�����ƣ����У�ճ����ɾ��, ������ ����Windows
	MyList list = new MyList();
 	MyTree tree = new MyTree(list);										//��list�ӵ�tree��
 	JPopupMenu popuplist = new JPopupMenu();					//�б�ĵ����˵�
 	JPopupMenu popuptree = new JPopupMenu();				//���ĵ����˵�
 	/**
 	 * 
 	 */
 	public MyFrame(){
		frame = new JFrame();
		jtb = new JToolBar();														
		jmb = new JMenuBar();													
		jtf1 = new JTextField();													
    	jtf2 = new JTextField(" ");
    	//�ڷָ��������ӹ������
		jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,new JScrollPane(tree),new JScrollPane(list));
		//����������
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
    	//�Ӳ˵�           
		file = new JMenu("�ļ�(F)");
		edit = new JMenu("�༭(E)");
		open = new JMenuItem("��");
		exit = new JMenuItem("�ر�");
		copy = new JMenuItem("����(C)");
		cut = new JMenuItem("����(X)");
		paste = new JMenuItem("ճ��(V)");
		delete = new JMenuItem("ɾ��(D)");
		rename = new JMenuItem("������");
		refresh1 = new JMenuItem("ˢ��");
	
		//�Ӳ˵�����˵�
		file.add(open);
		file.add(exit);
		edit.add(copy);
		edit.add(cut);
		edit.add(paste);
		edit.add(delete);
		edit.add(rename);
		//�˵����ı߿���
		jmb.setBorder(new BevelBorder(BevelBorder.RAISED));
		//�˵�����˵���
		jmb.add(file);
		jmb.add(edit);
    	//���߼��빤����
		jtb.add(back);
    	jtb.add(forward);
    	jtb.add(jtf1);
    	jtb.add(refresh);
    	jtb.add(jtf2);
    	jtb.add(search);
    	//�ָ��������
    	jsp.setDividerSize(5);									//�ָ�����СΪ5
        jsp.setDividerLocation(200);						//�ָ�����λ����СΪ200�����������1�ĳ�ʼ��ʾ���
        jsp.setVisible(true);
        //���������ָ����˵������봰��
        Image image = frame.getToolkit().getImage("images\\PC.png");
        frame.setIconImage(image);
        frame.add(jtb,BorderLayout.NORTH);
        frame.add(jsp,BorderLayout.CENTER);
        frame.setJMenuBar(jmb); 							//�Ѳ˵�����ӵ�jsp�ָ������
        frame.setTitle("��Դ������");
        frame.setSize(880,670);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        //�б�ĵ����Ӳ˵�
        JMenuItem openlist = new JMenuItem("��");
        JMenu builtlist = new JMenu("�½�");
        JMenuItem createfolder = new JMenuItem("�ļ���");
        JMenuItem createtxt = new JMenuItem("�ı��ĵ�");
        JMenuItem createword = new JMenuItem("Word�ĵ�");
        JMenuItem createppt = new JMenuItem("PPT��ʾ�ĸ�");
        JMenuItem createexcel= new JMenuItem("Excel������");
        JMenuItem renamelist = new JMenuItem("������");
        JMenuItem copylist = new JMenuItem("����(C)");
        JMenuItem cutlist = new JMenuItem("����(X)");
        JMenuItem pastelist = new JMenuItem("ճ��(V)");
        JMenuItem deletelist = new JMenuItem("ɾ��(D)");
        JMenuItem refresh1list = new JMenuItem("ˢ��");
        JMenuItem attributelist = new JMenuItem("����");
        //���ơ����С�ճ����ɾ���Ŀ�ݼ�
        copylist.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.CTRL_MASK));
        cutlist.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,InputEvent.CTRL_MASK));
        pastelist.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,InputEvent.CTRL_MASK));
        deletelist.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,InputEvent.CTRL_MASK));
    
        //�б���Ӳ˵�
        popuplist.add(openlist);
        popuplist.add(builtlist);
        popuplist.add(renamelist );
        popuplist.add(copylist);
        popuplist.add(cutlist);
        popuplist.add(pastelist); 
        popuplist.add(deletelist);
        popuplist.add(refresh1list);
        popuplist.add(attributelist);
        //�б����½����Ӳ˵�
        builtlist.add(createfolder);
        builtlist.add(createtxt);
        builtlist.add(createword);
        builtlist.add(createppt);
        builtlist.add(createexcel);
        //�½��ļ���
        createfolder.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		builtFiles(jtf1.getText(),"\\�½��ļ���");
        	}
        });
        //�½��ļ��ĵ�
        createtxt.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		builtFile(jtf1.getText(),"�½��ı��ĵ�",".txt");
        		System.out.println(jtf1.getText());
        	}
        });
        //�½�Word�ĵ�
        createword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				builtFile(jtf1.getText(),"�½�Word�ĵ�",".docx");
			}
		});
        //�½�PPT��ʾ�ĸ�
        createppt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				builtFile(jtf1.getText(),"�½�PPT��ʾ�ĸ�",".pptx");
			}
		});
        //�½�Excel������
        createexcel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				builtFile(jtf1.getText(),"�½�Excel������",".xlsx");
			}
		});
        
        //���Եļ���
        attributelist.addActionListener(new ActionListener() {  
             public void actionPerformed(ActionEvent event){
            	 attribute();
             }
      });
        //�������ļ���
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
        //���Ƶļ���
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
		//���еļ���
		cut.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cutWhether=true;
					cutname = list.getSelectedValue().toString();
					cutpath =jtf1.getText()+File.separator+cutname;
					System.out.println("����"+cutpath);
			}
		});
		cutlist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cutWhether=true;
				cutname = list.getSelectedValue().toString();
				cutpath =jtf1.getText()+File.separator+cutname;
				System.out.println("����"+cutpath);
			}
		});
		
		//ճ���ļ���
		//����ճ��
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
		//����ճ��
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
		//ɾ���ļ���
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
 		//���ļ���	
 		MouseAdapter mouseAdaptertree = new MouseAdapter() {
 			public void mouseClicked(MouseEvent e){
 				if (e.getButton() == MouseEvent.BUTTON3){
 					popuptree.show(tree, e.getX(), e.getY());
 				}
 				jtf1.setText(((Node)tree.object).file.toString());
 			}  
 		};
 		tree.addMouseListener(mouseAdaptertree);
 		//�˵���"��"
 		open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					list.setnode((Node)list.getSelectedValue());
					list.updateUI();
			}
	});	
 		//�б����
 		MouseAdapter mouseAdapterlist = new MouseAdapter() {
 			public void mouseClicked(MouseEvent e) {
				//�Ҽ���
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
 	 		    	list.setSelectedIndex(e.getY()/20); 	//��ȡ��ǰѡ��������� 
 	 		    	}
 	 			// ˫����
 	 			if(e.getClickCount()==2){
 	 				Object node;				//����һ���ڵ�
 	 				String string;
 	 				node = list.getSelectedValue();
 	 				string = ((Node)node).file.toString();
 	 				File file = new File(string);
				
	 				if(!file.isFile()){							//������ļ��У�����ļ���
	 					jtf1.setText(jtf1.getText()+File.separator+node);
	 					list.setnode((Node)node);
	 					list.updateUI();
					}
 	 				else if (file.isFile()){					//������ļ�������ļ�
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
 		//����Ҽ���
		ActionListener  open1= new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand()=="��"){
				System.out.println("openlist");	
				Object node;									//����һ���ֽ�
				String string;
				node = list.getSelectedValue();		//��ȡ��ǰѡ�е�ַ
				string = ((Node)node).file.toString();
				File file = new File(string);
	 				if(!file.isFile()){							//������ļ��У�����ļ���
 	 					list.setnode((Node)node);
 	 					list.updateUI();
					}
 	 				else if (file.isFile()){					//������ļ�������ļ�
 	 					Desktop desktop = Desktop.getDesktop();
 	 					try{
 	 						desktop.open(file);
 	 					}catch(IOException e1){}
 	 				}
				}
			}
	};
	openlist.addActionListener(open1);
		// ˢ�µļ���
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
		//ǰ��
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
		// ����
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
					jtf1.setText("��̨����");
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
 		//���رա��ļ���
 		ActionListener listen = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == exit){
					System.exit(-1);
				}
			}
		};
 		exit.addActionListener(listen);
 	}
 	
 	//���췽��
 	//�½��ļ��ķ���
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
 	//�½��ļ��еķ���
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
 	//�������ķ���
 	 	@SuppressWarnings("unused")
		public void reName(){
 			File thisfile = new File(jtf1.getText()+File.separator+list.getSelectedValue());
 			String oldname = thisfile.getName();
 			String inputValue = JOptionPane.showInputDialog("������"+"\n"+"��ʾ��"+"�������ļ�����Ӻ�׺��������"+"\n"+"             �ļ�������Ϊ���� \\ * / ? �� < > | �ո�"+"\n"+"���棺"+"��Ҫ�ı��׺���������أ�����");
 			File inputfile = new File(inputValue);
 			if(inputValue.isEmpty()){
 				JOptionPane.showMessageDialog(null, "û����������","���󣡣���" , JOptionPane.ERROR_MESSAGE);
 			}else if(!inputValue.isEmpty()){
 				if(inputValue.equals(oldname)){
 					JOptionPane.showMessageDialog(null, "����û�з����ı�","���󣡣���" , JOptionPane.ERROR_MESSAGE);
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

	// ���Ƶķ���
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
				fos.close();// �ر���
				File file1 = new File(jtf1.getText());
				Node node = new Node(file1);
				list.setnode(node);
				list.updateUI();
				tree.updateUI();
				System.out.println("�ļ����Ƴɹ�");
			} else if (fi1.isDirectory()) {
				File file = new File(pastepath + File.separator + fi1.getName());
				pasteFiles(fi1.listFiles(), file);
				File file1 = new File(jtf1.getText());
				Node node = new Node(file1);
				list.setnode(node);
				list.updateUI();
				tree.updateUI();
				System.out.println("�ļ��и��Ƴɹ�");
			}
		}
	}

	// ���еķ���
	@SuppressWarnings("unused")
	public void cutpaste(String pastepath) throws IOException {
		System.out.println(cutpath);
		if (cutpath != null) {
			File fi1 = new File(cutpath);
			if (fi1.isFile()) {
				System.out.println("�ļ�");
				str = pastepath + File.separator + cutname;
				File fi2 = new File(str);
				fi2.createNewFile();
				FileInputStream fis = new FileInputStream(fi1);// ������
				FileOutputStream fos = new FileOutputStream(fi2);// �����
				byte[] b = new byte[5 * 1024];
				int len;
				while ((len = fis.read(b)) != -1) {
					fos.write(b);
				}
				fis.close();
				fos.close();// �ر���
				File file1 = new File(jtf1.getText());
				Node node = new Node(file1);
				list.setnode(node);
				list.updateUI();
				tree.updateUI();
				System.out.println("�ļ����гɹ�");
			}else  {	
					System.out.println("�ļ���");
					File file = new File(pastepath+"\\"+fi1.getName());
					pasteFiles(fi1.listFiles(), file);
					System.err.println("�ļ��м��гɹ�");
					
					File file1 = new File(jtf1.getText());
					Node node  = new Node(file1);
					list.setnode(node);
					list.updateUI();
					tree.updateUI();
			delete(cutpath);
			}
		}
	}
	// ճ���ķ���
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
					fis.close(); // �ر���
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
 		//ɾ���ķ���
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
 		//�ļ���С
 		public long getFileSize(File f) throws Exception {
 		       @SuppressWarnings("unused")
			long s=0;
 		       return s = f.length();
 		}
 		//�ļ��д�С
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
 		//���ԵĹ��췽��
 		public void attribute() {
 			Object object = list.getSelectedValue();
 			if (object != null) {
 				File f = new File(jtf1.getText() + list.getSelectedValue());
 				//�ļ��д�С�ļ���
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
 				//�ļ���С�ļ���
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

 				SimpleDateFormat s = new SimpleDateFormat("yyyy��MM��dd��HH ʱmm��ss��");
 				System.out.println("�ļ��Ĵ�С:" + lengths + unit);
 				JOptionPane.showMessageDialog(frame,
 						"·����" + f.getAbsolutePath() + "\n" + "�Ƿ�ɶ���" + f.canRead() + "\n" + "�Ƿ��д��" + f.canWrite() + "\n"
 								+ "�ļ��Ĵ�С��" + "" + lengths + unit + "�ļ��Ƿ�����:" + f.isHidden() + "\n" + "�ļ��ϴ��޸ĵ�ʱ��:"
 								+ s.format(new Date(f.lastModified())),
 						"����", 1);

 			} else {
 				JOptionPane.showMessageDialog(frame, "û��ѡ���ļ���", "����", 2);
 			}
 		}
 	
	public static void main(String[] e) {                                                                                       
		new MyFrame();
	}

}
