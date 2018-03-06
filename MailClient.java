package com.mail.client;
import javax.mail.Address;
import javax.mail.internet.InternetAddress;
import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class MailClient
{
	JFrame frame;
	JLabel l1,labelfrom,labelTo,labelCC,labelTitle;
	JTextField from,to,cc,attachments,subject;
	JTextArea msgbox;
	JButton send,save,attach,canc;
	JFileChooser filechoose;
	String fromUser="captanhaneef@gmail.com";
	String[] toVar = null;
	String[] fileN=null;
	void mailClient()
	{
		frame=new JFrame("Mail");
		frame.getContentPane().setBackground(Color.lightGray);
		l1=new JLabel();
		labelfrom=new JLabel("From");
		labelfrom.setBounds(10,10,120,20);
		from=new JTextField(fromUser);
		from.setEditable(false);
		from.setBounds(70, 10, 300, 20);
		labelTo=new JLabel("To");
		labelTo.setBounds(10,30,120,20);
		to=new JTextField();
		to.setBounds(70, 30, 300, 20);
		labelCC=new JLabel("Subject");
		labelCC.setBounds(10,50,120,20);
		subject=new JTextField();
		subject.setBounds(70, 50, 300, 20);
		msgbox=new JTextArea();
		msgbox.setBounds(20, 100, 350, 200);
		attachments=new JTextField();
		attachments.setBounds(80, 80, 250, 20);
		send=new JButton("Send");
		send.setBounds(20, 290, 80, 30);
		send.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent ae)
					{
						List<String> lst=new ArrayList();
						
						String rec=to.getText();
						if(rec.contains(";"))
						{
							
								toVar=rec.split(";");
								
								for(int i=0;i<toVar.length;i++)
								{
									lst.add(toVar[i]);	
								}
								
						}
					
						String[] mailStr=(String[]) lst.toArray(new String[lst.size()]);
						
						String subVar=subject.getText();
						String msgVar=msgbox.getText();
						Provider pp=new Provider();
						pp.provider(mailStr, subVar, msgVar);
					}
				}
				);
		save=new JButton("Save");
		save.setBounds(110, 290, 80, 30);
		save.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent acteve)
					{
						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					}
				});
		canc=new JButton("Cancel");
		canc.setBounds(200, 290, 80, 30);
		attach=new JButton("@");
		attach.setBounds(20, 80, 50, 20);
		attach.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent actionE)
					{
						filechoose=new JFileChooser();
						int i=filechoose.showOpenDialog(filechoose);
						if(i==filechoose.APPROVE_OPTION)
						{
							File file=filechoose.getSelectedFile();
							try
							{
								FileReader fr=new FileReader(file);
								BufferedReader br=new BufferedReader(fr);
								String[] arr=new String[10];
								String dummy=";";
								String att=file.getName();
								List<String> lstarr=new ArrayList();
									att+=dummy;
									
									attachments.setText(att);
									/*String ntext=attachments.getText();
									attachments.setText(ntext+=att);*/
							}
							catch (FileNotFoundException e) 
							{
								
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
			}
					);
		frame.add(canc);
		frame.add(attach);
		frame.add(save);
		frame.add(send);
		frame.add(msgbox);
		frame.add(labelCC);
		frame.add(subject);
		frame.add(labelTo);
		frame.add(to);
		frame.add(from);
		frame.add(labelfrom);
		frame.add(attachments);
		frame.add(l1);
		frame.setSize(400, 400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
	public static void main(String[] args) {
		MailClient mc=new MailClient();
		mc.mailClient();
	}
}
