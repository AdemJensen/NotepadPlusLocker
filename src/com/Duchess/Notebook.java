package com.Duchess;
import java.awt.*;


import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

import java.util.*;
import java.io.*;
import javax.print.*;
import javax.print.attribute.*;
public class Notebook {

    //窗体
    static JFrame frame = new JFrame("记事本");
    //弹出


    //菜单条
    static JMenuBar bar = new JMenuBar();

    //菜单
    static JMenu fileMenu = new JMenu("文件(F)");
    static JMenu editMenu = new JMenu("编辑(E)");
    static JMenu formatMenu = new JMenu("格式(O)");
    static JMenu checkMenu = new JMenu("查看(V)");
    static JMenu helpMenu = new JMenu("帮助(H)");

    //菜单项
    static JMenuItem menu1 = new JMenuItem("新建(N)");
    static JMenuItem menu2 = new JMenuItem("打开(O)...");
    static JMenuItem menu3= new JMenuItem("保存(S)");
    static JMenuItem menu4 = new JMenuItem("另存为(A)...");

    static JMenuItem menu5 = new JMenuItem("页面设置(U)...");
    static JMenuItem menu6 = new JMenuItem("打印(P)...");

    static JMenuItem menu7= new JMenuItem("退出(X)");


    static JMenuItem menu8= new JMenuItem("撤销(U)");

    static JMenuItem menu9= new JMenuItem("剪切(T)");
    static JMenuItem menu10= new JMenuItem("复制(C)");
    static JMenuItem menu11= new JMenuItem("粘贴(P)");
    static JMenuItem menu12= new JMenuItem("删除(L)");

    static JMenuItem menu13= new JMenuItem("查找(F)...");
    static JMenuItem menu14= new JMenuItem("查找下一个(N)");
    static JMenuItem menu15= new JMenuItem("替换(R)...");
    static JMenuItem menu16= new JMenuItem("转到(G)...");

    static JMenuItem menu17= new JMenuItem("全选(A)");
    static JMenuItem menu18= new JMenuItem("时间/日期(D)");



    static JMenuItem menu19= new JMenuItem("自动换行(W)");

    static JMenuItem menu20= new JMenuItem("字体(F)...");



    static JMenuItem menu21= new JMenuItem("状态栏(S)");



    static JMenuItem menu22= new JMenuItem("查看帮助(H)");

    static JMenuItem menu23 = new JMenuItem("关于记事本(A)");

    //文本域
    static final JTextArea area = new JTextArea(20,20);
    //文本域
    static UndoManager undomg = new UndoManager();



    public static void init(){
        //把菜单添加到菜单条上

        bar.add(fileMenu);
        bar.add(editMenu);
        bar.add(formatMenu);
        bar.add(checkMenu);
        bar.add(helpMenu);
        //把菜单项添加到菜单
        fileMenu.add(menu1);
        fileMenu.add(menu2);
        fileMenu.add(menu3);
        fileMenu.add(menu4);
        JSeparator separator1=new JSeparator();
        fileMenu.add(separator1);
        fileMenu.add(menu5);
        fileMenu.add(menu6);
        JSeparator separator2=new JSeparator();
        fileMenu.add(separator2);
        fileMenu.add(menu7);
        editMenu.add(menu8);
        JSeparator separator3=new JSeparator();
        editMenu.add(separator3);
        editMenu.add(menu9);
        editMenu.add(menu10);
        editMenu.add(menu11);
        editMenu.add(menu12);
        JSeparator separator4=new JSeparator();
        editMenu.add(separator4);
        editMenu.add(menu13);
        editMenu.add(menu14);
        editMenu.add(menu15);
        editMenu.add(menu16);
        JSeparator separator5=new JSeparator();
        editMenu.add(separator5);
        editMenu.add(menu17);
        editMenu.add(menu18);
        formatMenu.add(menu19);
        formatMenu.add(menu20);
        checkMenu.add(menu21);
        helpMenu.add(menu22);
        JSeparator separator6=new JSeparator();
        helpMenu.add(separator6);
        helpMenu.add(menu23);
        //快捷键
        menu1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_MASK));
        menu2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,InputEvent.CTRL_MASK));
        menu3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK));
        menu6.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,InputEvent.CTRL_MASK));
        menu8.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,InputEvent.CTRL_MASK));
        menu9.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,InputEvent.CTRL_MASK));
        menu10.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.CTRL_MASK));
        menu11.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,InputEvent.CTRL_MASK));
        menu12.setAccelerator(KeyStroke.getKeyStroke("Del"));
        menu13.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,InputEvent.CTRL_MASK));
        menu14.setAccelerator(KeyStroke.getKeyStroke("F3"));
        menu15.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,InputEvent.CTRL_MASK));
        menu16.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,InputEvent.CTRL_MASK));
        menu17.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,InputEvent.CTRL_MASK));
        menu18.setAccelerator(KeyStroke.getKeyStroke("F5"));



        //事件







        menu6.addActionListener(new ActionListener() {
            private Component print;

            public void actionPerformed(ActionEvent e) {
                try{
                    //构建打印属性集
                    PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
                    //设置打印格式
                    DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
                    //查找所有的可用打印服务
                    PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
                    PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
                    //显示打印对话框
                    PrintService service = null;
                    service = ServiceUI.printDialog(null, 100, 100, printService, defaultService, flavor, pras);
                    if (service!=null)//
                    {
                        //创建打印作业
                        DocPrintJob job = service.createPrintJob();
                        DocAttributeSet das = new HashDocAttributeSet();
                        //建立打印文件格式
                        Doc doc = new SimpleDoc(area.getText().getBytes(), flavor, das);
                        //进行文件的打印
                        job.print(doc, pras);
                    }
                }catch(Exception e1)
                {

                    JOptionPane.showMessageDialog(print,this,"打印任务无法完成",0);
                }
            }
        });

        menu22.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame,"哈哈哈，我也不知道啊！","帮助主题",JOptionPane.INFORMATION_MESSAGE);
            }
        });

        menu23.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame,"*****************************************\n"+
                        " 编写者：凡鸟 \n"+
                        " 编写时间：2018\n","关于记事本",JOptionPane.INFORMATION_MESSAGE);
            }
        });


        //把菜单条添加到窗体上

        frame.add(bar,BorderLayout.NORTH);

        frame.add(area);
        initFrame(frame, 700, 600);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                // 弹出对话框，警告文件为保存，提示是否保存
                int choice = JOptionPane.showConfirmDialog(frame, "您的文件尚未保存，是否保存？",
                        "提示", JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                switch (choice) {
                    case JOptionPane.YES_OPTION:// 选择“是”
                        FileDialog fileDialog = new FileDialog(frame, "请输入保存的文件名", FileDialog.SAVE);
                        fileDialog.setVisible(true);
                        String path = fileDialog.getDirectory(); // 获取保存文件的路径
                        String fileName = fileDialog.getFile(); //获取保存文件的名字
                        File file = new File(path,fileName);
                        try {
                            //获取文本域的内容
                            String content = area.getText().replaceAll("\n","\r\n");
                            FileWriter fileWriter 	= new FileWriter(file);
                            fileWriter.write(content);
                            fileWriter.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        break;
                    case JOptionPane.NO_OPTION:// 选择“否”
                        System.exit(0);// 退出
                        break;
                    case JOptionPane.CANCEL_OPTION:// 选择“取消”
                        break;
                }
            }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        menu7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 弹出对话框，警告文件为保存，提示是否保存
                int choice = JOptionPane.showConfirmDialog(frame, "您的文件尚未保存，是否保存？",
                        "提示", JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                switch (choice) {
                    case JOptionPane.YES_OPTION:// 选择“是”
                        FileDialog fileDialog = new FileDialog(frame, "请输入保存的文件名", FileDialog.SAVE);
                        fileDialog.setVisible(true);
                        String path = fileDialog.getDirectory(); // 获取保存文件的路径
                        String fileName = fileDialog.getFile(); //获取保存文件的名字
                        File file = new File(path,fileName);
                        try {
                            //获取文本域的内容
                            String content = area.getText().replaceAll("\n","\r\n");
                            FileWriter fileWriter 	= new FileWriter(file);
                            fileWriter.write(content);
                            fileWriter.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        break;
                    case JOptionPane.NO_OPTION:// 选择“否”
                        System.exit(0);// 退出
                        break;
                    case JOptionPane.CANCEL_OPTION:// 选择“取消”
                        break;
                }
            }
        });
    }



    public static void initFrame(final JFrame frame, int width, int height){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension  d = toolkit.getScreenSize();
        int x = (int) d.getWidth();
        int y = (int) d.getHeight();

        frame.setBounds((x-width)/2, (y-height)/2, width, height);




        //文本域
        final JTextArea area=new JTextArea(20,50);
        frame.add(area,BorderLayout.CENTER);
        JScrollPane scroller=new JScrollPane(area);
        scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        frame.add(scroller,BorderLayout.CENTER);
        area.setWrapStyleWord(true);//设置单词在一行不足容纳时换行
        area.setLineWrap(true);//设置文本编辑区自动换行默认为true,即会"自动换行


        //创建右键弹出菜单
        final JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem popupMenu_Undo = new JMenuItem("撤销(U)");
        JMenuItem popupMenu_Cut = new JMenuItem("剪切(T)");
        JMenuItem popupMenu_Copy = new JMenuItem("复制(C)");
        JMenuItem popupMenu_Paste = new JMenuItem("粘帖(P)");
        JMenuItem popupMenu_Delete = new JMenuItem("删除(D)");
        JMenuItem popupMenu_SelectAll = new JMenuItem("全选(A)");

        popupMenu_Undo.setEnabled(false);

        //向右键菜单添加菜单项和分隔符
        popupMenu.add(popupMenu_Undo);
        popupMenu.addSeparator();
        popupMenu.add(popupMenu_Cut);
        popupMenu.add(popupMenu_Copy);
        popupMenu.add(popupMenu_Paste);
        popupMenu.add(popupMenu_Delete);
        popupMenu.addSeparator();
        popupMenu.add(popupMenu_SelectAll);





        popupMenu_Copy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                area.copy();
            }
        });

        popupMenu_Paste.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                area.paste();
            }
        });

        popupMenu_Cut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                area.cut();
            }
        });

        area.getDocument().addUndoableEditListener(undomg);
        popupMenu_Undo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(undomg.canUndo()) {
                    undomg.undo();
                } else {
                    JOptionPane.showMessageDialog(null,"无法撤销","警告",JOptionPane.WARNING_MESSAGE);
                }
            }
        });


        popupMenu_SelectAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                area.selectAll();
            }
        });
        menu2.addActionListener(new ActionListener() {


            public void actionPerformed(ActionEvent e) {
                FileDialog fileDialog = new FileDialog(frame, "打开", FileDialog.LOAD);
                fileDialog.setVisible(true);
                String path = fileDialog.getDirectory(); // 获取保存文件的路径
                String fileName = fileDialog.getFile(); //获取保存文件的名字
                File file = new File(path,fileName);

                try {
                    BufferedReader bufr=new BufferedReader(new FileReader(file));
                    String line=null;

                    while((line=bufr.readLine())!=null){

                        //将文本信息添加到文本区中。
                        area.append(line+"\r\n");}
                    //关闭读取流。
                    bufr.close();


                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });




        menu3.addActionListener(new ActionListener() {


            public void actionPerformed(ActionEvent e) {
                FileDialog fileDialog = new FileDialog(frame, "保存", FileDialog.SAVE);
                fileDialog.setVisible(true);
                String path = fileDialog.getDirectory(); // 获取保存文件的路径
                String fileName = fileDialog.getFile(); //获取保存文件的名字
                File file = new File(path,fileName);
                try {
                    //获取文本域的内容
                    String content = area.getText().replaceAll("\n","\r\n");

                    FileWriter fileWriter 	= new FileWriter(file);
                    fileWriter.write(content);
                    fileWriter.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });



        menu4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FileDialog fileDialog = new FileDialog(frame, "另存为", FileDialog.SAVE);
                fileDialog.setVisible(true);
                String path = fileDialog.getDirectory(); // 获取保存文件的路径
                String fileName = fileDialog.getFile(); //获取保存文件的名字
                File file = new File(path,fileName);
                try {
                    //获取文本域的内容
                    String content = area.getText().replaceAll("\n","\r\n");
                    FileWriter fileWriter 	= new FileWriter(file);
                    fileWriter.write(content);
                    fileWriter.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });






        area.getDocument().addUndoableEditListener(undomg);
        menu8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(undomg.canUndo()) {
                    undomg.undo();
                } else {
                    JOptionPane.showMessageDialog(null,"无法撤销","警告",JOptionPane.WARNING_MESSAGE);
                }
            }
        });





        menu10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                area.copy();
            }
        });

        menu11.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                area.paste();
            }
        });

        menu9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                area.cut();
            }
        });




        menu17.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                area.selectAll();
            }
        });




        menu18.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Calendar rightNow=Calendar.getInstance();
                Date date1=rightNow.getTime();
                area.insert(date1.toString(),area.getCaretPosition());
            }
        });

        area.setLineWrap(false);//设置文本编辑区自动换行默认为true,即会"自动换行
        menu19.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(area.getLineWrap()==false) {
                    area.setLineWrap(true);
                }else {
                    area.setLineWrap(false);
                }
            }
        });



        //文本编辑区注册右键菜单事件
        area.addMouseListener(new MouseAdapter()
        {	public void mousePressed(MouseEvent e)
        {	if(e.isPopupTrigger())//返回此鼠标事件是否为该平台的弹出菜单触发事件
        {	popupMenu.show(e.getComponent(),e.getX(),e.getY());//在组件调用者的坐标空间中的位置 X、Y 显示弹出菜单
        }
            checkMenuItemEnabled();//设置剪切，复制，粘帖，删除等功能的可用性
            area.requestFocus();//编辑区获取焦点
        }
            private void checkMenuItemEnabled() {
                // TODO Auto-generated method stub

            }
            public void mouseReleased(MouseEvent e)
            {	if(e.isPopupTrigger())//返回此鼠标事件是否为该平台的弹出菜单触发事件
            {	popupMenu.show(e.getComponent(),e.getX(),e.getY());//在组件调用者的坐标空间中的位置 X、Y 显示弹出菜单
            }
                checkMenuItemEnabled();//设置剪切，复制，粘帖，删除等功能的可用性
                area.requestFocus();//编辑区获取焦点
            }
        });//文本编辑区注册右键菜单事件结束





    }





    public static void main(String[] args) {
        init();

    }




}
