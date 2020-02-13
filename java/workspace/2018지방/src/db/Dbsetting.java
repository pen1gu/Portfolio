package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Dbsetting {
	static Connection con;
	static Statement stmt;

	public static void main(String[] args) throws Exception {
		con = DriverManager.getConnection("jdbc:mysql://localhost/MealSetting?serverTimezone=UTC", "root", "1234");
		stmt = con.createStatement();

		stmt.execute("Drop database if exists `MealSetting`");
		stmt.execute("CREATE SCHEMA IF NOT EXISTS `MealSetting` DEFAULT CHARACTER SET utf8 ;");

		stmt.execute("CREATE TABLE IF NOT EXISTS `MealSetting`.`member` (\r\n"
				+ "  `memberNo` INT(11) NOT NULL AUTO_INCREMENT,\r\n"
				+ "  `memberName` VARCHAR(20) NULL DEFAULT NULL,\r\n" + "  `passwf` VARCHAR(4) NULL DEFAULT NULL,\r\n"
				+ "  PRIMARY KEY (`memberNo`))\r\n" + "ENGINE = InnoDB\r\n" + "DEFAULT CHARACTER SET = utf8;");
		
		
		stmt.execute("CREATE TABLE IF NOT EXISTS `MealSetting`.`cuisine` (\r\n"
				+ "  `cusineNo` INT(11) NOT NULL AUTO_INCREMENT,\r\n"
				+ "  `cuisineName` VARCHAR(10) NULL DEFAULT NULL,\r\n" + "  PRIMARY KEY (`cusineNo`))\r\n"
				+ "ENGINE = InnoDB\r\n" + "DEFAULT CHARACTER SET = utf8;");
		
		stmt.execute("CREATE TABLE IF NOT EXISTS `MealSetting`.`meal` (\r\n" + 
				"  `mealNo` INT(11) NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `cusineNo` INT(11) NULL DEFAULT NULL,\r\n" + 
				"  `mealName` VARCHAR(20) NULL DEFAULT NULL,\r\n" + 
				"  `price` INT(11) NULL DEFAULT NULL,\r\n" + 
				"  `maxCount` INT(11) NULL DEFAULT NULL,\r\n" + 
				"  `todayMeal` TINYINT(4) NULL DEFAULT NULL,\r\n" + 
				"  PRIMARY KEY (`mealNo`))\r\n" + 
				"ENGINE = InnoDB\r\n" + 
				"DEFAULT CHARACTER SET = utf8;");
		
		stmt.execute("CREATE TABLE IF NOT EXISTS `MealSetting`.`orderlist` (\r\n"
				+ "  `orderNo` INT(11) NOT NULL AUTO_INCREMENT,\r\n" + "  `cuisineNo` INT(11) NULL DEFAULT NULL,\r\n"
				+ "  `mealNo` INT(11) NULL DEFAULT NULL,\r\n" + "  `memberNo` INT(11) NULL DEFAULT NULL,\r\n"
				+ "  `orderCount` INT(11) NULL DEFAULT NULL,\r\n" + "  `amount` INT(11) NULL DEFAULT NULL,\r\n"
				+ "  `orderDate` DATETIME NULL DEFAULT NULL,\r\n" + "  PRIMARY KEY (`orderNo`))\r\n"
				+ "ENGINE = InnoDB\r\n" + "DEFAULT CHARACTER SET = utf8;");
		
		
		stmt.execute("");
		
	}

}
