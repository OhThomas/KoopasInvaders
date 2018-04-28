/*
 * 
 */
package com.game.src.main.libs;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

// TODO: Auto-generated Javadoc
/**
 * The Class Animation.
 */
public class Animation {

	/** The speed. */
	private int speed;
	
	/** The frames. */
	private int frames;
	
	/** The index. */
	private int index = 0;
	
	/** The count. */
	private int count = 0;
	
	/** The img 1. */
	private BufferedImage img1;
	
	/** The img 2. */
	private BufferedImage img2;
	
	/** The img 3. */
	private BufferedImage img3;
	
	/** The img 4. */
	private BufferedImage img4;
	
	/** The img 5. */
	private BufferedImage img5;
	
	/** The img 6. */
	private BufferedImage img6;
	
	/** The img 7. */
	private BufferedImage img7;
	
	/** The img 8. */
	private BufferedImage img8;
	
	/** The img 9. */
	private BufferedImage img9;
	
	/** The img 10. */
	private BufferedImage img10;
	
	/** The img 11. */
	private BufferedImage img11;
	
	/** The img 12. */
	private BufferedImage img12;
	
	/** The img 13. */
	private BufferedImage img13;
	
	/** The img 14. */
	private BufferedImage img14;
	
	/** The img 15. */
	private BufferedImage img15;
	
	/** The img 16. */
	private BufferedImage img16;
	
	/** The img 17. */
	private BufferedImage img17;
	
	/** The img 18. */
	private BufferedImage img18;
	
	/** The img 19. */
	private BufferedImage img19;
	
	/** The img 20. */
	private BufferedImage img20;
	
	/** The img 21. */
	private BufferedImage img21;
	
	/** The img 22. */
	private BufferedImage img22;
	
	/** The img 23. */
	private BufferedImage img23;
	
	/** The img 24. */
	private BufferedImage img24;
	
	/** The img 25. */
	private BufferedImage img25;
	
	/** The img 26. */
	private BufferedImage img26;
	
	/** The img 27. */
	private BufferedImage img27;
	
	/** The img 28. */
	private BufferedImage img28;
	
	/** The img 29. */
	private BufferedImage img29;
	
	/** The img 30. */
	private BufferedImage img30;
	
	/** The current img. */
	private BufferedImage currentImg;
	
	/**
	 * Instantiates a new animation.
	 *
	 * @param speed the speed
	 * @param img1 the img 1
	 * @param img2 the img 2
	 * @param img3 the img 3
	 * @param img4 the img 4
	 * @param img5 the img 5
	 * @param img6 the img 6
	 * @param img7 the img 7
	 * @param img8 the img 8
	 * @param img9 the img 9
	 * @param img10 the img 10
	 * @param img11 the img 11
	 * @param img12 the img 12
	 * @param img13 the img 13
	 * @param img14 the img 14
	 * @param img15 the img 15
	 * @param img16 the img 16
	 * @param img17 the img 17
	 * @param img18 the img 18
	 * @param img19 the img 19
	 * @param img20 the img 20
	 * @param img21 the img 21
	 * @param img22 the img 22
	 * @param img23 the img 23
	 * @param img24 the img 24
	 * @param img25 the img 25
	 * @param img26 the img 26
	 * @param img27 the img 27
	 * @param img28 the img 28
	 * @param img29 the img 29
	 * @param img30 the img 30
	 */
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
	
	/**
	 * Instantiates a new animation.
	 *
	 * @param speed the speed
	 * @param img1 the img 1
	 * @param img2 the img 2
	 * @param img3 the img 3
	 * @param img4 the img 4
	 * @param img5 the img 5
	 * @param img6 the img 6
	 * @param img7 the img 7
	 * @param img8 the img 8
	 * @param img9 the img 9
	 * @param img10 the img 10
	 * @param img11 the img 11
	 * @param img12 the img 12
	 * @param img13 the img 13
	 * @param img14 the img 14
	 * @param img15 the img 15
	 * @param img16 the img 16
	 * @param img17 the img 17
	 * @param img18 the img 18
	 * @param img19 the img 19
	 * @param img20 the img 20
	 * @param img21 the img 21
	 * @param img22 the img 22
	 * @param img23 the img 23
	 * @param img24 the img 24
	 * @param img25 the img 25
	 * @param img26 the img 26
	 * @param img27 the img 27
	 * @param img28 the img 28
	 * @param img29 the img 29
	 */
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
	
	/**
	 * Instantiates a new animation.
	 *
	 * @param speed the speed
	 * @param img1 the img 1
	 * @param img2 the img 2
	 * @param img3 the img 3
	 * @param img4 the img 4
	 * @param img5 the img 5
	 * @param img6 the img 6
	 * @param img7 the img 7
	 * @param img8 the img 8
	 * @param img9 the img 9
	 * @param img10 the img 10
	 * @param img11 the img 11
	 * @param img12 the img 12
	 * @param img13 the img 13
	 * @param img14 the img 14
	 * @param img15 the img 15
	 * @param img16 the img 16
	 * @param img17 the img 17
	 * @param img18 the img 18
	 * @param img19 the img 19
	 * @param img20 the img 20
	 * @param img21 the img 21
	 * @param img22 the img 22
	 * @param img23 the img 23
	 * @param img24 the img 24
	 * @param img25 the img 25
	 * @param img26 the img 26
	 * @param img27 the img 27
	 * @param img28 the img 28
	 */
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
	
	/**
	 * Instantiates a new animation.
	 *
	 * @param speed the speed
	 * @param img1 the img 1
	 * @param img2 the img 2
	 * @param img3 the img 3
	 * @param img4 the img 4
	 * @param img5 the img 5
	 * @param img6 the img 6
	 * @param img7 the img 7
	 * @param img8 the img 8
	 * @param img9 the img 9
	 * @param img10 the img 10
	 * @param img11 the img 11
	 * @param img12 the img 12
	 * @param img13 the img 13
	 * @param img14 the img 14
	 * @param img15 the img 15
	 * @param img16 the img 16
	 * @param img17 the img 17
	 * @param img18 the img 18
	 * @param img19 the img 19
	 * @param img20 the img 20
	 * @param img21 the img 21
	 * @param img22 the img 22
	 * @param img23 the img 23
	 * @param img24 the img 24
	 * @param img25 the img 25
	 * @param img26 the img 26
	 * @param img27 the img 27
	 */
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
	
	/**
	 * Instantiates a new animation.
	 *
	 * @param speed the speed
	 * @param img1 the img 1
	 * @param img2 the img 2
	 * @param img3 the img 3
	 * @param img4 the img 4
	 * @param img5 the img 5
	 * @param img6 the img 6
	 * @param img7 the img 7
	 * @param img8 the img 8
	 * @param img9 the img 9
	 * @param img10 the img 10
	 * @param img11 the img 11
	 * @param img12 the img 12
	 * @param img13 the img 13
	 * @param img14 the img 14
	 * @param img15 the img 15
	 * @param img16 the img 16
	 * @param img17 the img 17
	 * @param img18 the img 18
	 * @param img19 the img 19
	 * @param img20 the img 20
	 * @param img21 the img 21
	 * @param img22 the img 22
	 * @param img23 the img 23
	 * @param img24 the img 24
	 * @param img25 the img 25
	 * @param img26 the img 26
	 */
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
	
	/**
	 * Instantiates a new animation.
	 *
	 * @param speed the speed
	 * @param img1 the img 1
	 * @param img2 the img 2
	 * @param img3 the img 3
	 * @param img4 the img 4
	 * @param img5 the img 5
	 * @param img6 the img 6
	 * @param img7 the img 7
	 * @param img8 the img 8
	 * @param img9 the img 9
	 * @param img10 the img 10
	 * @param img11 the img 11
	 * @param img12 the img 12
	 * @param img13 the img 13
	 * @param img14 the img 14
	 * @param img15 the img 15
	 * @param img16 the img 16
	 * @param img17 the img 17
	 * @param img18 the img 18
	 * @param img19 the img 19
	 * @param img20 the img 20
	 * @param img21 the img 21
	 * @param img22 the img 22
	 * @param img23 the img 23
	 * @param img24 the img 24
	 * @param img25 the img 25
	 */
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
	
	/**
	 * Instantiates a new animation.
	 *
	 * @param speed the speed
	 * @param img1 the img 1
	 * @param img2 the img 2
	 * @param img3 the img 3
	 * @param img4 the img 4
	 * @param img5 the img 5
	 * @param img6 the img 6
	 * @param img7 the img 7
	 * @param img8 the img 8
	 * @param img9 the img 9
	 * @param img10 the img 10
	 * @param img11 the img 11
	 * @param img12 the img 12
	 * @param img13 the img 13
	 * @param img14 the img 14
	 * @param img15 the img 15
	 * @param img16 the img 16
	 * @param img17 the img 17
	 * @param img18 the img 18
	 * @param img19 the img 19
	 * @param img20 the img 20
	 * @param img21 the img 21
	 * @param img22 the img 22
	 * @param img23 the img 23
	 * @param img24 the img 24
	 */
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
	
	/**
	 * Instantiates a new animation.
	 *
	 * @param speed the speed
	 * @param img1 the img 1
	 * @param img2 the img 2
	 * @param img3 the img 3
	 * @param img4 the img 4
	 * @param img5 the img 5
	 * @param img6 the img 6
	 * @param img7 the img 7
	 * @param img8 the img 8
	 * @param img9 the img 9
	 * @param img10 the img 10
	 * @param img11 the img 11
	 * @param img12 the img 12
	 * @param img13 the img 13
	 * @param img14 the img 14
	 * @param img15 the img 15
	 * @param img16 the img 16
	 * @param img17 the img 17
	 * @param img18 the img 18
	 * @param img19 the img 19
	 * @param img20 the img 20
	 * @param img21 the img 21
	 * @param img22 the img 22
	 * @param img23 the img 23
	 */
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
	
	/**
	 * Instantiates a new animation.
	 *
	 * @param speed the speed
	 * @param img1 the img 1
	 * @param img2 the img 2
	 * @param img3 the img 3
	 * @param img4 the img 4
	 * @param img5 the img 5
	 * @param img6 the img 6
	 * @param img7 the img 7
	 * @param img8 the img 8
	 * @param img9 the img 9
	 * @param img10 the img 10
	 * @param img11 the img 11
	 * @param img12 the img 12
	 * @param img13 the img 13
	 * @param img14 the img 14
	 * @param img15 the img 15
	 * @param img16 the img 16
	 * @param img17 the img 17
	 * @param img18 the img 18
	 * @param img19 the img 19
	 * @param img20 the img 20
	 * @param img21 the img 21
	 * @param img22 the img 22
	 */
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
	
	/**
	 * Instantiates a new animation.
	 *
	 * @param speed the speed
	 * @param img1 the img 1
	 * @param img2 the img 2
	 * @param img3 the img 3
	 * @param img4 the img 4
	 * @param img5 the img 5
	 * @param img6 the img 6
	 * @param img7 the img 7
	 * @param img8 the img 8
	 * @param img9 the img 9
	 * @param img10 the img 10
	 * @param img11 the img 11
	 * @param img12 the img 12
	 * @param img13 the img 13
	 * @param img14 the img 14
	 * @param img15 the img 15
	 * @param img16 the img 16
	 * @param img17 the img 17
	 * @param img18 the img 18
	 * @param img19 the img 19
	 * @param img20 the img 20
	 * @param img21 the img 21
	 */
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
	
	/**
	 * Instantiates a new animation.
	 *
	 * @param speed the speed
	 * @param img1 the img 1
	 * @param img2 the img 2
	 * @param img3 the img 3
	 * @param img4 the img 4
	 * @param img5 the img 5
	 * @param img6 the img 6
	 * @param img7 the img 7
	 * @param img8 the img 8
	 * @param img9 the img 9
	 * @param img10 the img 10
	 * @param img11 the img 11
	 * @param img12 the img 12
	 * @param img13 the img 13
	 * @param img14 the img 14
	 * @param img15 the img 15
	 * @param img16 the img 16
	 * @param img17 the img 17
	 * @param img18 the img 18
	 * @param img19 the img 19
	 * @param img20 the img 20
	 */
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
	
	/**
	 * Instantiates a new animation.
	 *
	 * @param speed the speed
	 * @param img1 the img 1
	 * @param img2 the img 2
	 * @param img3 the img 3
	 * @param img4 the img 4
	 * @param img5 the img 5
	 * @param img6 the img 6
	 * @param img7 the img 7
	 * @param img8 the img 8
	 * @param img9 the img 9
	 * @param img10 the img 10
	 * @param img11 the img 11
	 * @param img12 the img 12
	 * @param img13 the img 13
	 * @param img14 the img 14
	 * @param img15 the img 15
	 * @param img16 the img 16
	 * @param img17 the img 17
	 * @param img18 the img 18
	 * @param img19 the img 19
	 */
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
	
	/**
	 * Instantiates a new animation.
	 *
	 * @param speed the speed
	 * @param img1 the img 1
	 * @param img2 the img 2
	 * @param img3 the img 3
	 * @param img4 the img 4
	 * @param img5 the img 5
	 * @param img6 the img 6
	 * @param img7 the img 7
	 * @param img8 the img 8
	 * @param img9 the img 9
	 * @param img10 the img 10
	 * @param img11 the img 11
	 * @param img12 the img 12
	 * @param img13 the img 13
	 * @param img14 the img 14
	 * @param img15 the img 15
	 * @param img16 the img 16
	 * @param img17 the img 17
	 * @param img18 the img 18
	 */
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
	
	/**
	 * Instantiates a new animation.
	 *
	 * @param speed the speed
	 * @param img1 the img 1
	 * @param img2 the img 2
	 * @param img3 the img 3
	 * @param img4 the img 4
	 * @param img5 the img 5
	 * @param img6 the img 6
	 * @param img7 the img 7
	 * @param img8 the img 8
	 * @param img9 the img 9
	 * @param img10 the img 10
	 * @param img11 the img 11
	 * @param img12 the img 12
	 * @param img13 the img 13
	 * @param img14 the img 14
	 * @param img15 the img 15
	 * @param img16 the img 16
	 * @param img17 the img 17
	 */
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
	
	/**
	 * Instantiates a new animation.
	 *
	 * @param speed the speed
	 * @param img1 the img 1
	 * @param img2 the img 2
	 * @param img3 the img 3
	 * @param img4 the img 4
	 * @param img5 the img 5
	 * @param img6 the img 6
	 * @param img7 the img 7
	 * @param img8 the img 8
	 * @param img9 the img 9
	 * @param img10 the img 10
	 * @param img11 the img 11
	 * @param img12 the img 12
	 * @param img13 the img 13
	 * @param img14 the img 14
	 * @param img15 the img 15
	 * @param img16 the img 16
	 */
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
	
	/**
	 * Instantiates a new animation.
	 *
	 * @param speed the speed
	 * @param img1 the img 1
	 * @param img2 the img 2
	 * @param img3 the img 3
	 * @param img4 the img 4
	 * @param img5 the img 5
	 * @param img6 the img 6
	 * @param img7 the img 7
	 * @param img8 the img 8
	 * @param img9 the img 9
	 * @param img10 the img 10
	 * @param img11 the img 11
	 * @param img12 the img 12
	 * @param img13 the img 13
	 * @param img14 the img 14
	 * @param img15 the img 15
	 */
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
	
	/**
	 * Instantiates a new animation.
	 *
	 * @param speed the speed
	 * @param img1 the img 1
	 * @param img2 the img 2
	 * @param img3 the img 3
	 * @param img4 the img 4
	 * @param img5 the img 5
	 * @param img6 the img 6
	 * @param img7 the img 7
	 * @param img8 the img 8
	 * @param img9 the img 9
	 * @param img10 the img 10
	 * @param img11 the img 11
	 * @param img12 the img 12
	 * @param img13 the img 13
	 * @param img14 the img 14
	 */
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
	
	/**
	 * Instantiates a new animation.
	 *
	 * @param speed the speed
	 * @param img1 the img 1
	 * @param img2 the img 2
	 * @param img3 the img 3
	 * @param img4 the img 4
	 * @param img5 the img 5
	 * @param img6 the img 6
	 * @param img7 the img 7
	 * @param img8 the img 8
	 * @param img9 the img 9
	 * @param img10 the img 10
	 * @param img11 the img 11
	 * @param img12 the img 12
	 * @param img13 the img 13
	 */
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
	
	/**
	 * Instantiates a new animation.
	 *
	 * @param speed the speed
	 * @param img1 the img 1
	 * @param img2 the img 2
	 * @param img3 the img 3
	 * @param img4 the img 4
	 * @param img5 the img 5
	 * @param img6 the img 6
	 * @param img7 the img 7
	 * @param img8 the img 8
	 * @param img9 the img 9
	 * @param img10 the img 10
	 * @param img11 the img 11
	 * @param img12 the img 12
	 */
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
	
	/**
	 * Instantiates a new animation.
	 *
	 * @param speed the speed
	 * @param img1 the img 1
	 * @param img2 the img 2
	 * @param img3 the img 3
	 * @param img4 the img 4
	 * @param img5 the img 5
	 * @param img6 the img 6
	 * @param img7 the img 7
	 * @param img8 the img 8
	 * @param img9 the img 9
	 * @param img10 the img 10
	 * @param img11 the img 11
	 */
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
	
	/**
	 * Instantiates a new animation.
	 *
	 * @param speed the speed
	 * @param img1 the img 1
	 * @param img2 the img 2
	 * @param img3 the img 3
	 * @param img4 the img 4
	 * @param img5 the img 5
	 * @param img6 the img 6
	 * @param img7 the img 7
	 * @param img8 the img 8
	 * @param img9 the img 9
	 * @param img10 the img 10
	 */
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
	
	/**
	 * Instantiates a new animation.
	 *
	 * @param speed the speed
	 * @param img1 the img 1
	 * @param img2 the img 2
	 * @param img3 the img 3
	 * @param img4 the img 4
	 * @param img5 the img 5
	 * @param img6 the img 6
	 * @param img7 the img 7
	 * @param img8 the img 8
	 * @param img9 the img 9
	 */
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
	
	/**
	 * Instantiates a new animation.
	 *
	 * @param speed the speed
	 * @param img1 the img 1
	 * @param img2 the img 2
	 * @param img3 the img 3
	 * @param img4 the img 4
	 * @param img5 the img 5
	 * @param img6 the img 6
	 * @param img7 the img 7
	 * @param img8 the img 8
	 */
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
	
	/**
	 * Instantiates a new animation.
	 *
	 * @param speed the speed
	 * @param img1 the img 1
	 * @param img2 the img 2
	 * @param img3 the img 3
	 * @param img4 the img 4
	 * @param img5 the img 5
	 * @param img6 the img 6
	 * @param img7 the img 7
	 */
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
	
	/**
	 * Instantiates a new animation.
	 *
	 * @param speed the speed
	 * @param img1 the img 1
	 * @param img2 the img 2
	 * @param img3 the img 3
	 * @param img4 the img 4
	 * @param img5 the img 5
	 * @param img6 the img 6
	 */
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
	
	/**
	 * Instantiates a new animation.
	 *
	 * @param speed the speed
	 * @param img1 the img 1
	 * @param img2 the img 2
	 * @param img3 the img 3
	 * @param img4 the img 4
	 * @param img5 the img 5
	 */
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
	
	/**
	 * Instantiates a new animation.
	 *
	 * @param speed the speed
	 * @param img1 the img 1
	 * @param img2 the img 2
	 * @param img3 the img 3
	 * @param img4 the img 4
	 */
	//4 frame
	public Animation(int speed, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4){
		this.speed = speed;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		this.img4 = img4;
		frames = 4;
	}
	
	/**
	 * Instantiates a new animation.
	 *
	 * @param speed the speed
	 * @param img1 the img 1
	 * @param img2 the img 2
	 * @param img3 the img 3
	 */
	//3 frame
	public Animation(int speed, BufferedImage img1, BufferedImage img2, BufferedImage img3){
		this.speed = speed;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		frames = 3;
	}
	
	/**
	 * Instantiates a new animation.
	 *
	 * @param speed the speed
	 * @param img1 the img 1
	 * @param img2 the img 2
	 */
	//2 frame
	public Animation(int speed, BufferedImage img1, BufferedImage img2){
		this.speed = speed;
		this.img1 = img1;
		this.img2 = img2;
		frames = 2;
	}
	
	/**
	 * Run animation.
	 */
	public void runAnimation(){
		index++;
		if(index > speed){
			index = 0;
			nextFrame();
		}	
	}
	
	/**
	 * Next frame.
	 */
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
	
	/**
	 * Draw animation.
	 *
	 * @param g the g
	 * @param x the x
	 * @param y the y
	 * @param offset the offset
	 */
	public void drawAnimation(Graphics g, double x, double y, int offset){
		g.drawImage(currentImg, (int)x - offset, (int)y, null);
	}
	
	/**
	 * Sets the count.
	 *
	 * @param count the new count
	 */
	public void setCount(int count){
		this.count = count;
	}
	
	/**
	 * Gets the count.
	 *
	 * @return the count
	 */
	public int getCount(){
		return count;
	}
	
	/**
	 * Gets the speed.
	 *
	 * @return the speed
	 */
	public int getSpeed(){
		return speed;
	}
	
	/**
	 * Sets the speed.
	 *
	 * @param speed the new speed
	 */
	public void setSpeed(int speed){
		this.speed = speed;
	}
	
}