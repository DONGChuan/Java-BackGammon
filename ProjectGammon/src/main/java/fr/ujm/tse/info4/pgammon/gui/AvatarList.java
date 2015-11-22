package fr.ujm.tse.info4.pgammon.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class AvatarList extends JPanel{
	private static final long serialVersionUID = 4047846163448859090L;
	private OpaqueBG bg;
	private String path = "";
	private MonochromeButton ajouter;
	
	public AvatarList() {
		build();
	}

	private void build() {
		setPreferredSize(new Dimension(450,350));
		setOpaque(false);
		setLayout(null);
		
		JPanel pane = new JPanel();
		pane.setLayout(new GridLayout(0,3,10,10));
		pane.setOpaque(false);
		
		JScrollPane scroll = new JScrollPane(pane);
		scroll.setBounds(0,0,getPreferredSize().width,getPreferredSize().height - 50);
		scroll.getViewport().setBackground(new Color(0x000000));
		scroll.setBorder(null);
		scroll.setBounds(200,100,450,276);
		
		for(Avatar avatar : Avatar.values()){
			ImageAvatar img = new ImageAvatar(avatar,true);
			img.setPreferredSize(new Dimension(128,128));
			pane.add(img);
			img.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					path = ((ImageAvatar) e.getSource()).getPath();
					//System.out.println(path);
				}
			});
		}
		
		MonochromeButton closeBtn = new MonochromeButton("Cancel");
		closeBtn.setBounds(450, 400, 200, 40);
		closeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		ajouter = new MonochromeButton("Add");
		ajouter.setBounds(200, 400, 200, 40);
		
		bg = new OpaqueBG();
		bg.setBounds(getBounds());

		add(closeBtn);
		add(ajouter);
		add(scroll);
		add(bg);
	}
	
	public void close(){
		setVisible(false);
	}
	
	@Override
	public void setPreferredSize(Dimension preferredSize) {
		super.setPreferredSize(preferredSize);
	}
	
	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		bg.setBounds(0,0, width, height);
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public MonochromeButton getAjouter() {
		return ajouter;
	}

	public void setAjouter(MonochromeButton ajouter) {
		this.ajouter = ajouter;
	}
}
