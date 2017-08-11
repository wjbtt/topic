package test;

import java.util.Random;

public class TestRm {
	public static void main(String[] args) {
		Random rm = new Random();
		int a1 = 0,a2 = 0,a3 = 0,a4 = 0;
		int a5 = 0,a6 = 0,a7 = 0,a8 = 0;
		for(int i=0;i<100000;i++){
			int z = rm.nextInt(8)+1;
			if(z==1){
				a1+=1;
			}else if(z==2){
				a2+=1;
			}else if(z==3){
				a3+=1;
			}else if(z==4){
				a4+=1;
			}else if(z==5){
				a5+=1;
			}else if(z==6){
				a6+=1;
			}else if(z==7){
				a7+=1;
			}else if(z==8){
				a8+=1;
			}
		}
		System.out.println(a1+","+a2+","+a3+","+a4+","+a5+","+a6+","+a7+","+a8);
	}
}
