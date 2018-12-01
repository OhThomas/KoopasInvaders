package com.game.src.main.libs;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class Animation {

	private int speed;
	private int frames;
	private int index = 0;
	private int count = 0;
	
	private BufferedImage img1;
	private BufferedImage img2;
	private BufferedImage img3;
	private BufferedImage img4;
	private BufferedImage img5;
	private BufferedImage img6;
	private BufferedImage img7;
	private BufferedImage img8;
	private BufferedImage img9;
	private BufferedImage img10;
	private BufferedImage img11;
	private BufferedImage img12;
	private BufferedImage img13;
	private BufferedImage img14;
	private BufferedImage img15;
	private BufferedImage img16;
	private BufferedImage img17;
	private BufferedImage img18;
	private BufferedImage img19;
	private BufferedImage img20;
	private BufferedImage img21;
	private BufferedImage img22;
	private BufferedImage img23;
	private BufferedImage img24;
	private BufferedImage img25;
	private BufferedImage img26;
	private BufferedImage img27;
	private BufferedImage img28;
	private BufferedImage img29;
	private BufferedImage img30;
	
	private BufferedImage currentImg;
	//30 frame animation
	public Animation(int speed, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4, BufferedImage img5, BufferedImage img6, BufferedImage img7, BufferedImage img8, BufferedImage img9, BufferedImage img10, BufferedImage img11, BufferedImage img12, BufferedImage img13, BufferedImage img14, BufferedImage img15, BufferedImage img16, BufferedImage img17, BufferedImage img18, BufferedImage img19, BufferedImage img20, BufferedImage img21, BufferedImage img22, BufferedImage img23, BufferedImage img24, BufferedImage img25, BufferedImage img26, BufferedImage img27, BufferedImage img28, BufferedImage img29, BufferedImage img30){
		this.speed = speed;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		this.img4 = img4;
		this.img5 = img5;
		this.img6 = img6;
		this.img7 = img7;
		this.img8 = img8;
		this.img9 = img9;
		this.img10 = img10;
		this.img11 = img11;
		this.img12 = img12;
		this.img13 = img13;
		this.img14 = img14;
		this.img15 = img15;
		this.img16 = img16;
		this.img17 = img17;
		this.img18 = img18;
		this.img19 = img19;
		this.img20 = img20;
		this.img21 = img21;
		this.img22 = img22;
		this.img23 = img23;
		this.img24 = img24;
		this.img25 = img25;
		this.img26 = img26;
		this.img27 = img27;
		this.img28 = img28;
		this.img29 = img29;
		this.img30 = img30;
		frames = 30;
	}
	//29 frame animation
	public Animation(int speed, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4, BufferedImage img5, BufferedImage img6, BufferedImage img7, BufferedImage img8, BufferedImage img9, BufferedImage img10, BufferedImage img11, BufferedImage img12, BufferedImage img13, BufferedImage img14, BufferedImage img15, BufferedImage img16, BufferedImage img17, BufferedImage img18, BufferedImage img19, BufferedImage img20, BufferedImage img21, BufferedImage img22, BufferedImage img23, BufferedImage img24, BufferedImage img25, BufferedImage img26, BufferedImage img27, BufferedImage img28, BufferedImage img29){
		this.speed = speed;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		this.img4 = img4;
		this.img5 = img5;
		this.img6 = img6;
		this.img7 = img7;
		this.img8 = img8;
		this.img9 = img9;
		this.img10 = img10;
		this.img11 = img11;
		this.img12 = img12;
		this.img13 = img13;
		this.img14 = img14;
		this.img15 = img15;
		this.img16 = img16;
		this.img17 = img17;
		this.img18 = img18;
		this.img19 = img19;
		this.img20 = img20;
		this.img21 = img21;
		this.img22 = img22;
		this.img23 = img23;
		this.img24 = img24;
		this.img25 = img25;
		this.img26 = img26;
		this.img27 = img27;
		this.img28 = img28;
		this.img29 = img29;
		frames = 29;
	}
	//28 frame animation
	public Animation(int speed, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4, BufferedImage img5, BufferedImage img6, BufferedImage img7, BufferedImage img8, BufferedImage img9, BufferedImage img10, BufferedImage img11, BufferedImage img12, BufferedImage img13, BufferedImage img14, BufferedImage img15, BufferedImage img16, BufferedImage img17, BufferedImage img18, BufferedImage img19, BufferedImage img20, BufferedImage img21, BufferedImage img22, BufferedImage img23, BufferedImage img24, BufferedImage img25, BufferedImage img26, BufferedImage img27, BufferedImage img28){
		this.speed = speed;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		this.img4 = img4;
		this.img5 = img5;
		this.img6 = img6;
		this.img7 = img7;
		this.img8 = img8;
		this.img9 = img9;
		this.img10 = img10;
		this.img11 = img11;
		this.img12 = img12;
		this.img13 = img13;
		this.img14 = img14;
		this.img15 = img15;
		this.img16 = img16;
		this.img17 = img17;
		this.img18 = img18;
		this.img19 = img19;
		this.img20 = img20;
		this.img21 = img21;
		this.img22 = img22;
		this.img23 = img23;
		this.img24 = img24;
		this.img25 = img25;
		this.img26 = img26;
		this.img27 = img27;
		this.img28 = img28;
		frames = 28;
	}
	//27 frame animation
	public Animation(int speed, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4, BufferedImage img5, BufferedImage img6, BufferedImage img7, BufferedImage img8, BufferedImage img9, BufferedImage img10, BufferedImage img11, BufferedImage img12, BufferedImage img13, BufferedImage img14, BufferedImage img15, BufferedImage img16, BufferedImage img17, BufferedImage img18, BufferedImage img19, BufferedImage img20, BufferedImage img21, BufferedImage img22, BufferedImage img23, BufferedImage img24, BufferedImage img25, BufferedImage img26, BufferedImage img27){
		this.speed = speed;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		this.img4 = img4;
		this.img5 = img5;
		this.img6 = img6;
		this.img7 = img7;
		this.img8 = img8;
		this.img9 = img9;
		this.img10 = img10;
		this.img11 = img11;
		this.img12 = img12;
		this.img13 = img13;
		this.img14 = img14;
		this.img15 = img15;
		this.img16 = img16;
		this.img17 = img17;
		this.img18 = img18;
		this.img19 = img19;
		this.img20 = img20;
		this.img21 = img21;
		this.img22 = img22;
		this.img23 = img23;
		this.img24 = img24;
		this.img25 = img25;
		this.img26 = img26;
		this.img27 = img27;
		frames = 27;
	}
	//26 frame animation
	public Animation(int speed, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4, BufferedImage img5, BufferedImage img6, BufferedImage img7, BufferedImage img8, BufferedImage img9, BufferedImage img10, BufferedImage img11, BufferedImage img12, BufferedImage img13, BufferedImage img14, BufferedImage img15, BufferedImage img16, BufferedImage img17, BufferedImage img18, BufferedImage img19, BufferedImage img20, BufferedImage img21, BufferedImage img22, BufferedImage img23, BufferedImage img24, BufferedImage img25, BufferedImage img26){
		this.speed = speed;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		this.img4 = img4;
		this.img5 = img5;
		this.img6 = img6;
		this.img7 = img7;
		this.img8 = img8;
		this.img9 = img9;
		this.img10 = img10;
		this.img11 = img11;
		this.img12 = img12;
		this.img13 = img13;
		this.img14 = img14;
		this.img15 = img15;
		this.img16 = img16;
		this.img17 = img17;
		this.img18 = img18;
		this.img19 = img19;
		this.img20 = img20;
		this.img21 = img21;
		this.img22 = img22;
		this.img23 = img23;
		this.img24 = img24;
		this.img25 = img25;
		this.img26 = img26;
		frames = 26;
	}
	//25 frame animation
	public Animation(int speed, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4, BufferedImage img5, BufferedImage img6, BufferedImage img7, BufferedImage img8, BufferedImage img9, BufferedImage img10, BufferedImage img11, BufferedImage img12, BufferedImage img13, BufferedImage img14, BufferedImage img15, BufferedImage img16, BufferedImage img17, BufferedImage img18, BufferedImage img19, BufferedImage img20, BufferedImage img21, BufferedImage img22, BufferedImage img23, BufferedImage img24, BufferedImage img25){
		this.speed = speed;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		this.img4 = img4;
		this.img5 = img5;
		this.img6 = img6;
		this.img7 = img7;
		this.img8 = img8;
		this.img9 = img9;
		this.img10 = img10;
		this.img11 = img11;
		this.img12 = img12;
		this.img13 = img13;
		this.img14 = img14;
		this.img15 = img15;
		this.img16 = img16;
		this.img17 = img17;
		this.img18 = img18;
		this.img19 = img19;
		this.img20 = img20;
		this.img21 = img21;
		this.img22 = img22;
		this.img23 = img23;
		this.img24 = img24;
		this.img25 = img25;
		frames = 25;
	}
	//24 frame animation
	public Animation(int speed, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4, BufferedImage img5, BufferedImage img6, BufferedImage img7, BufferedImage img8, BufferedImage img9, BufferedImage img10, BufferedImage img11, BufferedImage img12, BufferedImage img13, BufferedImage img14, BufferedImage img15, BufferedImage img16, BufferedImage img17, BufferedImage img18, BufferedImage img19, BufferedImage img20, BufferedImage img21, BufferedImage img22, BufferedImage img23, BufferedImage img24){
		this.speed = speed;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		this.img4 = img4;
		this.img5 = img5;
		this.img6 = img6;
		this.img7 = img7;
		this.img8 = img8;
		this.img9 = img9;
		this.img10 = img10;
		this.img11 = img11;
		this.img12 = img12;
		this.img13 = img13;
		this.img14 = img14;
		this.img15 = img15;
		this.img16 = img16;
		this.img17 = img17;
		this.img18 = img18;
		this.img19 = img19;
		this.img20 = img20;
		this.img21 = img21;
		this.img22 = img22;
		this.img23 = img23;
		this.img24 = img24;
		frames = 24;
	}
	//23 frame animation
	public Animation(int speed, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4, BufferedImage img5, BufferedImage img6, BufferedImage img7, BufferedImage img8, BufferedImage img9, BufferedImage img10, BufferedImage img11, BufferedImage img12, BufferedImage img13, BufferedImage img14, BufferedImage img15, BufferedImage img16, BufferedImage img17, BufferedImage img18, BufferedImage img19, BufferedImage img20, BufferedImage img21, BufferedImage img22, BufferedImage img23){
		this.speed = speed;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		this.img4 = img4;
		this.img5 = img5;
		this.img6 = img6;
		this.img7 = img7;
		this.img8 = img8;
		this.img9 = img9;
		this.img10 = img10;
		this.img11 = img11;
		this.img12 = img12;
		this.img13 = img13;
		this.img14 = img14;
		this.img15 = img15;
		this.img16 = img16;
		this.img17 = img17;
		this.img18 = img18;
		this.img19 = img19;
		this.img20 = img20;
		this.img21 = img21;
		this.img22 = img22;
		this.img23 = img23;
		frames = 23;
	}
	//22 frame animation
	public Animation(int speed, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4, BufferedImage img5, BufferedImage img6, BufferedImage img7, BufferedImage img8, BufferedImage img9, BufferedImage img10, BufferedImage img11, BufferedImage img12, BufferedImage img13, BufferedImage img14, BufferedImage img15, BufferedImage img16, BufferedImage img17, BufferedImage img18, BufferedImage img19, BufferedImage img20, BufferedImage img21, BufferedImage img22){
		this.speed = speed;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		this.img4 = img4;
		this.img5 = img5;
		this.img6 = img6;
		this.img7 = img7;
		this.img8 = img8;
		this.img9 = img9;
		this.img10 = img10;
		this.img11 = img11;
		this.img12 = img12;
		this.img13 = img13;
		this.img14 = img14;
		this.img15 = img15;
		this.img16 = img16;
		this.img17 = img17;
		this.img18 = img18;
		this.img19 = img19;
		this.img20 = img20;
		this.img21 = img21;
		this.img22 = img22;
		frames = 22;
	}
	//21 frame animation
	public Animation(int speed, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4, BufferedImage img5, BufferedImage img6, BufferedImage img7, BufferedImage img8, BufferedImage img9, BufferedImage img10, BufferedImage img11, BufferedImage img12, BufferedImage img13, BufferedImage img14, BufferedImage img15, BufferedImage img16, BufferedImage img17, BufferedImage img18, BufferedImage img19, BufferedImage img20, BufferedImage img21){
		this.speed = speed;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		this.img4 = img4;
		this.img5 = img5;
		this.img6 = img6;
		this.img7 = img7;
		this.img8 = img8;
		this.img9 = img9;
		this.img10 = img10;
		this.img11 = img11;
		this.img12 = img12;
		this.img13 = img13;
		this.img14 = img14;
		this.img15 = img15;
		this.img16 = img16;
		this.img17 = img17;
		this.img18 = img18;
		this.img19 = img19;
		this.img20 = img20;
		this.img21 = img21;
		frames = 21;
	}
	//20 frame animation
	public Animation(int speed, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4, BufferedImage img5, BufferedImage img6, BufferedImage img7, BufferedImage img8, BufferedImage img9, BufferedImage img10, BufferedImage img11, BufferedImage img12, BufferedImage img13, BufferedImage img14, BufferedImage img15, BufferedImage img16, BufferedImage img17, BufferedImage img18, BufferedImage img19, BufferedImage img20){
		this.speed = speed;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		this.img4 = img4;
		this.img5 = img5;
		this.img6 = img6;
		this.img7 = img7;
		this.img8 = img8;
		this.img9 = img9;
		this.img10 = img10;
		this.img11 = img11;
		this.img12 = img12;
		this.img13 = img13;
		this.img14 = img14;
		this.img15 = img15;
		this.img16 = img16;
		this.img17 = img17;
		this.img18 = img18;
		this.img19 = img19;
		this.img20 = img20;
		frames = 20;
	}
	//19 frame animation
	public Animation(int speed, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4, BufferedImage img5, BufferedImage img6, BufferedImage img7, BufferedImage img8, BufferedImage img9, BufferedImage img10, BufferedImage img11, BufferedImage img12, BufferedImage img13, BufferedImage img14, BufferedImage img15, BufferedImage img16, BufferedImage img17, BufferedImage img18, BufferedImage img19){
		this.speed = speed;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		this.img4 = img4;
		this.img5 = img5;
		this.img6 = img6;
		this.img7 = img7;
		this.img8 = img8;
		this.img9 = img9;
		this.img10 = img10;
		this.img11 = img11;
		this.img12 = img12;
		this.img13 = img13;
		this.img14 = img14;
		this.img15 = img15;
		this.img16 = img16;
		this.img17 = img17;
		this.img18 = img18;
		this.img19 = img19;
		frames = 19;
	}
	//18 frame animation
	public Animation(int speed, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4, BufferedImage img5, BufferedImage img6, BufferedImage img7, BufferedImage img8, BufferedImage img9, BufferedImage img10, BufferedImage img11, BufferedImage img12, BufferedImage img13, BufferedImage img14, BufferedImage img15, BufferedImage img16, BufferedImage img17, BufferedImage img18){
		this.speed = speed;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		this.img4 = img4;
		this.img5 = img5;
		this.img6 = img6;
		this.img7 = img7;
		this.img8 = img8;
		this.img9 = img9;
		this.img10 = img10;
		this.img11 = img11;
		this.img12 = img12;
		this.img13 = img13;
		this.img14 = img14;
		this.img15 = img15;
		this.img16 = img16;
		this.img17 = img17;
		this.img18 = img18;
		frames = 18;
	}
	//17 frame animation
	public Animation(int speed, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4, BufferedImage img5, BufferedImage img6, BufferedImage img7, BufferedImage img8, BufferedImage img9, BufferedImage img10, BufferedImage img11, BufferedImage img12, BufferedImage img13, BufferedImage img14, BufferedImage img15, BufferedImage img16, BufferedImage img17){
		this.speed = speed;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		this.img4 = img4;
		this.img5 = img5;
		this.img6 = img6;
		this.img7 = img7;
		this.img8 = img8;
		this.img9 = img9;
		this.img10 = img10;
		this.img11 = img11;
		this.img12 = img12;
		this.img13 = img13;
		this.img14 = img14;
		this.img15 = img15;
		this.img16 = img16;
		this.img17 = img17;
		frames = 17;
	}
	//16 frame animation
	public Animation(int speed, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4, BufferedImage img5, BufferedImage img6, BufferedImage img7, BufferedImage img8, BufferedImage img9, BufferedImage img10, BufferedImage img11, BufferedImage img12, BufferedImage img13, BufferedImage img14, BufferedImage img15, BufferedImage img16){
		this.speed = speed;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		this.img4 = img4;
		this.img5 = img5;
		this.img6 = img6;
		this.img7 = img7;
		this.img8 = img8;
		this.img9 = img9;
		this.img10 = img10;
		this.img11 = img11;
		this.img12 = img12;
		this.img13 = img13;
		this.img14 = img14;
		this.img15 = img15;
		this.img16 = img16;
		frames = 16;
	}
	//15 frame animation
	public Animation(int speed, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4, BufferedImage img5, BufferedImage img6, BufferedImage img7, BufferedImage img8, BufferedImage img9, BufferedImage img10, BufferedImage img11, BufferedImage img12, BufferedImage img13, BufferedImage img14, BufferedImage img15){
		this.speed = speed;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		this.img4 = img4;
		this.img5 = img5;
		this.img6 = img6;
		this.img7 = img7;
		this.img8 = img8;
		this.img9 = img9;
		this.img10 = img10;
		this.img11 = img11;
		this.img12 = img12;
		this.img13 = img13;
		this.img14 = img14;
		this.img15 = img15;
		frames = 15;
	}
	//14 frame animation
	public Animation(int speed, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4, BufferedImage img5, BufferedImage img6, BufferedImage img7, BufferedImage img8, BufferedImage img9, BufferedImage img10, BufferedImage img11, BufferedImage img12, BufferedImage img13, BufferedImage img14){
		this.speed = speed;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		this.img4 = img4;
		this.img5 = img5;
		this.img6 = img6;
		this.img7 = img7;
		this.img8 = img8;
		this.img9 = img9;
		this.img10 = img10;
		this.img11 = img11;
		this.img12 = img12;
		this.img13 = img13;
		this.img14 = img14;
		frames = 14;
	}
	//13 frame animation
	public Animation(int speed, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4, BufferedImage img5, BufferedImage img6, BufferedImage img7, BufferedImage img8, BufferedImage img9, BufferedImage img10, BufferedImage img11, BufferedImage img12, BufferedImage img13){
		this.speed = speed;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		this.img4 = img4;
		this.img5 = img5;
		this.img6 = img6;
		this.img7 = img7;
		this.img8 = img8;
		this.img9 = img9;
		this.img10 = img10;
		this.img11 = img11;
		this.img12 = img12;
		this.img13 = img13;
		frames = 13;
		}
	//12 frame animation
	public Animation(int speed, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4, BufferedImage img5, BufferedImage img6, BufferedImage img7, BufferedImage img8, BufferedImage img9, BufferedImage img10, BufferedImage img11, BufferedImage img12){
		this.speed = speed;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		this.img4 = img4;
		this.img5 = img5;
		this.img6 = img6;
		this.img7 = img7;
		this.img8 = img8;
		this.img9 = img9;
		this.img10 = img10;
		this.img11 = img11;
		this.img12 = img12;
		frames = 12;
	}
	//11 frame animation
	public Animation(int speed, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4, BufferedImage img5, BufferedImage img6, BufferedImage img7, BufferedImage img8, BufferedImage img9, BufferedImage img10, BufferedImage img11){
		this.speed = speed;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		this.img4 = img4;
		this.img5 = img5;
		this.img6 = img6;
		this.img7 = img7;
		this.img8 = img8;
		this.img9 = img9;
		this.img10 = img10;
		this.img11 = img11;
		frames = 11;
	}
	//10 frame animation
	public Animation(int speed, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4, BufferedImage img5, BufferedImage img6, BufferedImage img7, BufferedImage img8, BufferedImage img9, BufferedImage img10){
		this.speed = speed;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		this.img4 = img4;
		this.img5 = img5;
		this.img6 = img6;
		this.img7 = img7;
		this.img8 = img8;
		this.img9 = img9;
		this.img10 = img10;
		frames = 10;
	}
	// 9 frame
	public Animation(int speed, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4, BufferedImage img5, BufferedImage img6, BufferedImage img7, BufferedImage img8, BufferedImage img9){
		this.speed = speed;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		this.img4 = img4;
		this.img5 = img5;
		this.img6 = img6;
		this.img7 = img7;
		this.img8 = img8;
		this.img9 = img9;
		frames = 9;
	}
	//8 frame
	public Animation(int speed, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4, BufferedImage img5, BufferedImage img6, BufferedImage img7, BufferedImage img8){
		this.speed = speed;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		this.img4 = img4;
		this.img5 = img5;
		this.img6 = img6;
		this.img7 = img7;
		this.img8 = img8;
		frames = 8;
	}
	//7 frame
	public Animation(int speed, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4, BufferedImage img5, BufferedImage img6, BufferedImage img7){
		this.speed = speed;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		this.img4 = img4;
		this.img5 = img5;
		this.img6 = img6;
		this.img7 = img7;
		frames = 7;
	}
	//6 frame
	public Animation(int speed, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4, BufferedImage img5, BufferedImage img6){
		this.speed = speed;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		this.img4 = img4;
		this.img5 = img5;
		this.img6 = img6;
		frames = 6;
	}
	//5 frame
	public Animation(int speed, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4, BufferedImage img5){
		this.speed = speed;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		this.img4 = img4;
		this.img5 = img5;
		frames = 5;
	}
	//4 frame
	public Animation(int speed, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4){
		this.speed = speed;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		this.img4 = img4;
		frames = 4;
	}
	//3 frame
	public Animation(int speed, BufferedImage img1, BufferedImage img2, BufferedImage img3){
		this.speed = speed;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		frames = 3;
	}
	//2 frame
	public Animation(int speed, BufferedImage img1, BufferedImage img2){
		this.speed = speed;
		this.img1 = img1;
		this.img2 = img2;
		frames = 2;
	}
	
	public void runAnimation(){
		index++;
		if(index > speed){
			index = 0;
			nextFrame();
		}	
	}
	
	public void nextFrame(){
		
		//switch statement
		switch(frames){
		case 2:
			if(count == 0)
				currentImg = img1;
			if(count == 1)
				currentImg = img2;

			count++;
			
			if(count > frames)
				count = 0;
			
			break;
		case 3:
			if(count == 0)
				currentImg = img1;
			if(count == 1)
				currentImg = img2;
			if(count == 2)
				currentImg = img3;
			
			count++;
			
			if(count > frames)
				count = 0;
			
			break;
		case 4:
			if(count == 0)
				currentImg = img1;
			if(count == 1)
				currentImg = img2;
			if(count == 2)
				currentImg = img3;
			if(count == 3)
				currentImg = img4;
			
			count++;
			
			if(count > frames)
				count = 0;
			
			break;
		case 5:
			if(count == 0)
				currentImg = img1;
			if(count == 1)
				currentImg = img2;
			if(count == 2)
				currentImg = img3;
			if(count == 3)
				currentImg = img4;
			if(count == 4)
				currentImg = img5;
			
			count++;
			
			if(count > frames)
				count = 0;
			
			break;
		case 6:
			if(count == 0)
				currentImg = img1;
			if(count == 1)
				currentImg = img2;
			if(count == 2)
				currentImg = img3;
			if(count == 3)
				currentImg = img4;
			if(count == 4)
				currentImg = img5;
			if(count == 5)
				currentImg = img6;
			
			count++;
			
			if(count > frames)
				count = 0;
			
			break;
		case 7:
			if(count == 0)
				currentImg = img1;
			if(count == 1)
				currentImg = img2;
			if(count == 2)
				currentImg = img3;
			if(count == 3)
				currentImg = img4;
			if(count == 4)
				currentImg = img5;
			if(count == 5)
				currentImg = img6;
			if(count == 6)
				currentImg = img7;
			
			count++;
			
			if(count > frames)
				count = 0;
			
			break;
		case 8:
			if(count == 0)
				currentImg = img1;
			if(count == 1)
				currentImg = img2;
			if(count == 2)
				currentImg = img3;
			if(count == 3)
				currentImg = img4;
			if(count == 4)
				currentImg = img5;
			if(count == 5)
				currentImg = img6;
			if(count == 6)
				currentImg = img7;
			if(count == 7)
				currentImg = img8;
			
			count++;
			
			if(count > frames)
				count = 0;
			
			break;
		case 9:
			if(count == 0)
				currentImg = img1;
			if(count == 1)
				currentImg = img2;
			if(count == 2)
				currentImg = img3;
			if(count == 3)
				currentImg = img4;
			if(count == 4)
				currentImg = img5;
			if(count == 5)
				currentImg = img6;
			if(count == 6)
				currentImg = img7;
			if(count == 7)
				currentImg = img8;
			if(count == 8)
				currentImg = img9;
			
			count++;
			
			if(count > frames)
				count = 0;
			
			break;	
		case 10:
			if(count == 0)
				currentImg = img1;
			if(count == 1)
				currentImg = img2;
			if(count == 2)
				currentImg = img3;
			if(count == 3)
				currentImg = img4;
			if(count == 4)
				currentImg = img5;
			if(count == 5)
				currentImg = img6;
			if(count == 6)
				currentImg = img7;
			if(count == 7)
				currentImg = img8;
			if(count == 8)
				currentImg = img9;
			if(count == 9)
				currentImg = img10;
			
			count++;
			
			if(count > frames)
				count = 0;
			
			break;
		case 11:
			if(count == 0)
				currentImg = img1;
			if(count == 1)
				currentImg = img2;
			if(count == 2)
				currentImg = img3;
			if(count == 3)
				currentImg = img4;
			if(count == 4)
				currentImg = img5;
			if(count == 5)
				currentImg = img6;
			if(count == 6)
				currentImg = img7;
			if(count == 7)
				currentImg = img8;
			if(count == 8)
				currentImg = img9;
			if(count == 9)
				currentImg = img10;
			if(count == 10)
				currentImg = img11;
			
			count++;
			
			if(count > frames)
				count = 0;
			
			break;
		case 12:
			if(count == 0)
				currentImg = img1;
			if(count == 1)
				currentImg = img2;
			if(count == 2)
				currentImg = img3;
			if(count == 3)
				currentImg = img4;
			if(count == 4)
				currentImg = img5;
			if(count == 5)
				currentImg = img6;
			if(count == 6)
				currentImg = img7;
			if(count == 7)
				currentImg = img8;
			if(count == 8)
				currentImg = img9;
			if(count == 9)
				currentImg = img10;
			if(count == 10)
				currentImg = img11;
			if(count == 11)
				currentImg = img12;
			
			count++;
			
			if(count > frames)
				count = 0;
			
			break;
		case 13:
			if(count == 0)
				currentImg = img1;
			if(count == 1)
				currentImg = img2;
			if(count == 2)
				currentImg = img3;
			if(count == 3)
				currentImg = img4;
			if(count == 4)
				currentImg = img5;
			if(count == 5)
				currentImg = img6;
			if(count == 6)
				currentImg = img7;
			if(count == 7)
				currentImg = img8;
			if(count == 8)
				currentImg = img9;
			if(count == 9)
				currentImg = img10;
			if(count == 10)
				currentImg = img11;
			if(count == 11)
				currentImg = img12;
			if(count == 12)
				currentImg = img13;
			
			count++;
			
			if(count > frames)
				count = 0;
			
			break;
		case 14:
			if(count == 0)
				currentImg = img1;
			if(count == 1)
				currentImg = img2;
			if(count == 2)
				currentImg = img3;
			if(count == 3)
				currentImg = img4;
			if(count == 4)
				currentImg = img5;
			if(count == 5)
				currentImg = img6;
			if(count == 6)
				currentImg = img7;
			if(count == 7)
				currentImg = img8;
			if(count == 8)
				currentImg = img9;
			if(count == 9)
				currentImg = img10;
			if(count == 10)
				currentImg = img11;
			if(count == 11)
				currentImg = img12;
			if(count == 12)
				currentImg = img13;
			if(count == 13)
				currentImg = img14;
			
			count++;
			
			if(count > frames)
				count = 0;
			
			break;
		case 15:
			if(count == 0)
				currentImg = img1;
			if(count == 1)
				currentImg = img2;
			if(count == 2)
				currentImg = img3;
			if(count == 3)
				currentImg = img4;
			if(count == 4)
				currentImg = img5;
			if(count == 5)
				currentImg = img6;
			if(count == 6)
				currentImg = img7;
			if(count == 7)
				currentImg = img8;
			if(count == 8)
				currentImg = img9;
			if(count == 9)
				currentImg = img10;
			if(count == 10)
				currentImg = img11;
			if(count == 11)
				currentImg = img12;
			if(count == 12)
				currentImg = img13;
			if(count == 13)
				currentImg = img14;
			if(count == 14)
				currentImg = img15;
			
			count++;
			
			if(count > frames)
				count = 0;
			
			break;
		case 16:
			if(count == 0)
				currentImg = img1;
			if(count == 1)
				currentImg = img2;
			if(count == 2)
				currentImg = img3;
			if(count == 3)
				currentImg = img4;
			if(count == 4)
				currentImg = img5;
			if(count == 5)
				currentImg = img6;
			if(count == 6)
				currentImg = img7;
			if(count == 7)
				currentImg = img8;
			if(count == 8)
				currentImg = img9;
			if(count == 9)
				currentImg = img10;
			if(count == 10)
				currentImg = img11;
			if(count == 11)
				currentImg = img12;
			if(count == 12)
				currentImg = img13;
			if(count == 13)
				currentImg = img14;
			if(count == 14)
				currentImg = img15;
			if(count == 15)
				currentImg = img16;
			
			count++;
			
			if(count > frames)
				count = 0;
			
			break;
		case 17:
			if(count == 0)
				currentImg = img1;
			if(count == 1)
				currentImg = img2;
			if(count == 2)
				currentImg = img3;
			if(count == 3)
				currentImg = img4;
			if(count == 4)
				currentImg = img5;
			if(count == 5)
				currentImg = img6;
			if(count == 6)
				currentImg = img7;
			if(count == 7)
				currentImg = img8;
			if(count == 8)
				currentImg = img9;
			if(count == 9)
				currentImg = img10;
			if(count == 10)
				currentImg = img11;
			if(count == 11)
				currentImg = img12;
			if(count == 12)
				currentImg = img13;
			if(count == 13)
				currentImg = img14;
			if(count == 14)
				currentImg = img15;
			if(count == 15)
				currentImg = img16;
			if(count == 16)
				currentImg = img17;
			
			count++;
			
			if(count > frames)
				count = 0;
			
			break;
		case 18:
			if(count == 0)
				currentImg = img1;
			if(count == 1)
				currentImg = img2;
			if(count == 2)
				currentImg = img3;
			if(count == 3)
				currentImg = img4;
			if(count == 4)
				currentImg = img5;
			if(count == 5)
				currentImg = img6;
			if(count == 6)
				currentImg = img7;
			if(count == 7)
				currentImg = img8;
			if(count == 8)
				currentImg = img9;
			if(count == 9)
				currentImg = img10;
			if(count == 10)
				currentImg = img11;
			if(count == 11)
				currentImg = img12;
			if(count == 12)
				currentImg = img13;
			if(count == 13)
				currentImg = img14;
			if(count == 14)
				currentImg = img15;
			if(count == 15)
				currentImg = img16;
			if(count == 16)
				currentImg = img17;
			if(count == 17)
				currentImg = img18;
			
			count++;
			
			if(count > frames)
				count = 0;
			
			break;
		case 19:
			if(count == 0)
				currentImg = img1;
			if(count == 1)
				currentImg = img2;
			if(count == 2)
				currentImg = img3;
			if(count == 3)
				currentImg = img4;
			if(count == 4)
				currentImg = img5;
			if(count == 5)
				currentImg = img6;
			if(count == 6)
				currentImg = img7;
			if(count == 7)
				currentImg = img8;
			if(count == 8)
				currentImg = img9;
			if(count == 9)
				currentImg = img10;
			if(count == 10)
				currentImg = img11;
			if(count == 11)
				currentImg = img12;
			if(count == 12)
				currentImg = img13;
			if(count == 13)
				currentImg = img14;
			if(count == 14)
				currentImg = img15;
			if(count == 15)
				currentImg = img16;
			if(count == 16)
				currentImg = img17;
			if(count == 17)
				currentImg = img18;
			if(count == 18)
				currentImg = img19;
			
			count++;
			
			if(count > frames)
				count = 0;
			
			break;
		case 20:
			if(count == 0)
				currentImg = img1;
			if(count == 1)
				currentImg = img2;
			if(count == 2)
				currentImg = img3;
			if(count == 3)
				currentImg = img4;
			if(count == 4)
				currentImg = img5;
			if(count == 5)
				currentImg = img6;
			if(count == 6)
				currentImg = img7;
			if(count == 7)
				currentImg = img8;
			if(count == 8)
				currentImg = img9;
			if(count == 9)
				currentImg = img10;
			if(count == 10)
				currentImg = img11;
			if(count == 11)
				currentImg = img12;
			if(count == 12)
				currentImg = img13;
			if(count == 13)
				currentImg = img14;
			if(count == 14)
				currentImg = img15;
			if(count == 15)
				currentImg = img16;
			if(count == 16)
				currentImg = img17;
			if(count == 17)
				currentImg = img18;
			if(count == 18)
				currentImg = img19;
			if(count == 19)
				currentImg = img20;
			
			count++;
			
			if(count > frames)
				count = 0;
			
			break;
		case 21:
			if(count == 0)
				currentImg = img1;
			if(count == 1)
				currentImg = img2;
			if(count == 2)
				currentImg = img3;
			if(count == 3)
				currentImg = img4;
			if(count == 4)
				currentImg = img5;
			if(count == 5)
				currentImg = img6;
			if(count == 6)
				currentImg = img7;
			if(count == 7)
				currentImg = img8;
			if(count == 8)
				currentImg = img9;
			if(count == 9)
				currentImg = img10;
			if(count == 10)
				currentImg = img11;
			if(count == 11)
				currentImg = img12;
			if(count == 12)
				currentImg = img13;
			if(count == 13)
				currentImg = img14;
			if(count == 14)
				currentImg = img15;
			if(count == 15)
				currentImg = img16;
			if(count == 16)
				currentImg = img17;
			if(count == 17)
				currentImg = img18;
			if(count == 18)
				currentImg = img19;
			if(count == 19)
				currentImg = img20;
			if(count == 20)
				currentImg = img21;
			
			count++;
			
			if(count > frames)
				count = 0;
			
			break;
		case 22:
			if(count == 0)
				currentImg = img1;
			if(count == 1)
				currentImg = img2;
			if(count == 2)
				currentImg = img3;
			if(count == 3)
				currentImg = img4;
			if(count == 4)
				currentImg = img5;
			if(count == 5)
				currentImg = img6;
			if(count == 6)
				currentImg = img7;
			if(count == 7)
				currentImg = img8;
			if(count == 8)
				currentImg = img9;
			if(count == 9)
				currentImg = img10;
			if(count == 10)
				currentImg = img11;
			if(count == 11)
				currentImg = img12;
			if(count == 12)
				currentImg = img13;
			if(count == 13)
				currentImg = img14;
			if(count == 14)
				currentImg = img15;
			if(count == 15)
				currentImg = img16;
			if(count == 16)
				currentImg = img17;
			if(count == 17)
				currentImg = img18;
			if(count == 18)
				currentImg = img19;
			if(count == 19)
				currentImg = img20;
			if(count == 20)
				currentImg = img21;
			if(count == 21)
				currentImg = img22;
			
			count++;
			
			if(count > frames)
				count = 0;
			
			break;
		case 23:
			if(count == 0)
				currentImg = img1;
			if(count == 1)
				currentImg = img2;
			if(count == 2)
				currentImg = img3;
			if(count == 3)
				currentImg = img4;
			if(count == 4)
				currentImg = img5;
			if(count == 5)
				currentImg = img6;
			if(count == 6)
				currentImg = img7;
			if(count == 7)
				currentImg = img8;
			if(count == 8)
				currentImg = img9;
			if(count == 9)
				currentImg = img10;
			if(count == 10)
				currentImg = img11;
			if(count == 11)
				currentImg = img12;
			if(count == 12)
				currentImg = img13;
			if(count == 13)
				currentImg = img14;
			if(count == 14)
				currentImg = img15;
			if(count == 15)
				currentImg = img16;
			if(count == 16)
				currentImg = img17;
			if(count == 17)
				currentImg = img18;
			if(count == 18)
				currentImg = img19;
			if(count == 19)
				currentImg = img20;
			if(count == 20)
				currentImg = img21;
			if(count == 21)
				currentImg = img22;
			if(count == 22)
				currentImg = img23;
			
			count++;
			
			if(count > frames)
				count = 0;
			
			break;
		case 24:
			if(count == 0)
				currentImg = img1;
			if(count == 1)
				currentImg = img2;
			if(count == 2)
				currentImg = img3;
			if(count == 3)
				currentImg = img4;
			if(count == 4)
				currentImg = img5;
			if(count == 5)
				currentImg = img6;
			if(count == 6)
				currentImg = img7;
			if(count == 7)
				currentImg = img8;
			if(count == 8)
				currentImg = img9;
			if(count == 9)
				currentImg = img10;
			if(count == 10)
				currentImg = img11;
			if(count == 11)
				currentImg = img12;
			if(count == 12)
				currentImg = img13;
			if(count == 13)
				currentImg = img14;
			if(count == 14)
				currentImg = img15;
			if(count == 15)
				currentImg = img16;
			if(count == 16)
				currentImg = img17;
			if(count == 17)
				currentImg = img18;
			if(count == 18)
				currentImg = img19;
			if(count == 19)
				currentImg = img20;
			if(count == 20)
				currentImg = img21;
			if(count == 21)
				currentImg = img22;
			if(count == 22)
				currentImg = img23;
			if(count == 23)
				currentImg = img24;
			
			count++;
			
			if(count > frames)
				count = 0;
			
			break;
		case 25:
			if(count == 0)
				currentImg = img1;
			if(count == 1)
				currentImg = img2;
			if(count == 2)
				currentImg = img3;
			if(count == 3)
				currentImg = img4;
			if(count == 4)
				currentImg = img5;
			if(count == 5)
				currentImg = img6;
			if(count == 6)
				currentImg = img7;
			if(count == 7)
				currentImg = img8;
			if(count == 8)
				currentImg = img9;
			if(count == 9)
				currentImg = img10;
			if(count == 10)
				currentImg = img11;
			if(count == 11)
				currentImg = img12;
			if(count == 12)
				currentImg = img13;
			if(count == 13)
				currentImg = img14;
			if(count == 14)
				currentImg = img15;
			if(count == 15)
				currentImg = img16;
			if(count == 16)
				currentImg = img17;
			if(count == 17)
				currentImg = img18;
			if(count == 18)
				currentImg = img19;
			if(count == 19)
				currentImg = img20;
			if(count == 20)
				currentImg = img21;
			if(count == 21)
				currentImg = img22;
			if(count == 22)
				currentImg = img23;
			if(count == 23)
				currentImg = img24;
			if(count == 24)
				currentImg = img25;
			
			count++;
			
			if(count > frames)
				count = 0;
			
			break;
		case 26:
			if(count == 0)
				currentImg = img1;
			if(count == 1)
				currentImg = img2;
			if(count == 2)
				currentImg = img3;
			if(count == 3)
				currentImg = img4;
			if(count == 4)
				currentImg = img5;
			if(count == 5)
				currentImg = img6;
			if(count == 6)
				currentImg = img7;
			if(count == 7)
				currentImg = img8;
			if(count == 8)
				currentImg = img9;
			if(count == 9)
				currentImg = img10;
			if(count == 10)
				currentImg = img11;
			if(count == 11)
				currentImg = img12;
			if(count == 12)
				currentImg = img13;
			if(count == 13)
				currentImg = img14;
			if(count == 14)
				currentImg = img15;
			if(count == 15)
				currentImg = img16;
			if(count == 16)
				currentImg = img17;
			if(count == 17)
				currentImg = img18;
			if(count == 18)
				currentImg = img19;
			if(count == 19)
				currentImg = img20;
			if(count == 20)
				currentImg = img21;
			if(count == 21)
				currentImg = img22;
			if(count == 22)
				currentImg = img23;
			if(count == 23)
				currentImg = img24;
			if(count == 24)
				currentImg = img25;
			if(count == 25)
				currentImg = img26;
			
			count++;
			
			if(count > frames)
				count = 0;
			
			break;
		case 27:
			if(count == 0)
				currentImg = img1;
			if(count == 1)
				currentImg = img2;
			if(count == 2)
				currentImg = img3;
			if(count == 3)
				currentImg = img4;
			if(count == 4)
				currentImg = img5;
			if(count == 5)
				currentImg = img6;
			if(count == 6)
				currentImg = img7;
			if(count == 7)
				currentImg = img8;
			if(count == 8)
				currentImg = img9;
			if(count == 9)
				currentImg = img10;
			if(count == 10)
				currentImg = img11;
			if(count == 11)
				currentImg = img12;
			if(count == 12)
				currentImg = img13;
			if(count == 13)
				currentImg = img14;
			if(count == 14)
				currentImg = img15;
			if(count == 15)
				currentImg = img16;
			if(count == 16)
				currentImg = img17;
			if(count == 17)
				currentImg = img18;
			if(count == 18)
				currentImg = img19;
			if(count == 19)
				currentImg = img20;
			if(count == 20)
				currentImg = img21;
			if(count == 21)
				currentImg = img22;
			if(count == 22)
				currentImg = img23;
			if(count == 23)
				currentImg = img24;
			if(count == 24)
				currentImg = img25;
			if(count == 25)
				currentImg = img26;
			if(count == 26)
				currentImg = img27;
			
			count++;
			
			if(count > frames)
				count = 0;
			
			break;
		case 28:
			if(count == 0)
				currentImg = img1;
			if(count == 1)
				currentImg = img2;
			if(count == 2)
				currentImg = img3;
			if(count == 3)
				currentImg = img4;
			if(count == 4)
				currentImg = img5;
			if(count == 5)
				currentImg = img6;
			if(count == 6)
				currentImg = img7;
			if(count == 7)
				currentImg = img8;
			if(count == 8)
				currentImg = img9;
			if(count == 9)
				currentImg = img10;
			if(count == 10)
				currentImg = img11;
			if(count == 11)
				currentImg = img12;
			if(count == 12)
				currentImg = img13;
			if(count == 13)
				currentImg = img14;
			if(count == 14)
				currentImg = img15;
			if(count == 15)
				currentImg = img16;
			if(count == 16)
				currentImg = img17;
			if(count == 17)
				currentImg = img18;
			if(count == 18)
				currentImg = img19;
			if(count == 19)
				currentImg = img20;
			if(count == 20)
				currentImg = img21;
			if(count == 21)
				currentImg = img22;
			if(count == 22)
				currentImg = img23;
			if(count == 23)
				currentImg = img24;
			if(count == 24)
				currentImg = img25;
			if(count == 25)
				currentImg = img26;
			if(count == 26)
				currentImg = img27;
			if(count == 27)
				currentImg = img28;
			
			count++;
			
			if(count > frames)
				count = 0;
			
			break;
		case 29:
			if(count == 0)
				currentImg = img1;
			if(count == 1)
				currentImg = img2;
			if(count == 2)
				currentImg = img3;
			if(count == 3)
				currentImg = img4;
			if(count == 4)
				currentImg = img5;
			if(count == 5)
				currentImg = img6;
			if(count == 6)
				currentImg = img7;
			if(count == 7)
				currentImg = img8;
			if(count == 8)
				currentImg = img9;
			if(count == 9)
				currentImg = img10;
			if(count == 10)
				currentImg = img11;
			if(count == 11)
				currentImg = img12;
			if(count == 12)
				currentImg = img13;
			if(count == 13)
				currentImg = img14;
			if(count == 14)
				currentImg = img15;
			if(count == 15)
				currentImg = img16;
			if(count == 16)
				currentImg = img17;
			if(count == 17)
				currentImg = img18;
			if(count == 18)
				currentImg = img19;
			if(count == 19)
				currentImg = img20;
			if(count == 20)
				currentImg = img21;
			if(count == 21)
				currentImg = img22;
			if(count == 22)
				currentImg = img23;
			if(count == 23)
				currentImg = img24;
			if(count == 24)
				currentImg = img25;
			if(count == 25)
				currentImg = img26;
			if(count == 26)
				currentImg = img27;
			if(count == 27)
				currentImg = img28;
			if(count == 28)
				currentImg = img29;
			
			count++;
			
			if(count > frames)
				count = 0;
			
			break;
		case 30:
			if(count == 0)
				currentImg = img1;
			if(count == 1)
				currentImg = img2;
			if(count == 2)
				currentImg = img3;
			if(count == 3)
				currentImg = img4;
			if(count == 4)
				currentImg = img5;
			if(count == 5)
				currentImg = img6;
			if(count == 6)
				currentImg = img7;
			if(count == 7)
				currentImg = img8;
			if(count == 8)
				currentImg = img9;
			if(count == 9)
				currentImg = img10;
			if(count == 10)
				currentImg = img11;
			if(count == 11)
				currentImg = img12;
			if(count == 12)
				currentImg = img13;
			if(count == 13)
				currentImg = img14;
			if(count == 14)
				currentImg = img15;
			if(count == 15)
				currentImg = img16;
			if(count == 16)
				currentImg = img17;
			if(count == 17)
				currentImg = img18;
			if(count == 18)
				currentImg = img19;
			if(count == 19)
				currentImg = img20;
			if(count == 20)
				currentImg = img21;
			if(count == 21)
				currentImg = img22;
			if(count == 22)
				currentImg = img23;
			if(count == 23)
				currentImg = img24;
			if(count == 24)
				currentImg = img25;
			if(count == 25)
				currentImg = img26;
			if(count == 26)
				currentImg = img27;
			if(count == 27)
				currentImg = img28;
			if(count == 28)
				currentImg = img29;
			if(count == 29)
				currentImg = img30;
			
			count++;
			
			if(count > frames)
				count = 0;
			
			break;
		}
	}
	
	public void drawAnimation(Graphics g, double x, double y, int offset){
		g.drawImage(currentImg, (int)x - offset, (int)y, null);
	}
	
	 public BufferedImage rotateImage(BufferedImage img, double angle) {

         double rads = Math.toRadians(angle);
         double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
         int w = img.getWidth();
         int h = img.getHeight();
         int newWidth = (int) Math.floor(w * cos + h * sin);
         int newHeight = (int) Math.floor(h * cos + w * sin);

         BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
         Graphics2D g2d = rotated.createGraphics();
         AffineTransform at = new AffineTransform();
         at.translate((newWidth - w) / 2, (newHeight - h) / 2);

         int x = w / 2;
         int y = h / 2;

         at.rotate(rads, x, y);
         g2d.setTransform(at);
         g2d.drawImage(img, 0, 0, null);
         g2d.dispose();

         return rotated;
     }
	public BufferedImage getCurrentImage() {
		return currentImg;
	}
	public void setCurrentImage(BufferedImage img) {
		currentImg = img;
	}
	public void setCount(int count){
		this.count = count;
	}
	public int getCount(){
		return count;
	}
	public int getSpeed(){
		return speed;
	}
	public void setSpeed(int speed){
		this.speed = speed;
	}
	
}