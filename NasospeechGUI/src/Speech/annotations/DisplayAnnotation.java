/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.annotations;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Tatapower SED
 *
 */
public class DisplayAnnotation {

    public String dispaly(String fileName, int startPos, int endPos, Connection conn, String current_userID, int orderby) {
        GetAnnotation getAnnotation = new GetAnnotation(conn);
        String current_user_roll = getAnnotation.getUserLevel(current_userID);

        // System.out.println(current_user_roll);

        ResultSet rs = getAnnotation.getSelected(fileName, startPos, endPos, orderby);
        String html = "";
        try {
            html = "<html>"
                    + "<body>"
                    + "<table>";

            int j = 0;
            String imgsrc = DisplayAnnotation.class.getClassLoader().getSystemResource("Speech/Icons/men1.png").toString();
            String imgsrc2 = DisplayAnnotation.class.getClassLoader().getSystemResource("Speech/Icons/men2.png").toString();
            String imgsrc3 = DisplayAnnotation.class.getClassLoader().getSystemResource("Speech/Icons/anndelete.png").toString();
            String imgsrc4 = DisplayAnnotation.class.getClassLoader().getSystemResource("Speech/Icons/annedit.jpg").toString();
            while (rs.next()) {
                try {
                    if (j++ % 2 == 0) {
                        html = html + "<tr><td><img src='" + imgsrc2 + "' /><br></td>";
                    } else {
                        html = html + "<tr><td><img src='" + imgsrc + "' /><br></td>";
                    }
                    String userID = rs.getString("user_id");
                    String userName = userID;
                    if (userID.length() > 10) {
                        userID = userID.substring(0, 10);
                    }

                    html = html + "<td><b>" + userID + "</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getDate("ann_date") + "<br></td>"
                            + "</tr>";


                    if (rs.getString("level").equals("High")) {
                        html = html + "<tr><td colspan=\"2\"  style=\"color:red\">" + rs.getString("message") + "</td></tr>";

                    } else if (rs.getString("level").equals("Low")) {
                        html = html + "<tr><td colspan=\"2\" style=\"color:blue\">" + rs.getString("message") + "</td></tr>";

                    } else {
                        html = html + "<tr><td colspan=\"2\" style=\"color:green\">" + rs.getString("message") + "</td></tr>";

                    }
                    String user_roll = getAnnotation.getUserLevel(userName);
                    try {
                        if ((current_userID.toLowerCase()).equals(userName.toLowerCase()) || (Integer.parseInt(user_roll) < (Integer.parseInt(current_user_roll)))) {
                            html = html + "<tr><td colspan=\"2\" align=\"right\"><a href='http://tatapower.com~edit~" + rs.getString("annotate_message_id") + "~" + rs.getString("message") + "~" + rs.getString("level") + "'><img src='" + imgsrc4 + "' /></a>&nbsp;&nbsp;<a href='http://tatapower.com~remove~" + rs.getString("annotate_message_id") + "~" + rs.getString("message") + "~" + rs.getString("level") + "'><img src='" + imgsrc3 + "' /></a></td></tr>";
                        }
                    } catch (NumberFormatException er) {
                        System.err.println(er);
                    }
                    html = html + "<tr><td colspan=\"2\"><hr></td></tr>";
                } catch (Exception er) {
                    System.err.println(er);
                }
            }
            html = html + "</table></body></html>";


            rs.close();


        } catch (SQLException er) {
            System.err.println(er);
        }
        return html;
    }
}
