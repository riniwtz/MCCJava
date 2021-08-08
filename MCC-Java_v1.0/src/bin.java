//			switch(cmd[0]) {
//				case "/give":
//					int amount = 1;
//					String item = "";
//					if (cmd.length == 3 || cmd.length == 4 && cmd[1].equals(player.getName())) {
//						
//						if (cmd.length == 4) {
//							amount = Integer.parseInt(cmd[3]);
//						}
//						
//						for (String b : block.getBlocks()) {
//							if (cmd[2].equals(b)) {
//								player.addItemInventory(cmd[2], amount);
//								item = cmd[2];
//								com.givePlayerItemOutput(item, amount, player);
//							}
//						}
//					}
//					break;
//				case "/time":
//					if (cmd.length == 2 || cmd.length == 3) {
//						switch (cmd[1]) {
//							case "add":
//								if (cmd.length == 3) {
//									world.addTime(Float.parseFloat(cmd[2]));
//								}
//								break;
//							case "query":
//								break;
//							case "set":
//								if (cmd.length == 3) {
//									world.setTime(Float.parseFloat(cmd[2]));
//								}
//								break;
////							Not really part of Minecraft command. (Only used for fixing bugs)
////							case "get":
////								if (cmd.length == 2) {
////									System.out.println((int)world.getTime());
////								}
////									
////								break;
//							default:
//						}
//					}
//					break;
//				case "/kill":
//					
//					if (cmd.length == 1) {
//						player.kill();
//						com.killPlayerOutput(player.getName());
//					}
//			
//					if (cmd.length == 2) {
//						for (String p : player.getPlayers()) {
//							if (cmd[1].equals(p)) {
//								player.kill(cmd[1]);
//								com.killPlayerOutput(cmd[1]);
//							}
//						}
//					}
//					break;	
//				default:
//					com.unknownCommandOutput(cmd);
//			}