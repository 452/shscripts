
101.times{
	printProgBar(it);
}
println ""
//delay(5000);

private void delay(long milliseconds) {
		String bar = "[--------------------]";
		String icon = "%";

		long startTime = new Date().getTime();
		boolean bouncePositive = true;
		int barPosition = 0;

		while((new Date().getTime() - startTime) < milliseconds) {
			if(barPosition < bar.length() && barPosition > 0) {
				String b1 = bar.substring(0, barPosition);
				String b2 = bar.substring(barPosition);
				System.out.print("\r Delaying: " + b1 + icon + b2);
				if(bouncePositive) barPosition++;
				else barPosition--;
			}
			if(barPosition == bar.length()) {
				barPosition--;
				bouncePositive = false;
			}
			if(barPosition == 0) {
				barPosition++;
				bouncePositive = true;
			}

			try { Thread.sleep(100); }
			catch (Exception e) {}
		}
		System.out.println("\n\r");
}

public static void printProgBar(int percent){
    StringBuilder bar = new StringBuilder("[");

    for(int i = 0; i < 50; i++){
        if( i < (percent/2)){
            bar.append("=");
        } else if( i == (percent/2)){
            bar.append(">");
        } else {
            bar.append(" ");
        }
    }

    bar.append("]   " + percent + "%     ");
    System.out.print("\r" + bar.toString());
}
