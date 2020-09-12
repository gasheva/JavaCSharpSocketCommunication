package ru.gasheva.database;

import ru.gasheva.backend.NotNormEntity;

import java.sql.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class NotNormDao {
    private DataSource dataSource = new DataSource("src/main/resources/JdbcConfigSqlite.properties");
    public List<NotNormEntity> read() {
        List<NotNormEntity> notNormEntities = new LinkedList<>();
        try(Connection conn = dataSource.getConnection()){
            String command = "SELECT gal_id,\n" +
                    "       gal_name,\n" +
                    "       gal_location,\n" +
                    "       id_check_date,\n" +
                    "       check_date,\n" +
                    "       need_rest,\n" +
                    "       marks,\n" +
                    "       id_paint,\n" +
                    "       paint_name,\n" +
                    "       paint_price,\n" +
                    "       artist_id,\n" +
                    "       artist_fio,\n" +
                    "       artist_b_city,\n" +
                    "       artist_have_awards,\n" +
                    "       checker_id,\n" +
                    "       checker_name,\n" +
                    "       start_year\n" +
                    "FROM not_normalize;";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(command);
            while (rs.next()){
                NotNormEntity entity = new NotNormEntity();
                entity.setGal_id(rs.getInt("gal_id"));
                entity.setGal_name(rs.getString("gal_name"));
                entity.setGal_location(rs.getString("gal_location"));
                entity.setId_check_date(rs.getInt("id_check_date"));
				entity.setCheck_date(LocalDate.parse(rs.getString("check_date")));
                System.out.println(entity.getCheck_date());
				entity.setNeed_rest(rs.getBoolean("need_rest"));
				entity.setMarks(rs.getString("marks"));
				entity.setId_paint(rs.getInt("id_paint"));
				entity.setPaint_name(rs.getString("paint_name"));
				entity.setPaint_price(rs.getInt("paint_price"));
				entity.setArtist_id(rs.getInt("artist_id"));
				entity.setArtist_fio(rs.getString("artist_fio"));
				entity.setArtist_b_city(rs.getString("artist_b_city"));
				entity.setArtist_have_awards(rs.getBoolean("artist_have_awards"));
				entity.setChecker_id(rs.getInt("checker_id"));
				entity.setChecker_name(rs.getString("checker_name"));
				entity.setStart_year(LocalDate.parse(rs.getString("start_year")));
                notNormEntities.add(entity);
            }
            return notNormEntities;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
