package kadai_010;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Scores_Chapter10 {

	public static void main(String[] args) {
		
		Scanner scanner = null;
		Connection con = null;
		Statement statement = null;
		
		try {

			//データベースに接続
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost/challenge_java",
					"root",
					"Mysql.1209"
			);
			statement = con.createStatement();
		
			System.out.println("データベース接続成功：" + statement.toString() );
			
			
            // SQLクエリを準備（成績更新）
            String sqlUp = "UPDATE scores SET score_math = 95,score_english = 80 WHERE id = 5;";

            // SQLクエリを実行（DBMSに送信）
            System.out.println("レコード更新を実行します");
            int rowCnt = statement.executeUpdate(sqlUp);
            System.out.println( rowCnt + "件のレコードが更新されました");
			
            
            
			//レコード取得、並べ替え
			String sqlOrder = "SELECT * FROM scores ORDER BY score_math DESC, score_english DESC;";
			
            // SQLクエリを実行（DBMSに送信）
            
            ResultSet result = statement.executeQuery(sqlOrder);
            System.out.println("数学・英語の点数が高い順に並べ替えました");
            
            // SQLクエリの実行結果を抽出
            while(result.next()) {
            	int id = result.getInt("id");
            	String name = result.getString("name");
            	int score_math = result.getInt("score_math");
            	int score_english = result.getInt("score_english");
            	System.out.println(result.getRow() + "件目：生徒ID=" + id
            			+"/氏名=" + name + "/数学=" + score_math+ "/英語=" + score_english);
            }
         }catch(InputMismatchException e) {
        	 System.out.println("入力が正しくありません");
		}catch(SQLException e) {
			System.out.println("エラー発生：" + e.getMessage());
		}finally {
            // 使用したオブジェクトを解放
            if( scanner != null ) {
                scanner.close();
            }
            if( statement != null ) {
                try { statement.close(); } catch(SQLException ignore) {}
            }
            if( con != null ) {
                try { con.close(); } catch(SQLException ignore) {}
            }
		}
		

	}

}
